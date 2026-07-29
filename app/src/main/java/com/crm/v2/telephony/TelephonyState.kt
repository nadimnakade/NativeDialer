package com.crm.v2.telephony

sealed interface CallState {
    data object Idle : CallState
    data object Registering : CallState
    data object Registered : CallState
    data object Calling : CallState
    data object Ringing : CallState
    data object Connected : CallState
    data object OnHold : CallState
    data object Error : CallState

    val isActive: Boolean get() = this is Calling || this is Ringing || this is Connected || this is OnHold
    val label: String get() = when (this) {
        Idle -> "Idle"
        Registering -> "Registering..."
        Registered -> "Registered"
        Calling -> "Calling..."
        Ringing -> "Ringing"
        Connected -> "Connected"
        OnHold -> "On Hold"
        Error -> "Error"
    }
}

sealed interface TelephonyEvent {
    data class IncomingCall(val number: String, val callerName: String?) : TelephonyEvent
    data class CallConnected(val callId: String) : TelephonyEvent
    data class CallDisconnected(val callId: String, val duration: Int) : TelephonyEvent
    data class RegistrationFailed(val message: String = "Registration failed") : TelephonyEvent
    data class Error(val message: String) : TelephonyEvent
}

data class TelephonyConfig(
    val providerType: ProviderType = ProviderType.CLOUD_CONNECT,
    val cloudTokenId: String = "",
    val cloudExtensionNumber: String = "",
    val cloudExtensionPassword: String = "",
    val sipServer: String = "",
    val sipUsername: String = "",
    val sipPassword: String = "",
)

enum class ProviderType {
    CLOUD_CONNECT,
    PJSIP,
    WEBRTC,
}
