package com.crm.v2.telephony.pjsip

import android.content.Context
import android.media.AudioManager
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _events = MutableSharedFlow<SipEvent>(extraBufferCapacity = 20)
    val events: Flow<SipEvent> = _events.asSharedFlow()

    private var isInitialized = false
    private var isRegistered = false

    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private external fun nativeInit(logLevel: Int): Int
    private external fun nativeDestroy(): Int
    private external fun nativeRegister(
        accountId: String,
        registrarUri: String,
        username: String,
        password: String,
        realm: String,
        proxyUri: String,
        iceEnabled: Boolean,
    ): Int
    private external fun nativeUnregister(): Int
    private external fun nativeMakeCall(targetUri: String): Int
    private external fun nativeHangup(): Int
    private external fun nativeHold(): Int
    private external fun nativeResume(): Int
    private external fun nativeTransfer(targetUri: String): Int
    private external fun nativeAnswer(): Int
    private external fun nativeMute(mute: Boolean): Int
    private external fun nativeSetSpeaker(on: Boolean): Int

    fun initialize(logLevel: Int = 3): Boolean {
        if (isInitialized) return true
        return try {
            val result = nativeInit(logLevel)
            isInitialized = result == 0
            if (isInitialized) {
                Log.d(TAG, "PJSIP initialized successfully")
                _events.tryEmit(SipEvent.RegistrationProgress)
            } else {
                Log.e(TAG, "PJSIP init failed with code: $result")
                _events.tryEmit(SipEvent.Error("PJSIP init failed: $result"))
            }
            isInitialized
        } catch (e: Exception) {
            Log.e(TAG, "PJSIP init exception", e)
            _events.tryEmit(SipEvent.Error("PJSIP init exception: ${e.message}"))
            false
        }
    }

    fun destroy() {
        if (!isInitialized) return
        try {
            nativeUnregister()
            nativeDestroy()
            isInitialized = false
            isRegistered = false
            Log.d(TAG, "PJSIP destroyed")
        } catch (e: Exception) {
            Log.e(TAG, "PJSIP destroy exception", e)
        }
    }

    fun register(config: SipAccountConfig): Boolean {
        if (!isInitialized) {
            Log.e(TAG, "Cannot register: PJSIP not initialized")
            return false
        }
        return try {
            Log.d(TAG, "Registering SIP: ${config.username}@${config.registrarUri}")
            val result = nativeRegister(
                accountId = config.accountId,
                registrarUri = config.registrarUri,
                username = config.username,
                password = config.password,
                realm = config.realm,
                proxyUri = config.proxyUri,
                iceEnabled = config.iceEnabled,
            )
            if (result == 0) {
                isRegistered = true
                Log.d(TAG, "SIP registration sent")
                true
            } else {
                Log.e(TAG, "SIP registration failed: $result")
                _events.tryEmit(SipEvent.Error("Registration failed: $result"))
                false
            }
        } catch (e: Exception) {
            Log.e(TAG, "SIP registration exception", e)
            _events.tryEmit(SipEvent.Error("Registration exception: ${e.message}"))
            false
        }
    }

    fun unregister() {
        if (!isInitialized || !isRegistered) return
        try {
            nativeUnregister()
            isRegistered = false
            Log.d(TAG, "SIP unregistered")
        } catch (e: Exception) {
            Log.e(TAG, "SIP unregister exception", e)
        }
    }

    fun makeCall(targetUri: String) {
        if (!isRegistered) {
            _events.tryEmit(SipEvent.Error("Not registered"))
            return
        }
        _sipState.value = SipCallState.Calling
        _events.tryEmit(SipEvent.CallOutgoing(callId = System.currentTimeMillis().toString(), targetUri))
        try {
            val result = nativeMakeCall(targetUri)
            if (result != 0) {
                _sipState.value = SipCallState.Idle
                _events.tryEmit(SipEvent.Error("Make call failed: $result"))
                Log.e(TAG, "Make call failed: $result")
            }
        } catch (e: Exception) {
            _sipState.value = SipCallState.Idle
            _events.tryEmit(SipEvent.Error("Make call exception: ${e.message}"))
            Log.e(TAG, "Make call exception", e)
        }
    }

    fun answer() {
        try {
            nativeAnswer()
            Log.d(TAG, "Call answered")
        } catch (e: Exception) {
            Log.e(TAG, "Answer exception", e)
        }
    }

    fun hangup() {
        val prevState = _sipState.value
        _sipState.value = SipCallState.Disconnecting
        try {
            nativeHangup()
            _sipState.value = SipCallState.Idle
            Log.d(TAG, "Call hung up")
        } catch (e: Exception) {
            _sipState.value = prevState
            Log.e(TAG, "Hangup exception", e)
        }
    }

    fun hold() {
        try {
            val result = nativeHold()
            if (result == 0) {
                _sipState.value = SipCallState.OnHold
                Log.d(TAG, "Call on hold")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Hold exception", e)
        }
    }

    fun resume() {
        try {
            val result = nativeResume()
            if (result == 0) {
                _sipState.value = SipCallState.Connected
                Log.d(TAG, "Call resumed")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Resume exception", e)
        }
    }

    fun transfer(targetUri: String) {
        try {
            nativeTransfer(targetUri)
            _events.tryEmit(SipEvent.CallTransfer(callId = "current", targetUri))
            Log.d(TAG, "Call transferred to $targetUri")
        } catch (e: Exception) {
            Log.e(TAG, "Transfer exception", e)
        }
    }

    fun mute(muted: Boolean) {
        try {
            nativeMute(muted)
            Log.d(TAG, "Mute: $muted")
        } catch (e: Exception) {
            Log.e(TAG, "Mute exception", e)
        }
    }

    fun setSpeaker(on: Boolean) {
        try {
            nativeSetSpeaker(on)
            audioManager.isSpeakerphoneOn = on
            Log.d(TAG, "Speaker: $on")
        } catch (e: Exception) {
            Log.e(TAG, "Speaker exception", e)
        }
    }

    fun setBluetooth(on: Boolean) {
        try {
            val mode = if (on) AudioManager.MODE_IN_COMMUNICATION else AudioManager.MODE_NORMAL
            audioManager.mode = mode
            Log.d(TAG, "Bluetooth: $on")
        } catch (e: Exception) {
            Log.e(TAG, "Bluetooth exception", e)
        }
    }

    fun isRegistered(): Boolean = isRegistered

    fun isInitialized(): Boolean = isInitialized

    fun formatSipUri(number: String, server: String): String {
        return "sip:$number@$server"
    }

    fun onNativeEvent(eventType: String, callId: String, statusCode: Int, reason: String) {
        Log.d(TAG, "Native event: $eventType callId=$callId status=$statusCode reason=$reason")
        when (eventType) {
            "on_registration_state" -> {
                if (statusCode / 100 == 2) {
                    isRegistered = true
                    _events.tryEmit(SipEvent.RegistrationSuccess(callId))
                    Log.d(TAG, "SIP registration successful")
                } else {
                    isRegistered = false
                    _events.tryEmit(SipEvent.RegistrationFailed(callId, statusCode, reason))
                    Log.e(TAG, "SIP registration failed: $statusCode $reason")
                }
            }
            "on_incoming_call" -> {
                _sipState.value = SipCallState.Ringing
                _events.tryEmit(SipEvent.CallIncoming(callId, reason, ""))
            }
            "on_call_state" -> {
                when (statusCode) {
                    180, 183 -> {
                        _sipState.value = SipCallState.Ringing
                    }
                    200 -> {
                        _sipState.value = SipCallState.Connected
                        _events.tryEmit(SipEvent.CallConnected(callId))
                    }
                    in 400..699 -> {
                        _sipState.value = SipCallState.Disconnected(reason)
                        _events.tryEmit(SipEvent.CallDisconnected(callId, statusCode, reason))
                    }
                    else -> {
                        Log.d(TAG, "Call state: $statusCode $reason")
                    }
                }
            }
            "on_call_media_state" -> {
                _events.tryEmit(SipEvent.CallMediaState(callId, statusCode == 0))
            }
        }
    }
}
