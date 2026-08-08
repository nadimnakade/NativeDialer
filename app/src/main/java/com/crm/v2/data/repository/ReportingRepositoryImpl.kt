package com.crm.v2.data.repository

import com.crm.v2.common.result.Resource
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.data.network.dto.pickup.DashboardCounterDto
import com.crm.v2.domain.model.*
import com.crm.v2.domain.repository.ReportingRepository
import kotlinx.serialization.json.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReportingRepositoryImpl @Inject constructor(
    private val pickupApi: PickupApi,
) : ReportingRepository {

    override suspend fun getDashboardData(userKey: String): Resource<DashboardData> {
        return try {
            val response = pickupApi.getDashboardCounter(userKey)
            if (response.isSuccessful) {
                val counters = response.body()
                Resource.Success(aggregateDashboardCounters(counters ?: emptyList()))
            } else {
                Resource.Error("Failed to load dashboard")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getDialerLive(userKey: String): Resource<LiveCount> {
        return try {
            val response = pickupApi.getDialerLive(userKey)
            if (response.isSuccessful) {
                val list = response.body()
                val first = list?.firstOrNull()
                first?.let { Resource.Success(it.toDomain()) }
                    ?: Resource.Error("Empty response")
            } else Resource.Error("Failed to load live count")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getDialerLiveIndv(userKey: String): Resource<LiveCount> {
        return try {
            val response = pickupApi.getDialerLiveIndv(userKey)
            if (response.isSuccessful) {
                val list = response.body()
                val first = list?.firstOrNull()
                first?.let { Resource.Success(it.toDomain()) }
                    ?: Resource.Error("Empty response")
            } else Resource.Error("Failed to load individual live count")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getTeamCallLog(userKey: String): Resource<List<CallLog>> {
        return try {
            val response = pickupApi.getTeamCallLog(userKey)
            if (response.isSuccessful) {
                val logs = response.body()?.map { it.toDomain() } ?: emptyList()
                Resource.Success(logs)
            } else Resource.Error("Failed to load team call log")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getAttendance(userKey: String): Resource<AttendanceData> {
        return try {
            val response = pickupApi.getAttendance(userKey)
            if (response.isSuccessful) {
                response.body()?.let { Resource.Success(it.toDomain()) }
                    ?: Resource.Error("Empty response")
            } else Resource.Error("Failed to load attendance")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getPerformance(userKey: String): Resource<List<PerformanceData>> {
        return try {
            val response = pickupApi.getIncentive(userKey)
            if (response.isSuccessful) {
                val bodyString = response.body()?.string() ?: "[]"
                val jsonArray = Json.parseToJsonElement(bodyString).jsonArray
                val data = jsonArray.mapNotNull { element ->
                    val map = element.jsonObject
                    val key = map["Key"]?.jsonPrimitive?.contentOrNull ?: return@mapNotNull null
                    val value = map["Value"]?.jsonPrimitive?.doubleOrNull ?: 0.0
                    PerformanceData(key, value)
                }
                Resource.Success(data)
            } else Resource.Error("Failed to load performance")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getRankings(userKey: String): Resource<RankingData> {
        // Rankings are obtained from separate legacy endpoints
        return Resource.Success(RankingData())
    }

    override suspend fun getIncentive(userKey: String): Resource<List<PerformanceData>> {
        return getPerformance(userKey)
    }

    override suspend fun getApplications(userKey: String): Resource<List<Map<String, Any>>> {
        return try {
            val response = pickupApi.getApplications(userKey)
            if (response.isSuccessful) {
                val bodyString = response.body()?.string() ?: "[]"
                val jsonArray = Json.parseToJsonElement(bodyString).jsonArray
                val result = jsonArray.map { element ->
                    element.jsonObject.mapValues { entry ->
                        when (val v = entry.value) {
                            is JsonPrimitive -> v.content
                            else -> v.toString()
                        }
                    }
                }
                Resource.Success(result)
            } else Resource.Error("Failed to load applications")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getDisbursalDetail(userKey: String, loanId: String): Resource<DisbursalDetail> {
        return Resource.Success(DisbursalDetail(loanId = loanId, clientName = "", loanAmount = 0.0, disbursedAmount = 0.0))
    }
}

private fun aggregateDashboardCounters(counters: List<DashboardCounterDto>): DashboardData {
    val c = counters.firstOrNull() ?: return DashboardData()
    return DashboardData(
        rank = c.rank?.toIntOrNull() ?: 0,
        points = c.points?.toDoubleOrNull() ?: 0.0,
        totalDisbursedAmt = c.totalDisbursedAmt?.toDoubleOrNull()
            ?: c.disbursedAmt?.toDoubleOrNull() ?: 0.0,
        clientConnectedFtd = c.clientConnected?.toIntOrNull() ?: 0,
        duration = c.duration ?: "00:00:00",
        dailyLeads = c.dailyLeadCount?.toIntOrNull() ?: 0,
        monthlyLeads = c.monthlyLeadCount?.toIntOrNull() ?: 0,
        loginCount = c.monthlyLoginCount?.toIntOrNull() ?: 0,
        loginAmount = c.monthlyLoginAmt?.toDoubleOrNull() ?: 0.0,
        connectivityPercent = 0.0,
    )
}

private fun com.crm.v2.data.network.dto.pickup.DialerLiveDto.toDomain() = LiveCount(
    dnd = dnd?.toIntOrNull() ?: 0,
    lead = lead?.toIntOrNull() ?: 0,
    followUp = followUp?.toIntOrNull() ?: 0,
    notInterested = ni?.toIntOrNull() ?: 0,
    notDoable = notDoable?.toIntOrNull() ?: 0,
    wrongNo = wrongNo?.toIntOrNull() ?: 0,
    blank = blank?.toIntOrNull() ?: 0,
    interested = interested?.toIntOrNull() ?: 0,
    notContactable = notContactable?.toIntOrNull() ?: 0,
    ringing = ringing?.toIntOrNull() ?: 0,
    totalCount = totalCount?.toIntOrNull() ?: 0,
    totalClientConnected = totalClientConnected?.toIntOrNull() ?: 0,
    duration = duration ?: "00:00:00",
    totalLoginAmt = totalLoginAmt?.toDoubleOrNull() ?: 0.0,
    totalLogin = totalLogin?.toIntOrNull() ?: 0,
    totalDisbursedAmt = totalDisbursedAmt?.toDoubleOrNull() ?: 0.0,
    totalDisbursedCount = totalDisbursedCount?.toIntOrNull() ?: 0,
    dailyLogin = dailyLogin?.toIntOrNull() ?: 0,
    dailyLoginAmt = dailyLoginAmt?.toDoubleOrNull() ?: 0.0,
)

private fun com.crm.v2.data.network.dto.pickup.AttendanceDto.toDomain() = AttendanceData(
    totalDays = totalDays ?: 0,
    salary = salary ?: 0.0,
    salaryEarned = salaryEarned ?: 0.0,
    absentDays = absentDays ?: 0,
    halfDay = halfDay ?: 0,
    late = late ?: 0,
    workingDays = workingDays ?: 0,
)

private fun com.crm.v2.data.network.dto.pickup.CallLogDto.toDomain() = CallLog(
    key = key,
    name = name,
    number = number,
    type = type,
    duration = duration ?: 0,
    callDate = callDate,
    userKey = userKey,
)
