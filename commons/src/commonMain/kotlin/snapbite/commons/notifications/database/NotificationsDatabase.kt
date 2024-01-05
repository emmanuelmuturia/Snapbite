package snapbite.commons.notifications.database

import androidx.room.Database
import androidx.room.RoomDatabase
import emmanuelmuturia.notifications.dao.NotificationDAO
import emmanuelmuturia.notifications.entity.NotificationEntity

@Database(entities = [NotificationEntity::class], version = 1, exportSchema = false)
abstract class NotificationsDatabase : RoomDatabase() { abstract fun notificationDAO(): NotificationDAO }