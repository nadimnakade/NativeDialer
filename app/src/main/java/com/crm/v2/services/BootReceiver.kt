package com.crm.v2.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.*

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED || intent.action == Intent.ACTION_MY_PACKAGE_REPLACED) {
            val syncRequest = PeriodicWorkRequestBuilder<SyncWorker>(15, java.util.concurrent.TimeUnit.MINUTES)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "crm_sync",
                ExistingPeriodicWorkPolicy.KEEP,
                syncRequest,
            )
        }
    }
}
