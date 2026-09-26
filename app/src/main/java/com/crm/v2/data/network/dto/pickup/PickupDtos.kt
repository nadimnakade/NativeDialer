package com.crm.v2.data.network.dto.pickup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardCounterDto(
    @SerialName("Rank") val rank: String? = null,
    @SerialName("Points") val points: String? = null,
    @SerialName("TotalDisbursedAmt") val totalDisbursedAmt: String? = null,
    @SerialName("DisbursedAmt") val disbursedAmt: String? = null,
    @SerialName("ClientConnected") val clientConnected: String? = null,
    @SerialName("Duration") val duration: String? = null,
    @SerialName("DailyLeadCount") val dailyLeadCount: String? = null,
    @SerialName("MonthlyLeadCount") val monthlyLeadCount: String? = null,
    @SerialName("MonthlyLoginCount") val monthlyLoginCount: String? = null,
    @SerialName("MonthlyLoginAmt") val monthlyLoginAmt: String? = null,
    @SerialName("DailyLoginCount") val dailyLoginCount: String? = null,
    @SerialName("DailyLoginAmt") val dailyLoginAmt: String? = null,
    @SerialName("Incentive") val incentive: String? = null,
    @SerialName("PL") val pl: String? = null,
    @SerialName("HL") val hl: String? = null,
    @SerialName("BL") val bl: String? = null,
    @SerialName("LAP") val lap: String? = null,
    @SerialName("CC") val cc: String? = null,
)

@Serializable
data class DialerLiveDto(
    @SerialName("Location") val location: String? = null,
    @SerialName("TL") val tl: String? = null,
    @SerialName("Caller") val caller: String? = null,
    @SerialName("DND") val dnd: String? = null,
    @SerialName("Lead") val lead: String? = null,
    @SerialName("FollowUp") val followUp: String? = null,
    @SerialName("NI") val ni: String? = null,
    @SerialName("NotDoable") val notDoable: String? = null,
    @SerialName("WrongNo") val wrongNo: String? = null,
    @SerialName("Blank") val blank: String? = null,
    @SerialName("Interested") val interested: String? = null,
    @SerialName("NotContactable") val notContactable: String? = null,
    @SerialName("Ringing") val ringing: String? = null,
    @SerialName("TotalCount") val totalCount: String? = null,
    @SerialName("Total") val total: String? = null,
    @SerialName("Auto") val auto: String? = null,
    @SerialName("Manual") val manual: String? = null,
    @SerialName("TotalClientConnected") val totalClientConnected: String? = null,
    @SerialName("Duration") val duration: String? = null,
    @SerialName("TotalLoginAmt") val totalLoginAmt: String? = null,
    @SerialName("TotalLogin") val totalLogin: String? = null,
    @SerialName("TotalDisbursedAmt") val totalDisbursedAmt: String? = null,
    @SerialName("TotalDisbursedCount") val totalDisbursedCount: String? = null,
    @SerialName("DailyLogin") val dailyLogin: String? = null,
    @SerialName("DailyLoginAmt") val dailyLoginAmt: String? = null,
)

@Serializable
data class DialerDataDto(
    @SerialName("CD_KEY") val key: Int? = null,
    @SerialName("CD_CLIENT_NAME") val clientName: String? = null,
    @SerialName("CD_CONTACT") val contact: String? = null,
    @SerialName("CD_PRODUCT") val product: String? = null,
    @SerialName("CD_CAMPAIGN_NAME") val campaign: String? = null,
    @SerialName("CD_SALARY") val salary: String? = null,
    @SerialName("CD_COMPANY") val company: String? = null,
    @SerialName("CD_STATUS") val status: String? = null,
    @SerialName("CD_CALL_BACK_DATE") val callBackDate: String? = null,
    @SerialName("CD_REMARKS") val remarks: String? = null,
    @SerialName("CD_USER_KEY") val userKey: String? = null,
)

@Serializable
data class PickupEntryDto(
    @SerialName("Key") val key: String? = null,
    @SerialName("CallerName") val callerName: String? = null,
    @SerialName("ClientName") val clientName: String? = null,
    @SerialName("ClientMobile") val clientMobile: String? = null,
    @SerialName("Product") val product: String? = null,
    @SerialName("Banks") val banks: String? = null,
    @SerialName("Status") val status: String? = null,
    @SerialName("PickupDate") val pickupDate: String? = null,
    @SerialName("Region") val region: String? = null,
    @SerialName("PickupType") val pickupType: String? = null,
    @SerialName("Remarks") val remarks: String? = null,
)

