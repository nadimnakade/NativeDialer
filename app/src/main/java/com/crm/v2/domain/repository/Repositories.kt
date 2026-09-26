package com.crm.v2.domain.repository

import com.crm.v2.common.result.Resource
import com.crm.v2.domain.model.*
import kotlinx.coroutines.flow.Flow

interface LeadRepository {
    suspend fun getLeads(userKey: String, pageIndex: Int): Resource<List<Lead>>
    suspend fun getLeadDetail(userKey: String, key: String): Resource<Lead>
    suspend fun createLead(
        name: String, mobileNo: String, product: String,
        callerId: String?, remarks: String?, userKey: String
    ): Resource<String>
    suspend fun getLeadCount(userKey: String, type: String?): Resource<LeadCount>
    suspend fun getFollowUpLeads(userKey: String, type: String?): Resource<List<DialerRecord>>
    suspend fun getCallerData(userKey: String, type: String?, pageIndex: Int = 0): Resource<List<DialerRecord>>
    suspend fun updateLeadStatus(request: Map<String, String>): Resource<String>
}

interface PickupRepository {
    suspend fun getPickupData(userKey: String): Resource<List<PickupEntry>>
    suspend fun getPickupEntryData(userKey: String, type: String?): Resource<List<PickupEntry>>
    suspend fun createPickup(leadId: String, userKey: String): Resource<String>
}

interface DialerRepository {
    suspend fun getDialerData(userKey: String): Resource<List<DialerRecord>>
    suspend fun getDialerNotUpdated(userKey: String): Resource<List<DialerRecord>>
    suspend fun updateDialingStatus(key: String): Resource<String>
    suspend fun updateDialer(
        key: String, status: String, callbackDate: String?,
        callbackTime: String?, remarks: String?, userKey: String,
        callDuration: String = "0@|0", callDate: String = "0",
        isHllapbl: Int = 0, isCibilBad: Int = 0, isRealEstate: Int = 0,
    ): Resource<String>
    suspend fun insertLeadInfoNew(
        clientName: String, contact: String, salary: String, company: String,
        remarks: String, status: String, callbackDate: String, callbackTime: String,
        callerDuration: String, callDateTime: String, userKey: String,
        isHllapbl: Int, isCibilBad: Int, isRealEstate: Int,
    ): Resource<String>
    fun startAutoDial(userKey: String): Flow<List<DialerRecord>>
    suspend fun stopAutoDial()
}

interface CallLogRepository {
    suspend fun saveCallLogBatch(logs: List<CallLog>): Resource<String>
    suspend fun saveCallLog(log: CallLog): Resource<String>
    fun getOfflineCallLogs(): Flow<List<CallLog>>
    suspend fun syncPendingCallLogs(): Resource<Int>
}

interface ReportingRepository {
    suspend fun getDashboardData(userKey: String): Resource<DashboardData>
    suspend fun getDialerLive(userKey: String): Resource<LiveCount>
    suspend fun getDialerLiveIndv(userKey: String): Resource<LiveCount>
    suspend fun getTeamCallLog(userKey: String): Resource<List<CallLog>>
    suspend fun getAttendance(userKey: String): Resource<AttendanceData>
    suspend fun getPerformance(userKey: String): Resource<List<PerformanceData>>
    suspend fun getRankings(userKey: String): Resource<RankingData>
    suspend fun getIncentive(userKey: String): Resource<List<PerformanceData>>
    suspend fun getApplications(userKey: String): Resource<List<Map<String, Any>>>
    suspend fun getDisbursalDetail(userKey: String, loanId: String): Resource<DisbursalDetail>
}

interface DailyLoginRepository {
    suspend fun insertDailyLogin(
        clientName: String, mobileNo: String, banks: String,
        product: String, region: String, losNo: String,
        appNo: String, loginAmt: Double, remarks: String?, userKey: String
    ): Resource<String>
    suspend fun getDailyLoginList(userKey: String): Resource<List<DailyLogin>>
}

interface DocumentRepository {
    suspend fun saveDocument(
        fileBytes: ByteArray, fileName: String,
        clientName: String, caller: String,
        mobileNo: String, fileType: String
    ): Resource<String>
    suspend fun getCallerClients(userKey: String, mobileNo: String): Resource<List<ClientInfo>>
    suspend fun getClientDocuments(userKey: String, mobileNo: String): Resource<List<Document>>
    suspend fun getPayslips(userKey: String): Resource<List<Document>>
}

interface SettingsRepository {
    suspend fun getProfile(userKey: String): Resource<Employee>
    suspend fun updateProfile(employee: Employee): Resource<String>
    suspend fun sendFeedback(body: String, user: String): Resource<String>
}

interface ContactRepository {
    fun getDeviceContacts(): Flow<List<Contact>>
    fun searchContacts(query: String): Flow<List<Contact>>
    fun getCachedContacts(): Flow<List<Contact>>
}

interface SearchApplicationRepository {
    suspend fun searchApplications(
        userKey: String,
        pageIndex: Int = 0,
        pageSize: Int = 10,
        search: String = "",
    ): Resource<List<SearchApplication>>
}
