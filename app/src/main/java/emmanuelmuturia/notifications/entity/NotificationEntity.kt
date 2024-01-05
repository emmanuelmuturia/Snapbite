package emmanuelmuturia.notifications.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.Nonnull

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey
    val notificationId: String,
    val notificationTitle: String?,
    val notificationBody: String?,
    val notificationTimestamp: Long
)