@Serializable
data class LeadDto(
    @SerialName("Key") val key: String? = null,
    @SerialName("ClientName") val clientName: String? = null,
    @SerialName("Contact") val contact: String? = null,
    @SerialName("Product") val product: String? = null,
    @SerialName("Salary") val salary: String? = null,
    @SerialName("Company") val company: String? = null,
    @SerialName("Status") val status: String? = null,
    @SerialName("CallerName") val callerName: String? = null,
    @SerialName("CallBackDate") val callBackDate: String? = null,
    @SerialName("IsHLLAPBL") val isHLLAPBL: String? = null,
    @SerialName("IsCibilBad") val isCibilBad: Boolean? = null,
    @SerialName("IsRealEstate") val isRealEstate: Boolean? = null,
    @SerialName("Remarks") val remarks: String? = null,
    @SerialName("CreatedDate") val createdDate: String? = null,
    @SerialName("UserKey") val userKey: String? = null,
)

@Serializable
data class LeadCountDto(
    @SerialName("Daily") val daily: Int? = null,
    @SerialName("Monthly") val monthly: Int? = null,
    @SerialName("Total") val total: Int? = null,
)

@Serializable
data class CallLogDto(
    @SerialName("Key") val key: String? = null,
    @SerialName("Name") val name: String? = null,
    @SerialName("Number") val number: String? = null,
    @SerialName("Type") val type: String? = null,
    @SerialName("Duration") val duration: Int? = null,
    @SerialName("CallDate") val callDate: String? = null,
    @SerialName("UserKey") val userKey: String? = null,
)

@Serializable
data class DailyLoginDto(
    @SerialName("Key") val key: String? = null,
    @SerialName("ClientName") val clientName: String? = null,
    @SerialName("MobileNo") val mobileNo: String? = null,
    @SerialName("Product") val product: String? = null,
    @SerialName("Banks") val banks: String? = null,
    @SerialName("LOSNo") val losNo: String? = null,
    @SerialName("AppNo") val appNo: String? = null,
    @SerialName("Region") val region: String? = null,
    @SerialName("LoginAmount") val loginAmount: Double? = null,
    @SerialName("Remarks") val remarks: String? = null,
)

@Serializable
data class DocumentDto(
    @SerialName("Key") val key: String? = null,
    @SerialName("ClientName") val clientName: String? = null,
    @SerialName("MobileNo") val mobileNo: String? = null,
    @SerialName("FileName") val fileName: String? = null,
    @SerialName("FileType") val fileType: String? = null,
    @SerialName("FileData") val fileData: String? = null,
)

@Serializable
data class AttendanceDto(
    @SerialName("TotalDays") val totalDays: Int? = null,
    @SerialName("Salary") val salary: Double? = null,
    @SerialName("SalaryEarned") val salaryEarned: Double? = null,
    @SerialName("AbsentDays") val absentDays: Int? = null,
    @SerialName("HalfDay") val halfDay: Int? = null,
    @SerialName("Late") val late: Int? = null,
    @SerialName("WorkingDays") val workingDays: Int? = null,
)

@Serializable
data class CallerClientDto(
    @SerialName("Key") val key: String? = null,
    @SerialName("ClientName") val clientName: String? = null,
    @SerialName("MobileNo") val mobileNo: String? = null,
)

@Serializable
data class UpdateStatusRequest(
    @SerialName("Key") val key: String? = null,
    @SerialName("CPVStatus") val cpvStatus: String? = null,
    @SerialName("DisbursedAmount") val disbursedAmount: Double? = null,
    @SerialName("Salary") val salary: Double? = null,
    @SerialName("CallBackDate") val callBackDate: String? = null,
    @SerialName("CallBackTime") val callBackTime: String? = null,
    @SerialName("IsHLLAPBL") val isHLLAPBL: String? = null,
    @SerialName("IsCibilBad") val isCibilBad: Boolean? = null,
    @SerialName("IsRealEstate") val isRealEstate: Boolean? = null,
    @SerialName("Remarks") val remarks: String? = null,
    @SerialName("UserKey") val userKey: String? = null,
    @SerialName("Latitude") val latitude: Double? = null,
    @SerialName("Longitude") val longitude: Double? = null,
)

@Serializable
data class InsertLeadRequest(
    @SerialName("Name") val name: String,
    @SerialName("MobileNo") val mobileNo: String,
    @SerialName("Product") val product: String,
    @SerialName("CallerID") val callerId: String? = null,
    @SerialName("Remarks") val remarks: String? = null,
    @SerialName("UserKey") val userKey: String,
)

@Serializable
data class InsertDailyLoginRequest(
    @SerialName("ClientName") val clientName: String,
    @SerialName("MobileNo") val mobileNo: String,
    @SerialName("Banks") val banks: String,
    @SerialName("Product") val product: String,
    @SerialName("Region") val region: String,
    @SerialName("LOSNo") val losNo: String,
    @SerialName("AppNo") val appNo: String,
    @SerialName("LoginAmt") val loginAmt: Double,
    @SerialName("Remarks") val remarks: String? = null,
    @SerialName("UserKey") val userKey: String,
)

@Serializable
data class SendEmailRequest(
    @SerialName("Body") val body: String,
    @SerialName("User") val user: String,
)
