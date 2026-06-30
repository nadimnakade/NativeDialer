package com.crm.v2.telephony.pjsip

import android.content.Context
import android.util.Log
import com.crm.v2.telephony.CallState
import com.crm.v2.telephony.TelephonyConfig
import com.crm.v2.telephony.TelephonyEvent
import com.crm.v2.telephony.TelephonyManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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
    private val pjsipManager: PjsipManager,
) : TelephonyManager {

    companion object {
        private const val TAG = "PjsipTelephonyProvider"
        private const val SIP_SERVER = "11.111.111.111"
        private const val SIP_PORT = "5060"
    }

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val _callState = MutableStateFlow<CallState>(CallState.Idle)
    override val callState: Flow<CallState> = _callState.asStateFlow()

    private val _events = MutableSharedFlow<TelephonyEvent>(extraBufferCapacity = 10)
    override val events: Flow<TelephonyEvent> = _events.asSharedFlow()

    private val _registrationState = MutableStateFlow(false)
    override val registrationState: Flow<Boolean> = _registrationState.asStateFlow()

    private var currentConfig: TelephonyConfig? = null

    init {
        pjsipManager.initialize(logLevel = 4)

        scope.launch {
            pjsipManager.sipState.collect { state ->
                _callState.value = when (state) {
                    is SipCallState.Idle -> CallState.Idle
                    is SipCallState.Calling -> CallState.Calling
                    is SipCallState.Ringing -> CallState.Ringing
                    is SipCallState.Connecting -> CallState.Calling
                    is SipCallState.Connected -> CallState.Connected
                    is SipCallState.OnHold -> CallState.OnHold
                    is SipCallState.Disconnecting -> CallState.Idle
                    is SipCallState.Disconnected -> CallState.Idle
                }
            }
        }

        scope.launch {
            pjsipManager.events.collect { event ->
                when (event) {
                    is SipEvent.RegistrationSuccess -> {
                        _registrationState.value = true
                        _events.tryEmit(TelephonyEvent.CallConnected("registered"))
                    }
                    is SipEvent.RegistrationFailed -> {
                        _registrationState.value = false
                        _events.tryEmit(TelephonyEvent.RegistrationFailed)
                    }
                    is SipEvent.CallIncoming -> {
                        _events.tryEmit(TelephonyEvent.IncomingCall(event.callerUri, event.callerName))
                    }
                    is SipEvent.CallConnected -> {
                        _events.tryEmit(TelephonyEvent.CallConnected(event.callId))
                    }
                    is SipEvent.CallDisconnected -> {
                        _events.tryEmit(TelephonyEvent.CallDisconnected(event.callId, 0))
                    }
                    is SipEvent.Error -> {
                        _events.tryEmit(TelephonyEvent.Error(event.message))
                    }
                    else -> {}
                }
            }
        }
    }

    override suspend fun register(config: TelephonyConfig) {
        currentConfig = config
        _callState.value = CallState.Registering

        val sipConfig = SipAccountConfig(
            accountId = "sip:${config.sipUsername}@$SIP_SERVER:$SIP_PORT",
            registrarUri = "sip:$SIP_SERVER:$SIP_PORT",
            username = config.sipUsername,
            password = config.sipPassword,
            proxyUri = if (config.sipServer.isNotEmpty()) "sip:${config.sipServer}" else "",
        )

        val registered = pjsipManager.register(sipConfig)
        if (!registered) {
            _callState.value = CallState.Idle
            _events.tryEmit(TelephonyEvent.RegistrationFailed)
        }
    }

    override suspend fun unregister() {
        pjsipManager.unregister()
        _callState.value = CallState.Idle
        _registrationState.value = false
        currentConfig = null
    }

    override suspend fun call(number: String) {
        val server = currentConfig?.sipServer?.takeIf { it.isNotEmpty() } ?: SIP_SERVER
        val targetUri = "sip:$number@$server"
        pjsipManager.makeCall(targetUri)
    }

    override suspend fun answer() {
        pjsipManager.answer()
    }

    override suspend fun hangup() {
        pjsipManager.hangup()
    }

    override suspend fun hold() {
        pjsipManager.hold()
    }

    override suspend fun resume() {
        pjsipManager.resume()
    }

    override suspend fun transfer(number: String) {
        val server = currentConfig?.sipServer?.takeIf { it.isNotEmpty() } ?: SIP_SERVER
        pjsipManager.transfer("sip:$number@$server")
    }

    override suspend fun conference(number: String) {
        Log.w(TAG, "Conference not yet supported via PJSIP")
    }

    override suspend fun mute(enabled: Boolean) {
        pjsipManager.mute(enabled)
    }

    override suspend fun speaker(enabled: Boolean) {
        pjsipManager.setSpeaker(enabled)
    }

    override suspend fun bluetooth(enabled: Boolean) {
        pjsipManager.setBluetooth(enabled)
    }

    override fun getCurrentState(): CallState = _callState.value
}
