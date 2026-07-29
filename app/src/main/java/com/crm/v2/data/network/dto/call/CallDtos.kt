package com.crm.v2.data.network.dto.call

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceCallRequest(
    @SerialName("phone_number") val phoneNumber: String,
    @SerialName("user_key") val userKey: String,
)

@Serializable
data class PlaceCallResponse(
    @SerialName("success") val success: Boolean = false,
    @SerialName("call_id") val callId: String = "",
    @SerialName("message") val message: String = "",
)

@Serializable
data class HangupCallRequest(
    @SerialName("call_id") val callId: String,
    @SerialName("user_key") val userKey: String,
)
