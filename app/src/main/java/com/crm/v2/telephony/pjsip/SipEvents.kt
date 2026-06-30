package com.crm.v2.telephony.pjsip

sealed interface SipEvent {
    data object RegistrationProgress : SipEvent
    data class RegistrationSuccess(val accountId: String) : SipEvent
    data class RegistrationFailed(val accountId: String, val statusCode: Int, val reason: String) : SipEvent
    data class CallIncoming(val callId: String, val callerUri: String, val callerName: String) : SipEvent
    data class CallOutgoing(val callId: String, val targetUri: String) : SipEvent
    data class CallConnected(val callId: String) : SipEvent
    data class CallDisconnected(val callId: String, val statusCode: Int, val reason: String) : SipEvent
    data class CallHold(val callId: String) : SipEvent
    data class CallUnhold(val callId: String) : SipEvent
    data class CallTransfer(val callId: String, val targetUri: String) : SipEvent
    data class CallMediaState(val callId: String, val active: Boolean) : SipEvent
    data class Error(val message: String) : SipEvent
}

sealed interface SipCallState {
    data object Idle : SipCallState
    data object Calling : SipCallState
    data object Ringing : SipCallState
    data object Connecting : SipCallState
    data object Connected : SipCallState
    data object OnHold : SipCallState
    data object Disconnecting : SipCallState
    data class Disconnected(val reason: String = "") : SipCallState

    val isActive: Boolean get() = this is Calling || this is Ringing || this is Connecting || this is Connected || this is OnHold
}
