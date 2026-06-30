package com.crm.v2.services

import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.crm.v2.CrmApplication
import com.crm.v2.R
import com.crm.v2.data.datastore.AuthDataStore
import com.crm.v2.presentation.MainActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CrmFirebaseMessagingService : FirebaseMessagingService() {

    @Inject
    lateinit var authDataStore: AuthDataStore

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        CoroutineScope(Dispatchers.IO).launch {
            authDataStore.fcmToken = token
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification?.title ?: message.data["title"] ?: "CRM Notification"
        val body = message.notification?.body ?: message.data["body"] ?: ""
        val type = message.data["type"] ?: "general"

        showNotification(title, body, type)
    }

    private fun showNotification(title: String, body: String, type: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra("notification_type", type)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
        )

        val channelId = when (type) {
            "call" -> CrmApplication.CHANNEL_CALLS
            "followup" -> CrmApplication.CHANNEL_FOLLOWUPS
            "sync" -> CrmApplication.CHANNEL_SYNC
            else -> CrmApplication.CHANNEL_FOLLOWUPS
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()

        NotificationManagerCompat.from(this).notify(System.currentTimeMillis().toInt(), notification)
    }
}
