package com.crm.v2.telephony.pjsip

import android.content.Context
import android.media.AudioManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.Flow
import org.pjsip.pjsua2.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PjsipManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    companion object {
        private const val TAG = "PjsipManager"

        init {
            try {
                System.loadLibrary("pjsua2")
                Log.d(TAG, "PJSIP native library loaded")
            } catch (e: UnsatisfiedLinkError) {
                Log.e(TAG, "Failed to load PJSIP native library", e)
            }
        }
    }

    private val _sipState = MutableStateFlow<SipCallState>(SipCallState.Idle)
    val sipState: Flow<SipCallState> = _sipState.asStateFlow()

    internal val _events = MutableSharedFlow<SipEvent>(extraBufferCapacity = 20)
    val events: Flow<SipEvent> = _events.asSharedFlow()

    private var ep: Endpoint? = null
    private var myAccount: MyAccount? = null
    private var currentCall: MyCall? = null
    private var isInitialized = false
    private var isRegistered = false

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    fun initialize(logLevel: Int = 3): Boolean {
        if (isInitialized) return true
        return try {
            ep = Endpoint()
            ep?.libCreate()

            val epCfg = EpConfig()
            epCfg.logConfig.level = logLevel

            val mediaCfg = epCfg.mediaConfig
            mediaCfg.clockRate = 16000
            mediaCfg.sndClockRate = 16000
            mediaCfg.channelCount = 1
            mediaCfg.audioFramePtTime = 20

            ep?.libInit(epCfg)

            val transportCfg = TransportConfig()
            transportCfg.port = 0
            try {
                ep?.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_UDP, transportCfg)
            } catch (_: Exception) {
                Log.w(TAG, "UDP failed, trying TCP")
                ep?.transportCreate(pjsip_transport_type_e.PJSIP_TRANSPORT_TCP, transportCfg)
            }

            ep?.libStart()
            isInitialized = true
            Log.d(TAG, "PJSIP initialized successfully")
            _events.tryEmit(SipEvent.RegistrationProgress)
            true
        } catch (e: Exception) {
            Log.e(TAG, "PJSIP init exception", e)
            _events.tryEmit(SipEvent.Error("PJSIP init exception: ${e.message}"))
            false
        }
    }

    fun register(config: SipAccountConfig): Boolean {
        if (!isInitialized) {
            Log.e(TAG, "Cannot register: PJSIP not initialized")
            return false
        }
        return try {
            myAccount = MyAccount(this)

            val accCfg = AccountConfig()
            val registrar = config.registrarUri.removePrefix("sip:")
            accCfg.idUri = "sip:${config.username}@$registrar"
            accCfg.regConfig.registrarUri = config.registrarUri

            val creds = accCfg.sipConfig.authCreds
            creds.clear()
            if (config.username.isNotEmpty()) {
                creds.add(AuthCredInfo("Digest", "*", config.username, 0, config.password))
            }

            val proxies = accCfg.sipConfig.proxies
            proxies.clear()
            if (config.proxyUri.isNotEmpty()) {
                proxies.add(config.proxyUri)
            }

            accCfg.natConfig.iceEnabled = config.iceEnabled

            myAccount?.create(accCfg)
            Thread.sleep(500)
            isRegistered = true
            _events.tryEmit(SipEvent.RegistrationSuccess(config.registrarUri))
            true
        } catch (e: Exception) {
            Log.e(TAG, "SIP registration exception", e)
            _events.tryEmit(SipEvent.Error("Registration exception: ${e.message}"))
            false
        }
    }

    fun unregister() {
        try {
            myAccount?.shutdown()
            myAccount = null
            isRegistered = false
            Log.d(TAG, "SIP unregistered")
        } catch (e: Exception) {
            Log.e(TAG, "SIP unregister exception", e)
        }
    }

    fun destroy() {
        if (!isInitialized) return
        try {
            unregister()
            currentCall?.delete()
            currentCall = null
            ep?.libDestroy()
            ep = null
            isInitialized = false
            Log.d(TAG, "PJSIP destroyed")
        } catch (e: Exception) {
            Log.e(TAG, "PJSIP destroy exception", e)
        }
    }

    fun makeCall(targetUri: String) {
        if (!isRegistered) {
            _events.tryEmit(SipEvent.Error("Not registered"))
            return
        }
        _sipState.value = SipCallState.Calling
        val callId = currentCall?.info()?.callIdString ?: System.currentTimeMillis().toString()
        _events.tryEmit(SipEvent.CallOutgoing(callId, targetUri))
        try {
            val account = myAccount ?: return
            if (currentCall != null) {
                Log.w(TAG, "Call already in progress, hanging up first")
                hangup()
            }
            currentCall = MyCall(account, -1, this)
            val prm = CallOpParam(true)
            currentCall?.makeCall(targetUri, prm)
        } catch (e: Exception) {
            _sipState.value = SipCallState.Idle
            _events.tryEmit(SipEvent.Error("Make call exception: ${e.message}"))
            Log.e(TAG, "Make call exception", e)
        }
    }

    fun answer() {
        try {
            val prm = CallOpParam()
            prm.statusCode = pjsip_status_code.PJSIP_SC_OK
            currentCall?.answer(prm)
        } catch (e: Exception) {
            Log.e(TAG, "Answer exception", e)
        }
    }

    fun hangup() {
        _sipState.value = SipCallState.Disconnecting
        try {
            val prm = CallOpParam()
            prm.statusCode = pjsip_status_code.PJSIP_SC_DECLINE
            currentCall?.hangup(prm)
            currentCall = null
            _sipState.value = SipCallState.Idle
        } catch (e: Exception) {
            _sipState.value = SipCallState.Idle
            Log.e(TAG, "Hangup exception", e)
        }
    }

    fun hold() {
        try {
            val prm = CallOpParam(true)
            currentCall?.setHold(prm)
            _sipState.value = SipCallState.OnHold
        } catch (e: Exception) {
            Log.e(TAG, "Hold exception", e)
        }
    }

    fun resume() {
        try {
            currentCall?.reinvite(CallOpParam(true))
            _sipState.value = SipCallState.Connected
        } catch (e: Exception) {
            Log.e(TAG, "Resume exception", e)
        }
    }

    fun transfer(targetUri: String) {
        try {
            val transferString = if (targetUri.startsWith("sip:")) "<$targetUri>" else "<sip:$targetUri>"
            val prm = CallOpParam(true)
            currentCall?.xfer(transferString, prm)
            _events.tryEmit(SipEvent.CallTransfer("current", targetUri))
        } catch (e: Exception) {
            Log.e(TAG, "Transfer exception", e)
        }
    }

    fun mute(muted: Boolean) {
        try {
            if (muted) audioManager.adjustStreamVolume(
                AudioManager.STREAM_VOICE_CALL, AudioManager.ADJUST_MUTE, 0
            ) else audioManager.adjustStreamVolume(
                AudioManager.STREAM_VOICE_CALL, AudioManager.ADJUST_UNMUTE, 0
            )
        } catch (e: Exception) {
            Log.e(TAG, "Mute exception", e)
        }
    }

    fun setSpeaker(on: Boolean) {
        try {
            audioManager.isSpeakerphoneOn = on
        } catch (e: Exception) {
            Log.e(TAG, "Speaker exception", e)
        }
    }

    fun setBluetooth(on: Boolean) {
        try {
            audioManager.mode = if (on) AudioManager.MODE_IN_COMMUNICATION else AudioManager.MODE_NORMAL
        } catch (e: Exception) {
            Log.e(TAG, "Bluetooth exception", e)
        }
    }

    fun isRegistered(): Boolean = isRegistered
    fun isInitialized(): Boolean = isInitialized

    fun formatSipUri(number: String, server: String) = "sip:$number@$server"

    internal fun onCallState(call: MyCall, ci: CallInfo) {
        val callId = ci.callIdString ?: call.hashCode().toString()
        when (ci.state) {
            pjsip_inv_state.PJSIP_INV_STATE_CALLING -> _sipState.value = SipCallState.Calling
            pjsip_inv_state.PJSIP_INV_STATE_CONNECTING -> _sipState.value = SipCallState.Connecting
            pjsip_inv_state.PJSIP_INV_STATE_CONFIRMED -> {
                _sipState.value = SipCallState.Connected
                _events.tryEmit(SipEvent.CallConnected(callId))
            }
            pjsip_inv_state.PJSIP_INV_STATE_DISCONNECTED -> {
                val reason = ci.connectDuration.sec.toString()
                _sipState.value = SipCallState.Disconnected(reason)
                _events.tryEmit(SipEvent.CallDisconnected(callId, 0, reason))
                currentCall = null
            }
            else -> {}
        }
    }

    fun onRegState(accountId: String, statusCode: Int) {
        if (statusCode / 100 == 2) {
            isRegistered = true
            _events.tryEmit(SipEvent.RegistrationSuccess(accountId))
        } else {
            isRegistered = false
            _events.tryEmit(SipEvent.RegistrationFailed(accountId, statusCode, ""))
        }
    }
}

