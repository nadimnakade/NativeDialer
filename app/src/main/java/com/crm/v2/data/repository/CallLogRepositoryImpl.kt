package com.crm.v2.data.repository

import com.crm.v2.common.result.Resource
import com.crm.v2.data.database.dao.CallLogDao
import com.crm.v2.data.database.entity.CallLogEntity
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.data.network.dto.pickup.CallLogDto
import com.crm.v2.domain.model.CallLog
import com.crm.v2.domain.repository.CallLogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CallLogRepositoryImpl @Inject constructor(
    private val pickupApi: PickupApi,
    private val callLogDao: CallLogDao,
) : CallLogRepository {

    override suspend fun saveCallLogBatch(logs: List<CallLog>): Resource<String> {
        return try {
            val dtos = logs.map { it.toDto() }
            val response = pickupApi.saveCallLogsAll(dtos)
            if (response.isSuccessful) Resource.Success("Call logs saved")
            else {
                val errBody = response.errorBody()?.string()
                Resource.Error(errBody?.take(200) ?: "Failed to save call logs (HTTP ${response.code()})")
            }
        } catch (e: Exception) {
            // Save offline
            callLogDao.insertCallLogs(logs.map { it.toEntity() })
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override suspend fun saveCallLog(log: CallLog): Resource<String> {
        return try {
            val response = pickupApi.saveCallLogIndv(log.toDto())
            if (response.isSuccessful) Resource.Success("Call log saved")
            else {
                val errBody = response.errorBody()?.string()
                Resource.Error(errBody?.take(200) ?: "Failed to save call log (HTTP ${response.code()})")
            }
        } catch (e: Exception) {
            callLogDao.insertCallLog(log.toEntity())
            Resource.Error(e.message ?: "Network error", e)
        }
    }

    override fun getOfflineCallLogs(): Flow<List<CallLog>> {
        return callLogDao.getCallLogs("", 100).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun syncPendingCallLogs(): Resource<Int> {
        return try {
            val unsynced = callLogDao.getUnsyncedCallLogs()
            if (unsynced.isEmpty()) return Resource.Success(0)

            val dtos = unsynced.map { it.toDto() }
            val response = pickupApi.saveCallLogsAll(dtos)
            if (response.isSuccessful) {
                callLogDao.markAsSynced(unsynced.map { it.id })
                Resource.Success(unsynced.size)
            } else {
                val errBody = response.errorBody()?.string()
                Resource.Error(errBody?.take(200) ?: "Sync failed (HTTP ${response.code()})")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Network error", e)
        }
    }
}

private fun CallLog.toDto() = CallLogDto(
    key = key, name = name, number = number,
    type = type, duration = duration, callDate = callDate, userKey = userKey,
)

private fun CallLog.toEntity() = CallLogEntity(
    key = key, name = name, number = number,
    type = type, duration = duration, callDate = callDate, userKey = userKey,
)

private fun CallLogEntity.toDomain() = CallLog(
    key = key, name = name, number = number,
    type = type, duration = duration, callDate = callDate, userKey = userKey,
)

private fun CallLogEntity.toDto() = CallLogDto(
    key = key, name = name, number = number,
    type = type, duration = duration, callDate = callDate, userKey = userKey,
)
