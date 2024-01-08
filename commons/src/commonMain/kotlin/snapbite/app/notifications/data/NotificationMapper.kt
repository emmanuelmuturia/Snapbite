package snapbite.app.notifications.data

import snapbite.app.database.NotificationEntity
import snapbite.app.notifications.domain.Notification

suspend fun NotificationEntity.toNotification(): Notification {

    return Notification(
        notificationId = notificationId,
        notificationTitle = notificationTitle,
        notificationBody = notificationBody,
        notificationTimestamp = notificationTimestamp
    )

}
