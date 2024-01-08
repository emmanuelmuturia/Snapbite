package snapbite.app.notifications.domain

data class Notification(
    val notificationId: String,
    val notificationTitle: String?,
    val notificationBody: String?,
    val notificationTimestamp: Long
)