internal class MyAccount(
    private val manager: PjsipManager,
) : Account() {
    override fun onRegState(prm: OnRegStateParam) {
        Log.d("MyAccount", "Reg state: ${prm.code} ${prm.reason}")
        manager.onRegState(prm.accountId, prm.code)
    }

    override fun onIncomingCall(prm: OnIncomingCallParam) {
        try {
            val call = MyCall(this, prm.callId, manager)
            val ci = call.info()
            Log.d("MyAccount", "Incoming call: ${ci.remoteUri}")
            manager.onCallState(call, ci)
        } catch (e: Exception) {
            Log.e("MyAccount", "Incoming call exception", e)
        }
    }
}

internal class MyCall(
    account: MyAccount,
    callId: Int,
    private val manager: PjsipManager,
) : Call(account, callId) {
    override fun onCallState(prm: OnCallStateParam) {
        try {
            manager.onCallState(this, info())
        } catch (e: Exception) {
            Log.e("MyCall", "onCallState exception", e)
        }
    }

    override fun onCallMediaState(prm: OnCallMediaStateParam) {
        try {
            val ci = info()
            for (i in 0 until ci.media.size().toInt()) {
                if (ci.media[i].type == pjmedia_type.PJMEDIA_TYPE_AUDIO) {
                    manager._events.tryEmit(SipEvent.CallMediaState(ci.callIdString, true))
                    break
                }
            }
        } catch (e: Exception) {
            Log.e("MyCall", "onCallMediaState exception", e)
        }
    }
}
