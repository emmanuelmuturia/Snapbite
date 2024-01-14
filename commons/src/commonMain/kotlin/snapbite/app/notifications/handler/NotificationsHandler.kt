package snapbite.app.notifications.handler

import android.content.Intent
import snapbite.app.notifications.domain.Notification
import snapbite.app.notifications.domain.NotificationRepository

class NotificationsHandler(private val notificationRepository: NotificationRepository) {

    suspend fun handleNotificationIntent(
        intent: Intent
    ) {

        val extras = intent.extras
        if (extras != null) {
            val notificationId = extras.getString("notificationId")
            val notificationTitle = extras.getString("notificationTitle")
            val notificationBody = extras.getString("notificationBody")

            if (notificationId != null) {
                saveNotification(
                    notificationId = notificationId,
                    notificationTitle = notificationTitle,
                    notificationBody = notificationBody
                )
            }
        }

    }


    private suspend fun saveNotification(
        notificationId: String,
        notificationTitle: String?,
        notificationBody: String?,
        notificationTimestamp: Long = System.currentTimeMillis()
    ) {

        notificationRepository.addNotification(
            notification = Notification(
                notificationId = notificationId,
                notificationTitle = notificationTitle,
                notificationBody = notificationBody,
                notificationTimestamp = notificationTimestamp
            )
        )

    }

}