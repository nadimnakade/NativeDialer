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
                val errBody = response.errorBody()?.string()
                ApiLogger.error(TAG, "GetDialerData failed: ${response.code()} $errBody")
                Resource.Error(errBody?.take(200) ?: "Failed to fetch dialer data (HTTP ${response.code()})")
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
                val errBody = response.errorBody()?.string()
                ApiLogger.error(TAG, "GetDialerNotUpdated failed: ${response.code()} $errBody")
                Resource.Error(errBody?.take(200) ?: "Failed to fetch non-updated dialer data (HTTP ${response.code()})")
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
            val bodyStr = response.body()?.string()
            ApiLogger.response(TAG, response.code(), url, bodyStr)
            if (response.isSuccessful) {
                ApiLogger.event(TAG, "UpdateDialingStatus OK for key=$key body=$bodyStr")
                if (bodyStr?.contains("Success", ignoreCase = true) == true) {
                    Resource.Success("Status updated")
                } else {
                    Resource.Error("Update dialing status failed: $bodyStr")
                }
            } else {
                val errBody = response.errorBody()?.string() ?: bodyStr
                ApiLogger.error(TAG, "UpdateDialingStatus failed: ${response.code()} $errBody")
                Resource.Error(errBody?.take(200) ?: "Failed to update dialing status (HTTP ${response.code()})")
            }
        } catch (e: Exception) {
            ApiLogger.error(TAG, "UpdateDialingStatus exception: ${e.message}", e)
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun updateDialer(
        key: String, status: String, callbackDate: String?,
        callbackTime: String?, remarks: String?, userKey: String,
        callDuration: String, callDate: String,
        isHllapbl: Int, isCibilBad: Int, isRealEstate: Int,
    ): Resource<String> {
        val safeKey = key.toIntOrNull() ?: 0
        val safeUserKey = userKey.toIntOrNull() ?: 0
        val safeRemarks = remarks?.ifEmpty { "NA" } ?: "NA"
        val safeCallbackDate = callbackDate ?: "01/01/0001"
        val safeCallbackTime = callbackTime ?: "00:00:00"
        val url = "Pickup/UpdateDialer?Key=$safeKey&Status=$status"
        ApiLogger.request(TAG, "GET", url)
        return try {
            val response = pickupApi.updateDialer(
                key = safeKey,
                status = status,
                remarks = safeRemarks,
                callbackDate = safeCallbackDate,
                callbackTime = safeCallbackTime,
                callDuration = callDuration,
                callDate = callDate,
                userKey = safeUserKey,
                isHllapbl = isHllapbl,
                isCibilBad = isCibilBad,
                isRealEstate = isRealEstate,
            )
            val bodyStr = response.body()?.string()
            ApiLogger.response(TAG, response.code(), url, bodyStr)
            if (response.isSuccessful) {
                if (bodyStr != null && bodyStr.contains("success", ignoreCase = true)) {
                    ApiLogger.event(TAG, "UpdateDialer OK: key=$key status=$status")
                    Resource.Success("Dialer updated")
                } else {
                    val cleaned = bodyStr?.trim()?.removeSurrounding("\"") ?: ""
                    val retVal = cleaned.toIntOrNull()
                    if (retVal != null && retVal > 0) {
                        ApiLogger.event(TAG, "UpdateDialer OK (int): key=$key status=$status val=$retVal")
                        Resource.Success("Dialer updated")
                    } else {
                        ApiLogger.error(TAG, "UpdateDialer no success in body: $bodyStr")
                        Resource.Error("Update failed: $bodyStr")
                    }
                }
            } else {
                val errBody = bodyStr ?: response.errorBody()?.string()
                ApiLogger.error(TAG, "UpdateDialer failed: ${response.code()} $errBody")
                Resource.Error(errBody?.take(200) ?: "Failed to update dialer (HTTP ${response.code()})")
            }
        } catch (e: Exception) {
            ApiLogger.error(TAG, "UpdateDialer exception: ${e.message}", e)
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun insertLeadInfoNew(
        clientName: String, contact: String, salary: String, company: String,
        remarks: String, status: String, callbackDate: String, callbackTime: String,
        callerDuration: String, callDateTime: String, userKey: String,
        isHllapbl: Int, isCibilBad: Int, isRealEstate: Int,
    ): Resource<String> {
        val safeCallbackDate = callbackDate.ifEmpty { "01/01/0001" }
        val safeCallbackTime = callbackTime.ifEmpty { "00:00:00" }
        return try {
            val response = pickupApi.addLeadNew(
                clientName = clientName,
                contact = contact,
                salary = salary,
                company = company,
                remarks = remarks,
                status = status,
                callbackDate = safeCallbackDate,
                callbackTime = safeCallbackTime,
                callerDuration = callerDuration,
                callDateTime = callDateTime,
                userKey = userKey,
                isHllapbl = isHllapbl,
                isCibilBad = isCibilBad,
                isRealEstate = isRealEstate,
            )
            val bodyStr = response.body()?.string()
            ApiLogger.response(TAG, response.code(), "Pickup/InsertLeadInfo_New", bodyStr)
            if (response.isSuccessful) {
                if (bodyStr != null && bodyStr.contains("success", ignoreCase = true)) {
                    Resource.Success("Lead created")
                } else {
                    val cleaned = bodyStr?.trim()?.removeSurrounding("\"") ?: ""
                    val retVal = cleaned.toIntOrNull() ?: 0
                    if (retVal > 0) {
                        Resource.Success("Lead created")
                    } else {
                        Resource.Error("Insert failed: server returned $bodyStr")
                    }
                }
            } else {
                val errBody = bodyStr ?: response.errorBody()?.string()
                ApiLogger.error(TAG, "InsertLeadInfo_New failed: ${response.code()} $errBody")
                Resource.Error(errBody?.take(200) ?: "Failed to create lead (HTTP ${response.code()})")
            }
        } catch (e: Exception) {
            ApiLogger.error(TAG, "InsertLeadInfo_New exception: ${e.message}", e)
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
