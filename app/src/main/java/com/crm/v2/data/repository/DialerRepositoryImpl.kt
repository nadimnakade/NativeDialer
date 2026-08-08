package com.crm.v2.data.repository

import android.util.Log
import com.crm.v2.common.log.ApiLogger
import com.crm.v2.common.result.Resource
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.domain.model.DialerRecord
import com.crm.v2.domain.repository.DialerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "DialerRepo"

@Singleton
class DialerRepositoryImpl @Inject constructor(
    private val pickupApi: PickupApi,
) : DialerRepository {

    override suspend fun getDialerData(userKey: String): Resource<List<DialerRecord>> {
        val url = "Pickup/GetDialerData?userKey=$userKey"
        ApiLogger.request(TAG, "GET", url)
        return try {
            val response = pickupApi.getDialerData(userKey)
            if (response.isSuccessful) {
                val raw = response.body()
                ApiLogger.response(TAG, response.code(), url, raw?.toString()?.take(500))
                val records = raw?.map { dto ->
                    DialerRecord(
                        key = dto.key?.toString() ?: "",
                        clientName = dto.clientName ?: "",
                        contact = dto.contact ?: "",
                        product = dto.product,
                        campaign = dto.campaign,
                        salary = dto.salary,
                        company = dto.company,
                        status = dto.status,
                        callbackDate = dto.callBackDate,
                        remarks = dto.remarks,
                        userKey = dto.userKey,
                    )
                } ?: emptyList()
                ApiLogger.event(TAG, "GetDialerData returned ${records.size} records")
                Resource.Success(records)
            } else {
                ApiLogger.error(TAG, "GetDialerData failed: ${response.code()} ${response.message()}")
                Resource.Error("Failed to fetch dialer data: ${response.code()}")
            }
        } catch (e: Exception) {
            ApiLogger.error(TAG, "GetDialerData exception: ${e.message}", e)
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getDialerNotUpdated(userKey: String): Resource<List<DialerRecord>> {
        val url = "Pickup/GetDialerNotUpdatedData?userKey=$userKey"
        ApiLogger.request(TAG, "GET", url)
        return try {
            val response = pickupApi.getDialerNotUpdated(userKey)
            if (response.isSuccessful) {
                val raw = response.body()
                ApiLogger.response(TAG, response.code(), url, raw?.toString()?.take(500))
                val records = raw?.map { dto ->
                    DialerRecord(
                        key = dto.key?.toString() ?: "",
                        clientName = dto.clientName ?: "",
                        contact = dto.contact ?: "",
                        product = dto.product,
                        campaign = dto.campaign,
                        salary = dto.salary,
                        company = dto.company,
                        status = dto.status,
                        callbackDate = dto.callBackDate,
                        remarks = dto.remarks,
                        userKey = dto.userKey,
                    )
                } ?: emptyList()
                ApiLogger.event(TAG, "GetDialerNotUpdated returned ${records.size} records")
                Resource.Success(records)
            } else {
                ApiLogger.error(TAG, "GetDialerNotUpdated failed: ${response.code()}")
                Resource.Error("Failed to fetch non-updated dialer data: ${response.code()}")
            }
        } catch (e: Exception) {
            ApiLogger.error(TAG, "GetDialerNotUpdated exception: ${e.message}", e)
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun updateDialingStatus(key: String): Resource<String> {
        val url = "Pickup/UpdateDialingStatus?Key=$key"
        ApiLogger.request(TAG, "GET", url)
        return try {
            val response = pickupApi.updateDialingStatus(key)
            ApiLogger.response(TAG, response.code(), url, response.body()?.toString())
            if (response.isSuccessful) {
                ApiLogger.event(TAG, "UpdateDialingStatus OK for key=$key")
                Resource.Success("Status updated")
            } else {
                ApiLogger.error(TAG, "UpdateDialingStatus failed: ${response.code()}")
                Resource.Error("Failed to update dialing status: ${response.code()}")
            }
        } catch (e: Exception) {
            ApiLogger.error(TAG, "UpdateDialingStatus exception: ${e.message}", e)
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun updateDialer(
        key: String, status: String, callbackDate: String?,
        remarks: String?, userKey: String
    ): Resource<String> {
        val safeKey = key.toIntOrNull() ?: 0
        val safeUserKey = userKey.toIntOrNull() ?: 0
        val safeRemarks = remarks?.ifEmpty { "NA" } ?: "NA"
        val safeCallbackDate = callbackDate ?: ""
        val url = "Pickup/UpdateDialer?Key=$safeKey&Status=$status&Remarks=$safeRemarks&CallBackDate=$safeCallbackDate"
        ApiLogger.request(TAG, "GET", url)
        return try {
            val response = pickupApi.updateDialer(
                key = safeKey,
                status = status,
                remarks = safeRemarks,
                callbackDate = safeCallbackDate,
                callbackTime = "",
                callDuration = "0@|0",
                callDate = "0",
                userKey = safeUserKey,
                isHllapbl = 0,
                isCibilBad = 0,
                isRealEstate = 0,
            )
            val bodyStr = response.body()?.string()
            ApiLogger.response(TAG, response.code(), url, bodyStr)
            if (response.isSuccessful) {
                ApiLogger.event(TAG, "UpdateDialer OK: key=$key status=$status body=$bodyStr")
                Resource.Success("Dialer updated")
            } else {
                ApiLogger.error(TAG, "UpdateDialer failed: ${response.code()} $bodyStr")
                Resource.Error("Failed to update dialer: ${response.code()}")
            }
        } catch (e: Exception) {
            ApiLogger.error(TAG, "UpdateDialer exception: ${e.message}", e)
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override fun startAutoDial(userKey: String): Flow<List<DialerRecord>> = flow {
        ApiLogger.event(TAG, "startAutoDial: fetching queue for userKey=$userKey")
        val result = getDialerData(userKey)
        if (result is Resource.Success) {
            emit(result.data)
        }
    }

    override suspend fun stopAutoDial() {
        ApiLogger.event(TAG, "stopAutoDial called")
    }
}
