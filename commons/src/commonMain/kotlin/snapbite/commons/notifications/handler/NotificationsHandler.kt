package snapbite.commons.notifications.handler

import android.content.Intent
import emmanuelmuturia.notifications.entity.NotificationEntity
import emmanuelmuturia.notifications.repository.NotificationRepository

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


    suspend fun saveNotification(
        notificationId: String,
        notificationTitle: String?,
        notificationBody: String?,
        notificationTimestamp: Long = System.currentTimeMillis()
    ) {

        notificationRepository.addNotification(
            notificationEntity = NotificationEntity(
                notificationId = notificationId,
                notificationTitle = notificationTitle,
                notificationBody = notificationBody,
                notificationTimestamp = notificationTimestamp
            )
        )

    }

}