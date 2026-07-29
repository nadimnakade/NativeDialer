package com.crm.v2.telephony.provider

import android.content.Context
import android.util.Log
import com.crm.v2.common.constants.ApiConstants
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

    override suspend fun register(config: TelephonyConfig) {
        _callState.value = CallState.Registered
        _registrationState.value = true
        Log.d(TAG, "Registered")
    }

    override suspend fun unregister() {
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
            Log.d(TAG, "Placing call to $number via backend: $url")

            val request = Request.Builder()
                .url(url)
                .post(jsonBody.toRequestBody("application/json".toMediaType()))
                .build()

            withContext(Dispatchers.IO) {
                client.newCall(request).execute().use { response ->
                    val bodyString = response.body?.string() ?: ""
                    Log.d(TAG, "Place call response: ${response.code} - $bodyString")

                    if (response.isSuccessful) {
                        val result = json.decodeFromString(PlaceCallResponse.serializer(), bodyString)
                        if (result.success) {
                            currentCallId = result.callId
                            Log.d(TAG, "Call placed. callId=${result.callId}")
                        } else {
                            _callState.value = CallState.Error
                            _events.tryEmit(TelephonyEvent.Error(result.message))
                            Log.e(TAG, "Call failed: ${result.message}")
                        }
                    } else {
                        _callState.value = CallState.Error
                        _events.tryEmit(TelephonyEvent.Error("API error: ${response.code}"))
                        Log.e(TAG, "API error: ${response.code}")
                    }
                }
            }
        } catch (e: Exception) {
            _callState.value = CallState.Error
            _events.tryEmit(TelephonyEvent.Error("Call failed: ${e::class.simpleName}: ${e.message}"))
            Log.e(TAG, "Call exception", e)
        }
    }

    private fun listenForSseEvents(userKey: String) {
        sseEventSource?.cancel()
        val sseUrl = "${ApiConstants.DIALER_WS_URL}Call/events?userKey=$userKey"
        Log.d(TAG, "Listening for SSE events: $sseUrl")

        val request = Request.Builder().url(sseUrl).build()
        val factory = EventSources.createFactory(sseClient)

        sseEventSource = factory.newEventSource(request, object : EventSourceListener() {
            override fun onEvent(eventSource: EventSource, id: String?, type: String?, data: String) {
                Log.d(TAG, "SSE event: type=$type data=$data")
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
                            Log.d(TAG, "Media info received: $remoteIp:$remotePort codec=$codec")
                            startAudioStream(remoteIp, remotePort, codec)
                        }
                        state == "ringing" -> {
                            _callState.value = CallState.Ringing
                            Log.d(TAG, "Call ringing")
                        }
                        state == "connected" -> {
                            _callState.value = CallState.Connected
                            _events.tryEmit(TelephonyEvent.CallConnected(currentCallId))
                            Log.d(TAG, "Call connected")
                        }
                        state == "failed" -> {
                            rtpAudioManager.stop()
                            _callState.value = CallState.Error
                            _events.tryEmit(TelephonyEvent.Error(message.ifEmpty { "Call failed" }))
                            Log.e(TAG, "Call failed: $message")
                        }
                        state == "ended" -> {
                            rtpAudioManager.stop()
                            _callState.value = CallState.Idle
                            _events.tryEmit(TelephonyEvent.CallDisconnected(currentCallId, 0))
                            currentCallId = ""
                            Log.d(TAG, "Call ended")
                        }
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to parse SSE event", e)
                }
            }

            override fun onFailure(eventSource: EventSource, t: Throwable?, response: okhttp3.Response?) {
                Log.w(TAG, "SSE connection failed: ${t?.message ?: response?.code}")
                sseEventSource = null
            }

            override fun onClosed(eventSource: EventSource) {
                Log.d(TAG, "SSE connection closed")
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
                    client.newCall(request).execute().close()
                }
            } catch (e: Exception) {
                Log.w(TAG, "Hangup API call failed", e)
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
}

