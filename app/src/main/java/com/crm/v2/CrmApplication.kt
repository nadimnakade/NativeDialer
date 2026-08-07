package com.crm.v2

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CrmApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        // Notification channels require API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService(NotificationManager::class.java)

            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_CALLS,
                    getString(R.string.notification_channel_calls),
                    NotificationManager.IMPORTANCE_HIGH
                ).apply {
                    description = "Active call and call status notifications"
                    setShowBadge(true)
                }
            )

            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_FOLLOWUPS,
                    getString(R.string.notification_channel_followups),
                    NotificationManager.IMPORTANCE_DEFAULT
                ).apply {
                    description = "Follow-up reminder notifications"
                    setShowBadge(true)
                }
            )

            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_SYNC,
                    getString(R.string.notification_channel_sync),
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "Background data sync notifications"
                    setShowBadge(false)
                }
            )

            notificationManager.createNotificationChannel(
                NotificationChannel(
                    CHANNEL_FOREGROUND,
                    getString(R.string.notification_channel_foreground),
                    NotificationManager.IMPORTANCE_LOW
                ).apply {
                    description = "Foreground service indicator for active calls"
                    setShowBadge(false)
                }
            )
        }
    }

    companion object {
        const val CHANNEL_CALLS = "crm_calls"
        const val CHANNEL_FOLLOWUPS = "crm_followups"
        const val CHANNEL_SYNC = "crm_sync"
        const val CHANNEL_FOREGROUND = "crm_foreground"
    }
}
