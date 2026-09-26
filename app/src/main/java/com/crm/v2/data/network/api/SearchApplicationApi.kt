package com.crm.v2.data.network.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApplicationApi {

    @GET("SearchApplication.ashx")
    suspend fun searchApplications(
        @Query("UserKey") userKey: String,
        @Query("iDisplayStart") displayStart: Int = 0,
        @Query("iDisplayLength") displayLength: Int = 10,
        @Query("iSortCol_0") sortCol: Int = 0,
        @Query("sSortDir_0") sortDir: String = "asc",
        @Query("sSearch") search: String = "",
    ): Response<ResponseBody>
}
