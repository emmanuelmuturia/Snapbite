package snapbite.app.notifications.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import snapbite.app.notifications.domain.Notification
import snapbite.app.notifications.domain.NotificationRepository
import timber.log.Timber
import javax.inject.Inject

class FCMService : FirebaseMessagingService() {

    @Inject
    lateinit var notificationRepository: NotificationRepository

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Timber.tag(tag = "This is the Remote Message:").d(message = remoteMessage.data.toString())

        val notificationEntity = remoteMessage.data.toNotificationEntity()

        Timber.tag(tag = "This is the Notification Entity").d(message = notificationEntity.toString())

        CoroutineScope(Dispatchers.IO).launch {
            sendNotification(
                notificationTitle = remoteMessage.notification?.title ?: "",
                notificationBody = remoteMessage.notification?.body ?: "",
                applicationContext
            )
            notificationRepository.addNotification(notification = notificationEntity)
        }
    }

    private fun sendNotification(
        notificationTitle: String,
        notificationBody: String,
        context: Context
    ) {
        val notificationManager =
            ContextCompat.getSystemService(
                context,
                NotificationManager::class.java
            ) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "emmanuelmuturia"
            val channelName = "Emmanuel Muturia™"
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(context, "emmanuelmuturia")
            //.setSmallIcon(R.drawable.notification)
            .setContentTitle(notificationTitle)
            .setContentText(notificationBody)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        notificationManager.notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
    }

    private fun Map<String, String>.toNotificationEntity(): Notification {
        return Notification(
            notificationId = this["notificationId"] ?: "",
            notificationTitle = this["notificationTitle"] ?: "",
            notificationBody = this["notificationBody"] ?: "",
            notificationTimestamp = System.currentTimeMillis()
        )
    }

}