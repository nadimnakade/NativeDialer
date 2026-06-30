package com.crm.v2.data.repository

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
                        key = dto.key ?: "",
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
                        key = dto.key ?: "",
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
            val response = pickupApi.updateDialingStatus(key)
            if (response.isSuccessful) Resource.Success("Status updated")
            else Resource.Error("Failed to update dialing status")
        } catch (e: Exception) {
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
                "CPVStatus" to status,
                "UserKey" to userKey,
            )
            callbackDate?.let { body["CallBackDate"] = it }
            remarks?.let { body["Remarks"] = it }

            val response = pickupApi.updateDialer(body)
            if (response.isSuccessful) Resource.Success("Dialer updated")
            else Resource.Error("Failed to update dialer")
        } catch (e: Exception) {
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
