package com.crm.v2.data.network.api

import com.crm.v2.data.network.dto.cloudconnect.Click2CallRequest
import com.crm.v2.data.network.dto.cloudconnect.Click2CallResponse
import com.crm.v2.data.network.dto.cloudconnect.CallLogRequest
import com.crm.v2.data.network.dto.cloudconnect.CallLogResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface CloudConnectApi {

    @POST
    suspend fun click2Call(
        @Url url: String,
        @Body request: Click2CallRequest,
    ): Response<Click2CallResponse>

    @POST
    suspend fun getCallLog(
        @Url url: String,
        @Body request: CallLogRequest,
    ): Response<CallLogResponse>
}
