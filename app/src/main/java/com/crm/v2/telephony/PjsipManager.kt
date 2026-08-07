package com.crm.v2.telephony

import android.util.Log

class PjsipManager {

    companion object {
        private const val TAG = "PjsipManager"
        private const val LIB_NAME = "pjsip_bridge"

        const val TRANSPORT_UDP = 0
        const val TRANSPORT_TLS = 1
        const val TRANSPORT_WSS = 2
        const val TRANSPORT_TCP = 3

        init {
            System.loadLibrary(LIB_NAME)
        }
    }

    interface Callback {
        fun onRegState(accId: Int, code: Int, reason: String)
        fun onCallState(callId: Int, state: Int, lastStatus: Int, reason: String)
        fun onIncomingCall(accId: Int, callId: Int, remoteUri: String)
        fun onCallMediaState(callId: Int)
    }

    private external fun nativeCreate(): Int
    private external fun nativeAddTransport(transportType: Int, server: String, port: Int): Int
    private external fun nativeAddAccount(uri: String, regUri: String, username: String, password: String, realm: String): Int
    private external fun nativeMakeCall(uri: String): Int
    private external fun nativeHangupCall(callId: Int): Int
    private external fun nativeAnswerCall(callId: Int, code: Int): Int
    private external fun nativeGetActiveCallId(): Int
    private external fun nativeSetCallback(callback: Callback)
    private external fun nativeSetMute(callId: Int, mute: Boolean): Int
    private external fun nativeDestroy()

    private var isCreated = false

    fun init(callback: Callback) {
        nativeSetCallback(callback)

        val result = nativeCreate()
        if (result == 0) {
            isCreated = true
            Log.i(TAG, "PJSIP created successfully")
        } else {
            Log.e(TAG, "PJSIP create failed: $result")
        }
    }

    fun addTransport(type: Int, server: String, port: Int): Int {
        if (!isCreated) return -1
        val result = nativeAddTransport(type, server, port)
        Log.i(TAG, "Add transport (type=$type, server=$server, port=$port): $result")
        return result
    }

    fun addAccount(sipUri: String, regUri: String, username: String, password: String, realm: String): Int {
        if (!isCreated) return -1
        val result = nativeAddAccount(sipUri, regUri, username, password, realm)
        Log.i(TAG, "Add account result: $result")
        return result
    }

    fun makeCall(sipUri: String): Int {
        if (!isCreated) return -1
        val result = nativeMakeCall(sipUri)
        Log.i(TAG, "Make call result: $result")
        return result
    }

    fun hangup(callId: Int): Int {
        if (!isCreated) return -1
        return nativeHangupCall(callId)
    }

    fun getActiveCallId(): Int {
        if (!isCreated) return -1
        return nativeGetActiveCallId()
    }

    fun answer(callId: Int, code: Int = 200): Int {
        if (!isCreated) return -1
        return nativeAnswerCall(callId, code)
    }

    fun setMute(callId: Int, mute: Boolean): Int {
        if (!isCreated) return -1
        val result = nativeSetMute(callId, mute)
        Log.i(TAG, "Set mute (callId=$callId, mute=$mute): $result")
        return result
    }

    fun destroy() {
        if (isCreated) {
            nativeDestroy()
            isCreated = false
            Log.i(TAG, "PJSIP destroyed")
        }
    }
}