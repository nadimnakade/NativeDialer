package com.crm.v2.telephony.provider

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.crm.v2.telephony.CallState
import com.crm.v2.telephony.TelephonyEvent
import kotlinx.coroutines.flow.MutableSharedFlow

object CloudConnectEvents {
    val events = MutableSharedFlow<TelephonyEvent>(extraBufferCapacity = 10)
    var currentCallState: CallState = CallState.Idle
        private set

    fun updateState(state: CallState) {
        currentCallState = state
    }
}

class CloudConnectWebhookReceiver : BroadcastReceiver() {

    companion object {
        private const val TAG = "CloudConnectWebhook"

        const val ACTION_CALL_EVENT = "com.crm.v2.CALL_EVENT"
        const val EXTRA_UNIQUE_TOKEN = "unique_token"
        const val EXTRA_UUID = "uuid"
        const val EXTRA_EXTENSION_NUMBER = "extension_number"
        const val EXTRA_CALLER_NAME = "caller_name"
        const val EXTRA_CALLER_NUMBER = "caller_number"
        const val EXTRA_CALL_STATUS = "call_status"
        const val EXTRA_CALL_DIRECTION = "call_direction"
        const val EXTRA_CALL_DURATION = "call_duration"
        const val EXTRA_CALL_ANSWER_TIME = "call_answer_time"
        const val EXTRA_REASON = "reason"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val callStatus = intent.getStringExtra(EXTRA_CALL_STATUS) ?: return
        val callerNumber = intent.getStringExtra(EXTRA_CALLER_NUMBER) ?: ""
        val callerName = intent.getStringExtra(EXTRA_CALLER_NAME) ?: ""
        val duration = intent.getIntExtra(EXTRA_CALL_DURATION, 0)
        val uniqueToken = intent.getStringExtra(EXTRA_UNIQUE_TOKEN) ?: ""

        Log.d(TAG, "Webhook received: status=$callStatus, caller=$callerNumber, duration=$duration")

        when (callStatus.lowercase()) {
            "ring" -> {
                CloudConnectEvents.updateState(CallState.Ringing)
                CloudConnectEvents.events.tryEmit(
                    TelephonyEvent.IncomingCall(callerNumber, callerName)
                )
            }
            "answer" -> {
                CloudConnectEvents.updateState(CallState.Connected)
                CloudConnectEvents.events.tryEmit(
                    TelephonyEvent.CallConnected(uniqueToken)
                )
            }
            "hangup" -> {
                CloudConnectEvents.updateState(CallState.Idle)
                CloudConnectEvents.events.tryEmit(
                    TelephonyEvent.CallDisconnected(uniqueToken, duration)
                )
            }
        }
    }
}
