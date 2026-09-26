package com.crm.v2.data.network.api

import com.crm.v2.common.constants.ApiConstants
import com.crm.v2.data.network.dto.pickup.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface PickupApi {

    // Dashboard
    @GET(ApiConstants.Pickup.DASHBOARD_COUNTER)
    suspend fun getDashboardCounter(@Query("userKey") userKey: String): Response<List<DashboardCounterDto>>

    @GET(ApiConstants.Pickup.DIALER_LIVE)
    suspend fun getDialerLive(@Query("userKey") userKey: String): Response<List<DialerLiveDto>>

    @GET(ApiConstants.Pickup.DIALER_LIVE_INDV)
    suspend fun getDialerLiveIndv(@Query("userKey") userKey: String): Response<List<DialerLiveDto>>

    @GET(ApiConstants.Pickup.LIVE_COUNT)
    suspend fun getLiveCount(@Query("userKey") userKey: String): Response<List<CallLogDto>>

    @GET(ApiConstants.Pickup.TEAM_CALL_LOG)
    suspend fun getTeamCallLog(@Query("userKey") userKey: String): Response<List<CallLogDto>>

    @GET(ApiConstants.Pickup.ATTENDANCE)
    suspend fun getAttendance(@Query("UserKey") userKey: String): Response<AttendanceDto>

    @GET(ApiConstants.Pickup.INCENTIVE)
    suspend fun getIncentive(@Query("userKey") userKey: String): Response<ResponseBody>

    @GET(ApiConstants.Pickup.APPLICATIONS)
    suspend fun getApplications(@Query("userKey") userKey: String): Response<ResponseBody>

    // Pickup
    @GET(ApiConstants.Pickup.GET_DATA)
    suspend fun getPickupData(@Query("userKey") userKey: String): Response<List<PickupEntryDto>>

    @GET(ApiConstants.Pickup.GET_ENTRY_DATA)
    suspend fun getPickupEntryData(
        @Query("userKey") userKey: String,
        @Query("type") type: String? = null,
    ): Response<List<PickupEntryDto>>

    @POST(ApiConstants.Pickup.UPDATE_STATUS)
    suspend fun updateStatus(@Body request: UpdateStatusRequest): Response<ResponseBody>

    // Dialer
    @GET(ApiConstants.Dialer.GET_DATA)
    suspend fun getDialerData(@Query("userKey") userKey: String): Response<List<DialerDataDto>>

    @GET(ApiConstants.Dialer.GET_NOT_UPDATED)
    suspend fun getDialerNotUpdated(@Query("userKey") userKey: String): Response<List<DialerDataDto>>

    @GET(ApiConstants.Dialer.UPDATE_STATUS)
    suspend fun updateDialingStatus(@Query("Key") key: String): Response<ResponseBody>

    @GET(ApiConstants.Dialer.UPDATE)
    suspend fun updateDialer(
        @Query("Key") key: Int,
        @Query("Status") status: String,
        @Query("Remarks") remarks: String,
        @Query("CallBackDate") callbackDate: String,
        @Query("CallBackTime") callbackTime: String,
        @Query("CallDuration") callDuration: String,
        @Query("CallDate") callDate: String,
        @Query("UserKey") userKey: Int,
        @Query("IsHLLAPBL") isHllapbl: Int,
        @Query("IsCibilBad") isCibilBad: Int,
        @Query("IsRealEstate") isRealEstate: Int,
    ): Response<ResponseBody>

    // Leads
    @GET(ApiConstants.Dialer.GET_LEAD)
    suspend fun getLeads(
        @Query("userKey") userKey: String,
        @Query("pageIndex") pageIndex: Int = 0,
    ): Response<List<LeadDto>>

    @GET(ApiConstants.Dialer.INSERT_LEAD)
    suspend fun insertLead(
        @Query("Name") name: String,
        @Query("MobileNo") mobileNo: String,
        @Query("Product") product: String,
        @Query("CallerID") callerId: String,
        @Query("Remarks") remarks: String,
        @Query("UserKey") userKey: String,
    ): Response<ResponseBody>

    @GET(ApiConstants.Dialer.ADD_LEAD)
    suspend fun addLead(
        @Query("CD_CLIENT_NAME") clientName: String,
        @Query("CD_CONTACT") contact: String,
        @Query("CD_SALARY") salary: String,
        @Query("CD_COMPANY") company: String,
        @Query("CD_REMARKS") remarks: String,
        @Query("CD_STATUS") status: String,
        @Query("CD_CALLBACK_DATE") callbackDate: String,
        @Query("CD_CALLBACK_TIME") callbackTime: String,
        @Query("CD_CALLER_DURATION") callerDuration: String,
        @Query("CD_CALL_DATE_TIME") callDateTime: String,
        @Query("UserKey") userKey: String,
        @Query("IsHLLAPBL") isHllapbl: Int,
        @Query("IsCibilBad") isCibilBad: Int,
        @Query("IsRealEstate") isRealEstate: Int,
    ): Response<ResponseBody>

    @GET(ApiConstants.Dialer.ADD_LEAD_NEW)
    suspend fun addLeadNew(
        @Query("CD_CLIENT_NAME") clientName: String,
        @Query("CD_CONTACT") contact: String,
        @Query("CD_SALARY") salary: String,
        @Query("CD_COMPANY") company: String,
        @Query("CD_REMARKS") remarks: String,
        @Query("CD_STATUS") status: String,
        @Query("CD_CALLBACK_DATE") callbackDate: String,
        @Query("CD_CALLBACK_TIME") callbackTime: String,
        @Query("CD_CALLER_DURATION") callerDuration: String,
        @Query("CD_CALL_DATE_TIME") callDateTime: String,
        @Query("UserKey") userKey: String,
        @Query("IsHLLAPBL") isHllapbl: Int,
        @Query("IsCibilBad") isCibilBad: Int,
        @Query("IsRealEstate") isRealEstate: Int,
    ): Response<ResponseBody>

    @POST(ApiConstants.Dialer.MANAGE_LEAD)
    suspend fun manageLead(@Body body: Map<String, String>): Response<ResponseBody>

    @GET(ApiConstants.Dialer.LEAD_COUNT)
    suspend fun getLeadCount(
        @Query("userKey") userKey: String,
        @Query("type") type: String? = null,
    ): Response<LeadCountDto>

    @GET(ApiConstants.Dialer.LEAD_FOLLOWUP)
    suspend fun getLeadFollowup(
        @Query("userKey") userKey: String,
        @Query("type") type: String? = null,
    ): Response<List<DialerDataDto>>

    @GET("Pickup/GetCallerData")
    suspend fun getCallerData(
        @Query("userKey") userKey: String,
        @Query("type") type: String? = null,
        @Query("pageIndex") pageIndex: Int = 0,
    ): Response<List<DialerDataDto>>

    // Call Logs
    @POST(ApiConstants.CallLog.SAVE)
    suspend fun saveCallLogs(@Body body: List<CallLogDto>): Response<ResponseBody>

    @POST(ApiConstants.CallLog.SAVE_ALL)
    suspend fun saveCallLogsAll(@Body body: List<CallLogDto>): Response<ResponseBody>

    @POST(ApiConstants.CallLog.SAVE_INDV)
    suspend fun saveCallLogIndv(@Body body: CallLogDto): Response<ResponseBody>

    // Daily Login
    @GET(ApiConstants.DailyLogin.INSERT)
    suspend fun insertDailyLogin(
        @Query("Product") product: String,
        @Query("Banks") banks: String,
        @Query("LOSNo") losNo: String,
        @Query("AppNo") appNo: String,
        @Query("Region") region: String,
        @Query("ClientName") clientName: String,
        @Query("Contact") contact: String,
        @Query("LoginAmt") loginAmt: String,
        @Query("Remarks") remarks: String,
        @Query("UserKey") userKey: String,
    ): Response<ResponseBody>

    @GET(ApiConstants.DailyLogin.LIST)
    suspend fun getDailyLoginList(
        @Query("UserKey") userKey: String,
        @Query("SearchText") searchText: String = "",
    ): Response<List<DailyLoginDto>>

    // Documents
    @POST(ApiConstants.Documents.SAVE)
    @Multipart
    suspend fun saveDocument(
        @Part file: MultipartBody.Part,
        @Part("FileName") fileName: okhttp3.RequestBody,
        @Part("Name") name: okhttp3.RequestBody,
        @Part("Caller") caller: okhttp3.RequestBody,
        @Part("MobileNo") mobileNo: okhttp3.RequestBody,
        @Part("FileType") fileType: okhttp3.RequestBody,
    ): Response<ResponseBody>

    @GET(ApiConstants.Documents.GET_CLIENTS)
    suspend fun getCallerClients(
        @Query("userKey") userKey: String,
        @Query("mobileNo") mobileNo: String,
    ): Response<List<CallerClientDto>>

    @GET(ApiConstants.Documents.GET_DOCUMENTS)
    suspend fun getClientDocuments(
        @Query("userKey") userKey: String,
        @Query("mobileNo") mobileNo: String,
    ): Response<List<DocumentDto>>

    // Email
    @POST(ApiConstants.Email.SEND)
    suspend fun sendEmail(@Body request: SendEmailRequest): Response<ResponseBody>

    // Call Recording
    @POST(ApiConstants.CallLog.SAVE_RECORDING)
    @Multipart
    suspend fun saveRecording(
        @Part file: MultipartBody.Part,
        @Part("FileName") fileName: okhttp3.RequestBody,
        @Part("Caller") caller: okhttp3.RequestBody,
        @Part("CD_KEY") cdKey: okhttp3.RequestBody,
        @Part("CD_CONTACT") cdContact: okhttp3.RequestBody,
    ): Response<ResponseBody>
}
