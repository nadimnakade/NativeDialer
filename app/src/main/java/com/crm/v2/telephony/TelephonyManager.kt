package com.crm.v2.telephony

import android.content.Context
import kotlinx.coroutines.flow.Flow

interface TelephonyManager {

    val callState: Flow<CallState>
    val events: Flow<TelephonyEvent>
    val registrationState: Flow<Boolean>

    suspend fun register(config: TelephonyConfig)
    suspend fun unregister()

    suspend fun call(number: String)
    suspend fun answer()
    suspend fun hangup()

    suspend fun hold()
    suspend fun resume()

    suspend fun transfer(number: String)
    suspend fun conference(number: String)

    suspend fun mute(enabled: Boolean)
    suspend fun speaker(enabled: Boolean)
    suspend fun bluetooth(enabled: Boolean)

    fun getCurrentState(): CallState
}
