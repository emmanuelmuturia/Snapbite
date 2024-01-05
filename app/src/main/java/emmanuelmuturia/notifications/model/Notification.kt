package emmanuelmuturia.notifications.model

data class Notification(
    val notificationId: String,
    val notificationTitle: String,
    val notificationBody: String,
    val notificationTimestamp: Long,
    var isRead: Boolean = false
)
