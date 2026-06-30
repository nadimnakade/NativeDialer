package com.crm.v2.data.network.dto.cloudconnect

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Click2CallRequest(
    @SerialName("token_id") val tokenId: String,
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("extension_number") val extensionNumber: String,
    @SerialName("extension_password") val extensionPassword: String,
)

@Serializable
data class Click2CallResponse(
    @SerialName("status") val status: String = "",
    @SerialName("message") val message: String = "",
    @SerialName("data") val data: String = "",
    @SerialName("unique_token") val uniqueToken: String = "",
    @SerialName("code") val code: String = "",
    @SerialName("status_reason") val statusReason: String = "",
)

@Serializable
data class CallLogRequest(
    @SerialName("token_id") val tokenId: String,
    @SerialName("unique_token") val uniqueToken: String? = null,
)

@Serializable
data class CallLogResponse(
    @SerialName("status") val status: String = "",
    @SerialName("message") val message: String = "",
    @SerialName("data") val data: List<CallLogEntry> = emptyList(),
)

@Serializable
data class CallLogEntry(
    @SerialName("callid") val callId: String = "",
    @SerialName("tenant_id") val tenantId: String = "",
    @SerialName("start_date") val startDate: String = "",
    @SerialName("answer_time") val answerTime: String = "",
    @SerialName("end_date") val endDate: String = "",
    @SerialName("caller") val caller: String = "",
    @SerialName("callee") val callee: String = "",
    @SerialName("forward") val forward: String = "",
    @SerialName("call_direction") val callDirection: String = "",
    @SerialName("did") val did: String = "",
    @SerialName("call_sec") val callSec: String = "",
    @SerialName("call_minute") val callMinute: String = "",
    @SerialName("answer_sec") val answerSec: String = "",
    @SerialName("answer_minute") val answerMinute: String = "",
    @SerialName("call_status") val callStatus: String = "",
    @SerialName("hangup_by") val hangupBy: String = "",
    @SerialName("hangup_reason") val hangupReason: String = "",
    @SerialName("incoming_call_charges") val incomingCallCharges: String = "",
    @SerialName("outgoing_call_charges") val outgoingCallCharges: String = "",
    @SerialName("call_rec_path") val callRecordingPath: String = "",
)
