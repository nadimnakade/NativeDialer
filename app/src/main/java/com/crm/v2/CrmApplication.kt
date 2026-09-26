package com.crm.v2

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import com.crm.v2.data.config.CloudConnectConfigProvider
import com.crm.v2.data.config.ExtensionConfigProvider
import com.crm.v2.data.datastore.AuthDataStore
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class CrmApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var cloudConnectConfigProvider: CloudConnectConfigProvider

    @Inject
    lateinit var extensionConfigProvider: ExtensionConfigProvider

    @Inject
    lateinit var authDataStore: AuthDataStore

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(android.util.Log.DEBUG)
            .build()

    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
        loadCloudConnectConfig()
    }

    private fun loadCloudConnectConfig() {
        applicationScope.launch {
            try {
                cloudConnectConfigProvider.fetchConfig()
                Log.d("CLOUD_CONNECT", "XML config loaded at startup")

                if (authDataStore.isLoggedIn) {
                    val userKey = authDataStore.userKey?.toIntOrNull() ?: 0
                    if (userKey > 0) {
                        extensionConfigProvider.fetchAndApplyExtension(userKey)
                        extensionConfigProvider.markAsNewDialer(userKey)
                        Log.d("CLOUD_CONNECT", "Extension loaded for existing session: empCode=$userKey")
                    }
                }
            } catch (e: Exception) {
                Log.e("CLOUD_CONNECT", "Failed to load config at startup: ${e.message}", e)
            }
        }
    }

    private fun createNotificationChannels() {
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
