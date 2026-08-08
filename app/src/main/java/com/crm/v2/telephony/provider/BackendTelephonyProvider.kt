package com.crm.v2.telephony.provider

import android.content.Context
import android.media.AudioManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.util.Log
import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.common.log.ApiLogger
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.data.network.dto.call.PlaceCallRequest
import com.crm.v2.data.network.dto.call.PlaceCallResponse
import com.crm.v2.data.network.dto.call.HangupCallRequest
import com.crm.v2.telephony.CallState
import com.crm.v2.telephony.RtpAudioManager
import com.crm.v2.telephony.TelephonyConfig
import com.crm.v2.telephony.TelephonyEvent
import com.crm.v2.telephony.TelephonyManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.sse.EventSource
import okhttp3.sse.EventSourceListener
import okhttp3.sse.EventSources
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackendTelephonyProvider @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authDataStore: AuthDataStore,
    private val rtpAudioManager: RtpAudioManager,
) : TelephonyManager {

    companion object {
        private const val TAG = "BackendTelephonyProvider"
    }

    private val client = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()

    private val sseClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC })
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(0, TimeUnit.SECONDS)
        .build()

    private val json = Json { ignoreUnknownKeys = true; isLenient = true }

    private val _callState = MutableStateFlow<CallState>(CallState.Idle)
    override val callState: Flow<CallState> = _callState.asStateFlow()

    private val _events = MutableSharedFlow<TelephonyEvent>(extraBufferCapacity = 10)
    override val events: Flow<TelephonyEvent> = _events.asSharedFlow()

    private val _registrationState = MutableStateFlow(false)
    override val registrationState: Flow<Boolean> = _registrationState.asStateFlow()

    private var currentCallId: String = ""
    private var sseEventSource: EventSource? = null

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private var outgoingRingtone: Ringtone? = null
    private var previousAudioMode = AudioManager.MODE_NORMAL

    override suspend fun register(config: TelephonyConfig) {
        _callState.value = CallState.Registered
        _registrationState.value = true
        Log.d(TAG, "Registered")
    }

    override suspend fun unregister() {
        stopOutgoingRingtone()
        rtpAudioManager.stop()
        currentCallId = ""
        _callState.value = CallState.Idle
        _registrationState.value = false
        sseEventSource?.cancel()
        sseEventSource = null
        Log.d(TAG, "Unregistered")
    }

    override suspend fun call(number: String) {
        _callState.value = CallState.Calling

        try {
            val userKey = authDataStore.userKey ?: run {
                _callState.value = CallState.Error
                _events.tryEmit(TelephonyEvent.Error("User not logged in"))
                return
            }

            listenForSseEvents(userKey)

            val requestBody = PlaceCallRequest(
                phoneNumber = number,
                userKey = userKey,
            )

            val jsonBody = json.encodeToString(PlaceCallRequest.serializer(), requestBody)
            val url = ApiConstants.DIALER_WS_URL + ApiConstants.Call.PLACE
            ApiLogger.request(TAG, "POST", url, jsonBody)

            val request = Request.Builder()
                .url(url)
                .post(jsonBody.toRequestBody("application/json".toMediaType()))
                .build()

            withContext(Dispatchers.IO) {
                client.newCall(request).execute().use { response ->
                    val bodyString = response.body?.string() ?: ""
                    ApiLogger.response(TAG, response.code, url, bodyString)

                    if (response.isSuccessful) {
                        val result = json.decodeFromString(PlaceCallResponse.serializer(), bodyString)
                        if (result.success) {
                            currentCallId = result.callId
                            ApiLogger.event(TAG, "Call placed: callId=${result.callId} to=$number")
                        } else {
                            _callState.value = CallState.Error
                            _events.tryEmit(TelephonyEvent.Error(result.message))
                            ApiLogger.error(TAG, "Place call failed: ${result.message}")
                        }
                    } else {
                        _callState.value = CallState.Error
                        _events.tryEmit(TelephonyEvent.Error("API error: ${response.code}"))
                        ApiLogger.error(TAG, "Place call HTTP error: ${response.code}")
                    }
                }
            }
        } catch (e: Exception) {
            _callState.value = CallState.Error
            _events.tryEmit(TelephonyEvent.Error("Call failed: ${e::class.simpleName}: ${e.message}"))
            ApiLogger.error(TAG, "Place call exception: ${e.message}", e)
        }
    }

    private fun listenForSseEvents(userKey: String) {
        sseEventSource?.cancel()
        val sseUrl = "${ApiConstants.DIALER_WS_URL}Call/events?userKey=$userKey"
        ApiLogger.event(TAG, "SSE connecting: $sseUrl")

        val request = Request.Builder().url(sseUrl).build()
        val factory = EventSources.createFactory(sseClient)

        sseEventSource = factory.newEventSource(request, object : EventSourceListener() {
            override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                ApiLogger.event(TAG, "SSE event: type=$type data=$data")
                try {
                    val jsonObj = json.parseToJsonElement(data).jsonObject
                    val state = jsonObj["state"]?.jsonPrimitive?.content ?: ""
                    val message = jsonObj["message"]?.jsonPrimitive?.content ?: ""
                    val eventType = type ?: ""

                    when {
                        eventType == "media_info" || state == "media_info" -> {
                            val remoteIp = jsonObj["remote_media_ip"]?.jsonPrimitive?.content ?: ""
                            val remotePort = jsonObj["remote_media_port"]?.jsonPrimitive?.content?.toIntOrNull() ?: 0
                            val codec = jsonObj["codec"]?.jsonPrimitive?.content ?: "PCMU"
                            ApiLogger.event(TAG, "SSE media_info: remote=$remoteIp:$remotePort codec=$codec")
                            startAudioStream(remoteIp, remotePort, codec)
                        }
                        state == "ringing" -> {
                            _callState.value = CallState.Ringing
                            playOutgoingRingtone()
                            ApiLogger.event(TAG, "SSE call ringing")
                        }
                        state == "connected" -> {
                            stopOutgoingRingtone()
                            _callState.value = CallState.Connected
                            _events.tryEmit(TelephonyEvent.CallConnected(currentCallId))
                            ApiLogger.event(TAG, "SSE call connected")
                        }
                        state == "failed" -> {
                            stopOutgoingRingtone()
                            rtpAudioManager.stop()
                            _callState.value = CallState.Error
                            _events.tryEmit(TelephonyEvent.Error(message.ifEmpty { "Call failed" }))
                            ApiLogger.error(TAG, "SSE call failed: $message")
                        }
                        state == "ended" -> {
                            stopOutgoingRingtone()
                            rtpAudioManager.stop()
                            _callState.value = CallState.Idle
                            _events.tryEmit(TelephonyEvent.CallDisconnected(currentCallId, 0))
                            ApiLogger.event(TAG, "SSE call ended")
                            currentCallId = ""
                        }
                    }
                } catch (e: Exception) {
                    ApiLogger.error(TAG, "SSE parse error: ${e.message}", e)
                }
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: okhttp3.Response?) {
                ApiLogger.error(TAG, "SSE connection failed: ${t?.message ?: response?.code?.toString()}")
                sseEventSource = null
            }

            override fun onClosed(eventSource: EventSource) {
                ApiLogger.event(TAG, "SSE connection closed")
                sseEventSource = null
            }
        })
    }

    private fun startAudioStream(remoteIp: String, remotePort: Int, codec: String) {
        val audioWsUrl = "${ApiConstants.DIALER_WS_URL}ws/audio"
        Log.d(TAG, "Starting audio stream to: $audioWsUrl (remote=$remoteIp:$remotePort)")
        rtpAudioManager.start(audioWsUrl)
    }

    override suspend fun answer() {
        Log.d(TAG, "Answer - handled server-side")
    }

    override suspend fun hangup() {
        stopOutgoingRingtone()
        rtpAudioManager.stop()
        val wasActive = _callState.value.isActive
        val callId = currentCallId
        currentCallId = ""
        _callState.value = CallState.Idle

        if (wasActive && callId.isNotEmpty()) {
            try {
                val userKey = authDataStore.userKey ?: return
                val requestBody = HangupCallRequest(callId = callId, userKey = userKey)
                val jsonBody = json.encodeToString(HangupCallRequest.serializer(), requestBody)
                val url = ApiConstants.DIALER_WS_URL + ApiConstants.Call.HANGUP

                val request = Request.Builder()
                    .url(url)
                    .post(jsonBody.toRequestBody("application/json".toMediaType()))
                    .build()

                withContext(Dispatchers.IO) {
                    client.newCall(request).execute().use { response ->
                        val bodyString = response.body?.string() ?: ""
                        ApiLogger.response(TAG, response.code, url, bodyString)
                    }
                }
                ApiLogger.event(TAG, "Hangup sent: callId=$callId")
            } catch (e: Exception) {
                ApiLogger.error(TAG, "Hangup API failed: ${e.message}", e)
            }
        }
        _events.tryEmit(TelephonyEvent.CallDisconnected(callId, 0))
    }

    override suspend fun hold() {
        if (_callState.value is CallState.Connected) {
            _callState.value = CallState.OnHold
            Log.d(TAG, "Call on hold")
        }
    }

    override suspend fun resume() {
        if (_callState.value is CallState.OnHold) {
            _callState.value = CallState.Connected
            Log.d(TAG, "Call resumed")
        }
    }

    override suspend fun transfer(number: String) {
        Log.d(TAG, "Transfer to $number")
    }

    override suspend fun conference(number: String) {
        Log.w(TAG, "Conference not supported")
    }

    override suspend fun mute(enabled: Boolean) {
        Log.d(TAG, "Mute: $enabled")
    }

    override suspend fun speaker(enabled: Boolean) {
        Log.d(TAG, "Speaker: $enabled")
    }

    override suspend fun bluetooth(enabled: Boolean) {
        Log.d(TAG, "Bluetooth: $enabled")
    }

    override fun getCurrentState(): CallState = _callState.value

    private fun playOutgoingRingtone() {
        try {
            if (outgoingRingtone?.isPlaying == true) return
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            outgoingRingtone = RingtoneManager.getRingtone(context, uri)
            outgoingRingtone?.let { ring ->
                previousAudioMode = audioManager.mode
                audioManager.mode = AudioManager.MODE_RINGTONE
                ring.isLooping = true
                ring.play()
                ApiLogger.event(TAG, "Outgoing ringtone started")
            }
        } catch (e: Exception) {
            ApiLogger.error(TAG, "Failed to play outgoing ringtone: ${e.message}", e)
        }
    }

    private fun stopOutgoingRingtone() {
        try {
            outgoingRingtone?.let { ring ->
                if (ring.isPlaying) {
                    ring.stop()
                    ApiLogger.event(TAG, "Outgoing ringtone stopped")
                }
            }
            outgoingRingtone = null
            audioManager.mode = previousAudioMode
        } catch (e: Exception) {
            ApiLogger.error(TAG, "Failed to stop outgoing ringtone: ${e.message}", e)
        }
    }
}

