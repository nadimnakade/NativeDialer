package com.crm.v2.data.network.api

import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.data.network.dto.auth.LoginResponseDto
import com.crm.v2.data.network.dto.auth.UserStatusDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AuthApi {

    @GET(ApiConstants.Auth.LOGIN)
    suspend fun login(
        @Query("UserName") userName: String,
        @Query("Password") password: String,
        @Query("UUID") uuid: String,
        @Query("Type") type: String = "asdfasdfasdf",
    ): Response<List<LoginResponseDto>>

    @GET(ApiConstants.Auth.LOGIN_MOBILE)
    suspend fun loginMobile(
        @Query("MobileNo") mobileNo: String,
        @Query("UDID") udid: String,
        @Query("Type") type: String = "asdfasdfasdf",
    ): Response<List<LoginResponseDto>>

    @GET(ApiConstants.Auth.USER_STATUS)
    suspend fun getUserStatus(@Query("userKey") userKey: String): Response<UserStatusDto>
}
