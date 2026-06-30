package com.crm.v2.data.network.dto.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    @SerialName("Key") val userKey: Int = 0,
    @SerialName("EmployeeName") val employeeName: String = "",
    @SerialName("DesignationDesc") val designation: String? = null,
    @SerialName("RoleDesc") val role: String? = null,
    @SerialName("Token") val token: String = "",
    @SerialName("Expiration") val expiration: String = "",
    @SerialName("UserName") val userName: String = "",
    @SerialName("MobileNo1") val mobileNo: Long = 0,
    @SerialName("PhotoURL") val photoUrl: String? = null,
)

@Serializable
data class UserStatusDto(
    @SerialName("isActive") val isActive: Boolean = true,
)
