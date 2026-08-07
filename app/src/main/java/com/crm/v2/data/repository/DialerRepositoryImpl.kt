package com.crm.v2.data.repository

import android.util.Log
import com.crm.v2.common.result.Resource
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.domain.model.DialerRecord
import com.crm.v2.domain.repository.DialerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DialerRepositoryImpl @Inject constructor(
    private val pickupApi: PickupApi,
) : DialerRepository {

    override suspend fun getDialerData(userKey: String): Resource<List<DialerRecord>> {
        return try {
            val response = pickupApi.getDialerData(userKey)
            if (response.isSuccessful) {
                val records = response.body()?.map { dto ->
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
                Resource.Success(records)
            } else Resource.Error("Failed to fetch dialer data")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun getDialerNotUpdated(userKey: String): Resource<List<DialerRecord>> {
        return try {
            val response = pickupApi.getDialerNotUpdated(userKey)
            if (response.isSuccessful) {
                val records = response.body()?.map { dto ->
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
                Resource.Success(records)
            } else Resource.Error("Failed to fetch non-updated dialer data")
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun updateDialingStatus(key: String): Resource<String> {
        return try {
            Log.d("DialerRepository", "updateDialingStatus key: $key")
            val response = pickupApi.updateDialingStatus(key)
            Log.d("DialerRepository", "updateDialingStatus response: ${response.code()} ${response.message()}")
            if (response.isSuccessful) Resource.Success("Status updated")
            else Resource.Error("Failed to update dialing status: ${response.code()}")
        } catch (e: Exception) {
            Log.e("DialerRepository", "updateDialingStatus error", e)
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun updateDialer(
        key: String, status: String, callbackDate: String?,
        remarks: String?, userKey: String
    ): Resource<String> {
        return try {
            val body = mutableMapOf<String, String>(
                "Key" to key,
                "Status" to status.ifEmpty { "-1" },
                "UserKey" to userKey,
                "Remarks" to (remarks?.ifEmpty { "NA" } ?: "NA"),
                "CallBackDate" to (callbackDate ?: ""),
                "CallBackTime" to "",
                "CallDuration" to "0@|0",
                "CallDate" to "0",
                "IsHLLAPBL" to "0",
                "IsCibilBad" to "0",
                "IsRealEstate" to "0",
            )

            Log.d("DialerRepository", "updateDialer body: $body")
            val response = pickupApi.updateDialer(body)
            Log.d("DialerRepository", "updateDialer response: ${response.code()} ${response.message()}")
            if (response.isSuccessful) Resource.Success("Dialer updated")
            else Resource.Error("Failed to update dialer: ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            Log.e("DialerRepository", "updateDialer error", e)
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override fun startAutoDial(userKey: String): Flow<List<DialerRecord>> = flow {
        val result = getDialerData(userKey)
        if (result is Resource.Success) {
            emit(result.data)
        }
    }

    override suspend fun stopAutoDial() {
        // Cancel auto-dial job
    }
}
