package com.crm.v2.telephony.provider

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import com.crm.v2.common.constants.ApiConstants
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

    private val _callState = MutableStateFlow<CallState>(CallState.Idle)
    override val callState: Flow<CallState> = _callState.asStateFlow()

    private val _events = MutableSharedFlow<TelephonyEvent>(extraBufferCapacity = 10)
    override val events: Flow<TelephonyEvent> = _events.asSharedFlow()

    private val _registrationState = MutableStateFlow(false)
    override val registrationState: Flow<Boolean> = _registrationState.asStateFlow()

    private var currentCallId: Int = -1
    @Volatile private var isInitialized = false
    @Volatile private var isReRegistering = false
    private var pendingConfig: TelephonyConfig? = null
    private var networkCallback: ConnectivityManager.NetworkCallback? = null

    init {
        registerNetworkCallback()
    }

    override suspend fun register(config: TelephonyConfig) {
        if (isInitialized) return

        _callState.value = CallState.Registering
        Log.d(TAG, "Registering with PJSIP via UDP...")

        val callback = object : PjsipManager.Callback {
            override fun onRegState(accId: Int, code: Int, reason: String) {
                Log.i(TAG, "onRegState: accId=$accId, code=$code, reason=$reason")
                if (code in 200..299 || code == 0) {
                    _callState.value = CallState.Registered
                    _registrationState.value = true
                    Log.d(TAG, "SIP Registration successful")
                } else if (code == 0 && reason.isEmpty()) {
                    _callState.value = CallState.Registered
                    _registrationState.value = true
                    Log.d(TAG, "SIP Registration successful (code=0)")
                } else {
                    _callState.value = CallState.Error
                    _registrationState.value = false
                    _events.tryEmit(TelephonyEvent.RegistrationFailed("Registration failed: $code $reason"))
                    Log.e(TAG, "SIP Registration failed: $code $reason")
                }
            }

            override fun onCallState(callId: Int, state: Int, lastStatus: Int, reason: String) {
                Log.i(TAG, "onCallState: callId=$callId, state=$state, lastStatus=$lastStatus, reason=$reason")
                when (state) {
                    2, 3 -> {
                        _callState.value = CallState.Calling
                    }
                    4, 5 -> {
                        _callState.value = CallState.Ringing
                    }
                    6 -> {
                        if (lastStatus in 200..299 || lastStatus == 0) {
                            _callState.value = CallState.Connected
                            currentCallId = callId
                            _events.tryEmit(TelephonyEvent.CallConnected(callId.toString()))
                            Log.d(TAG, "Call connected")
                        } else {
                            Log.w(TAG, "Call state CONFIRMED but lastStatus=$lastStatus ($reason) — treating as disconnected")
                            _callState.value = CallState.Idle
                            _events.tryEmit(TelephonyEvent.CallDisconnected(callId.toString(), lastStatus))
                            currentCallId = -1
                        }
                    }
                    7 -> {
                        _callState.value = CallState.Idle
                        _events.tryEmit(TelephonyEvent.CallDisconnected(currentCallId.toString(), lastStatus))
                        currentCallId = -1
                        Log.d(TAG, "Call disconnected (status=$lastStatus reason=$reason)")
                    }
                    else -> {
                        Log.d(TAG, "Call state: $state, lastStatus: $lastStatus, reason: $reason")
                    }
                }
            }

            override fun onIncomingCall(accId: Int, callId: Int, remoteUri: String) {
                Log.i(TAG, "onIncomingCall: accId=$accId, callId=$callId, remoteUri=$remoteUri")
                _callState.value = CallState.Ringing
                currentCallId = callId
                _events.tryEmit(TelephonyEvent.IncomingCall(remoteUri, null))
            }

            override fun onCallMediaState(callId: Int) {
                Log.i(TAG, "onCallMediaState: callId=$callId — audio active")
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
        Log.d(TAG, "UDP transport result: $transportResult")

        val sipId = "sip:$sipUsername@$sipServer"
        val regUri = "sip:$sipServer:$sipPort"
        val accountId = pjsipManager.addAccount(sipId, regUri, sipUsername, sipPassword, sipRealm)
        Log.d(TAG, "Account added: status=$accountId")

        pendingConfig = config
        isInitialized = true
    }

    override suspend fun unregister() {
        unregisterNetworkCallback()
        pjsipManager.destroy()
        currentCallId = -1
        isInitialized = false
        _callState.value = CallState.Idle
        _registrationState.value = false
        Log.d(TAG, "Unregistered")
    }

    override suspend fun call(number: String) {
        if (!isInitialized) {
            _events.tryEmit(TelephonyEvent.Error("Not registered"))
            return
        }

        val sipServer = ApiConstants.Sip.SERVER
        val sipPort = ApiConstants.Sip.PORT.toIntOrNull() ?: 7065
        val callUri = "sip:$number@$sipServer:$sipPort"
        Log.d(TAG, "Calling $callUri")

        _callState.value = CallState.Calling
        val status = pjsipManager.makeCall(callUri)
        if (status != 0) {
            _callState.value = CallState.Error
            _events.tryEmit(TelephonyEvent.Error("Call failed: status=$status"))
            Log.e(TAG, "Make call failed: $status")
        }
    }

    override suspend fun answer() {
        if (currentCallId >= 0) {
            pjsipManager.answer(currentCallId, 200)
            Log.d(TAG, "Call answered")
        }
    }

    override suspend fun hangup() {
        if (currentCallId >= 0) {
            pjsipManager.hangup(currentCallId)
            Log.d(TAG, "Call hung up")
        }
        currentCallId = -1
        _callState.value = CallState.Idle
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

    private fun registerNetworkCallback() {
        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkRequest = NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    Log.i(TAG, "Network available — re-registering SIP")
                    if (isInitialized && _registrationState.value.not()) {
                        reRegister()
                    }
                }

                override fun onLost(network: Network) {
                    Log.w(TAG, "Network lost — SIP may be unreachable")
                    _registrationState.value = false
                }
            }

            connectivityManager.registerNetworkCallback(networkRequest, networkCallback!!)
            Log.d(TAG, "Network callback registered")
        } catch (e: Exception) {
            Log.e(TAG, "Failed to register network callback: ${e.message}")
        }
    }

    private fun unregisterNetworkCallback() {
        try {
            if (networkCallback != null) {
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                connectivityManager.unregisterNetworkCallback(networkCallback!!)
                networkCallback = null
                Log.d(TAG, "Network callback unregistered")
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to unregister network callback: ${e.message}")
        }
    }

    private fun reRegister() {
        val config = pendingConfig ?: return
        if (isReRegistering) {
            Log.d(TAG, "Re-registration already in progress, skipping")
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
            Log.i(TAG, "Re-registration initiated")
        } catch (e: Exception) {
            isReRegistering = false
            Log.e(TAG, "Re-registration failed: ${e.message}")
        }
    }
}
