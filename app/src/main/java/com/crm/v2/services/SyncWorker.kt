package com.crm.v2.services

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.crm.v2.data.database.dao.CallLogDao
import com.crm.v2.data.database.dao.SyncQueueDao
import com.crm.v2.data.network.api.PickupApi
import com.crm.v2.data.network.dto.pickup.CallLogDto
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val callLogDao: CallLogDao,
    private val syncQueueDao: SyncQueueDao,
    private val pickupApi: PickupApi,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            // Sync unsynced call logs
            val unsyncedLogs = callLogDao.getUnsyncedCallLogs()
            if (unsyncedLogs.isNotEmpty()) {
                val dtos = unsyncedLogs.map { entity ->
                    CallLogDto(
                        key = entity.key,
                        name = entity.name,
                        number = entity.number,
                        type = entity.type,
                        duration = entity.duration,
                        callDate = entity.callDate,
                        userKey = entity.userKey,
                    )
                }

                val response = pickupApi.saveCallLogsAll(dtos)
                if (response.isSuccessful) {
                    callLogDao.markAsSynced(unsyncedLogs.map { it.id })
                }
            }

            Result.success()
        } catch (e: Exception) {
            if (runAttemptCount < 3) Result.retry() else Result.failure()
        }
    }
}
