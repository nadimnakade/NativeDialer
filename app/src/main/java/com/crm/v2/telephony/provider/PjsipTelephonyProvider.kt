package com.crm.v2.telephony.provider

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.common.log.ApiLogger
import com.crm.v2.telephony.CallState
import com.crm.v2.telephony.PjsipManager
import com.crm.v2.telephony.TelephonyConfig
import com.crm.v2.telephony.TelephonyEvent
import com.crm.v2.telephony.TelephonyManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PjsipTelephonyProvider @Inject constructor(
    @ApplicationContext private val context: Context,
) : TelephonyManager {

    companion object {
        private const val TAG = "PjsipTelephonyProvider"
    }

    private val pjsipManager = PjsipManager()
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private val _callState = MutableStateFlow<CallState>(CallState.Idle)
    override val callState: Flow<CallState> = _callState.asStateFlow()

    private val _events = MutableSharedFlow<TelephonyEvent>(extraBufferCapacity = 10)
    override val events: Flow<TelephonyEvent> = _events.asSharedFlow()

    private val _registrationState = MutableStateFlow(false)
    override val registrationState: Flow<Boolean> = _registrationState.asStateFlow()

    private var currentCallId: Int = -1
    @Volatile private var isInitialized = false
    @Volatile private var isReRegistering = false
    @Volatile private var isMuted = false
    @Volatile private var isSpeakerOn = false
    private var previousAudioMode = AudioManager.MODE_NORMAL
    private var pendingConfig: TelephonyConfig? = null
    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    private var outgoingRingtone: Ringtone? = null

    init {
        registerNetworkCallback()
    }

    override suspend fun register(config: TelephonyConfig) {
        if (isInitialized) return

        _callState.value = CallState.Registering
        ApiLogger.event(TAG, "Registering with PJSIP via UDP...")

        val callback = object : PjsipManager.Callback {
            override fun onRegState(accId: Int, code: Int, reason: String) {
                ApiLogger.event(TAG, "SIP onRegState: accId=$accId code=$code reason=$reason")
                if (code in 200..299 || code == 0) {
                    _callState.value = CallState.Registered
                    _registrationState.value = true
                    ApiLogger.event(TAG, "SIP Registration successful")
                } else if (code == 0 && reason.isEmpty()) {
                    _callState.value = CallState.Registered
                    _registrationState.value = true
                    ApiLogger.event(TAG, "SIP Registration successful (code=0)")
                } else {
                    _callState.value = CallState.Error
                    _registrationState.value = false
                    _events.tryEmit(TelephonyEvent.RegistrationFailed("Registration failed: $code $reason"))
                    ApiLogger.error(TAG, "SIP Registration failed: $code $reason")
                }
            }

            override fun onCallState(callId: Int, state: Int, lastStatus: Int, reason: String) {
                ApiLogger.event(TAG, "SIP onCallState: callId=$callId state=$state lastStatus=$lastStatus reason=$reason")
                when (state) {
                    1 -> {
                        _callState.value = CallState.Calling
                    }
                    2, 3 -> {
                        _callState.value = CallState.Ringing
                    }
                    4, 5 -> {
                        stopOutgoingRingtone()
                        if (_callState.value != CallState.Connected) {
                            _callState.value = CallState.Connected
                            currentCallId = callId
                            _events.tryEmit(TelephonyEvent.CallConnected(callId.toString()))
                            ApiLogger.event(TAG, "SIP call connected: callId=$callId")
                        }
                    }
                    6 -> {
                        stopOutgoingRingtone()
                        _callState.value = CallState.Idle
                        _events.tryEmit(TelephonyEvent.CallDisconnected(callId.toString(), lastStatus))
                        currentCallId = -1
                        ApiLogger.event(TAG, "SIP call disconnected: callId=$callId status=$lastStatus reason=$reason")
                    }
                    else -> {
                        ApiLogger.event(TAG, "SIP call state: callId=$callId state=$state lastStatus=$lastStatus reason=$reason")
                    }
                }
            }

            override fun onIncomingCall(accId: Int, callId: Int, remoteUri: String) {
                ApiLogger.event(TAG, "SIP onIncomingCall: accId=$accId callId=$callId remoteUri=$remoteUri")
                _callState.value = CallState.Ringing
                currentCallId = callId
                _events.tryEmit(TelephonyEvent.IncomingCall(remoteUri, null))
            }

            override fun onCallMediaState(callId: Int) {
                ApiLogger.event(TAG, "SIP onCallMediaState: callId=$callId — audio active")
            }
        }

        pjsipManager.init(callback)

        val sipServer = config.sipServer.ifEmpty { ApiConstants.Sip.SERVER }
        val sipUsername = config.sipUsername.ifEmpty { ApiConstants.Sip.USERNAME }
        val sipPassword = config.sipPassword.ifEmpty { ApiConstants.Sip.PASSWORD }
        val sipRealm = config.sipServer.ifEmpty { ApiConstants.Sip.REALM }
        val sipPort = ApiConstants.Sip.PORT.toIntOrNull() ?: 7065

        val transportResult = pjsipManager.addTransport(
            PjsipManager.TRANSPORT_UDP,
            sipServer,
            sipPort
        )
        ApiLogger.event(TAG, "UDP transport result: $transportResult")

        val sipId = "sip:$sipUsername@$sipServer"
        val regUri = "sip:$sipServer:$sipPort"
        val accountId = pjsipManager.addAccount(sipId, regUri, sipUsername, sipPassword, sipRealm)
        ApiLogger.event(TAG, "Account added: status=$accountId")

        pendingConfig = config
        isInitialized = true
    }

    override suspend fun unregister() {
        stopOutgoingRingtone()
        unregisterNetworkCallback()
        pjsipManager.destroy()
        currentCallId = -1
        isInitialized = false
        _callState.value = CallState.Idle
        _registrationState.value = false
        ApiLogger.event(TAG, "Unregistered")
    }

    override suspend fun call(number: String) {
        if (!isInitialized) {
            _events.tryEmit(TelephonyEvent.Error("Not registered"))
            return
        }

        val sipServer = ApiConstants.Sip.SERVER
        val sipPort = ApiConstants.Sip.PORT.toIntOrNull() ?: 7065
        val callUri = "sip:$number@$sipServer:$sipPort"
        ApiLogger.event(TAG, "Calling: $callUri")

        _callState.value = CallState.Calling
        playOutgoingRingtone()
        val status = pjsipManager.makeCall(callUri)
        if (status != 0) {
            stopOutgoingRingtone()
            _callState.value = CallState.Error
            _events.tryEmit(TelephonyEvent.Error("Call failed: status=$status"))
            ApiLogger.error(TAG, "Make call failed: $status")
        }
    }

    override suspend fun answer() {
        if (currentCallId >= 0) {
            pjsipManager.answer(currentCallId, 200)
            ApiLogger.event(TAG, "Call answered: callId=$currentCallId")
        }
    }

    override suspend fun hangup() {
        stopOutgoingRingtone()
        val callIdToHangup = currentCallId
        if (callIdToHangup >= 0) {
            pjsipManager.hangup(callIdToHangup)
            ApiLogger.event(TAG, "Hung up: callId=$callIdToHangup")
        } else {
            val activeId = pjsipManager.getActiveCallId()
            if (activeId >= 0) {
                pjsipManager.hangup(activeId)
                ApiLogger.event(TAG, "Hung up via active lookup: callId=$activeId")
            }
        }
        resetAudioState()
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
        isMuted = enabled
        if (currentCallId >= 0) {
            pjsipManager.setMute(currentCallId, enabled)
        }
        ApiLogger.event(TAG, "Mute: $enabled")
    }

    override suspend fun speaker(enabled: Boolean) {
        isSpeakerOn = enabled
        previousAudioMode = audioManager.mode
        audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
        audioManager.isSpeakerphoneOn = enabled
        ApiLogger.event(TAG, "Speaker: $enabled")
    }

    override suspend fun bluetooth(enabled: Boolean) {
        ApiLogger.event(TAG, "Bluetooth: $enabled")
    }

    override fun getCurrentState(): CallState = _callState.value

    private fun playOutgoingRingtone() {
        try {
            if (outgoingRingtone?.isPlaying == true) return
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            outgoingRingtone = RingtoneManager.getRingtone(context, uri)
            outgoingRingtone?.let { ring ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    val attrs = AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                    ring.audioAttributes = attrs
                }
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
        } catch (e: Exception) {
            ApiLogger.error(TAG, "Failed to stop outgoing ringtone: ${e.message}", e)
        }
    }

    private fun resetAudioState() {
        if (isMuted && currentCallId >= 0) {
            pjsipManager.setMute(currentCallId, false)
            isMuted = false
        }
        audioManager.isSpeakerphoneOn = false
        audioManager.mode = previousAudioMode
        isSpeakerOn = false
    }

    private fun registerNetworkCallback() {
        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkRequest = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    ApiLogger.event(TAG, "Network available — re-registering SIP")
                    if (isInitialized && _registrationState.value.not()) {
                        reRegister()
                    }
                }

                override fun onLost(network: Network) {
                    ApiLogger.event(TAG, "Network lost — SIP may be unreachable")
                    _registrationState.value = false
                }
            }

            connectivityManager.registerNetworkCallback(networkRequest, networkCallback!!)
            ApiLogger.event(TAG, "Network callback registered")
        } catch (e: Exception) {
            ApiLogger.error(TAG, "Failed to register network callback: ${e.message}")
        }
    }

    private fun unregisterNetworkCallback() {
        try {
            if (networkCallback != null) {
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                connectivityManager.unregisterNetworkCallback(networkCallback!!)
                networkCallback = null
                ApiLogger.event(TAG, "Network callback unregistered")
            }
        } catch (e: Exception) {
            ApiLogger.error(TAG, "Failed to unregister network callback: ${e.message}")
        }
    }

    private fun reRegister() {
        val config = pendingConfig ?: return
        if (isReRegistering) {
            ApiLogger.event(TAG, "Re-registration already in progress, skipping")
            return
        }
        isReRegistering = true
        try {
            isInitialized = false
            pjsipManager.destroy()
            _callState.value = CallState.Registering

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    register(config)
                } finally {
                    isReRegistering = false
                }
            }
            ApiLogger.event(TAG, "Re-registration initiated")
        } catch (e: Exception) {
            isReRegistering = false
            ApiLogger.error(TAG, "Re-registration failed: ${e.message}")
        }
    }
}
