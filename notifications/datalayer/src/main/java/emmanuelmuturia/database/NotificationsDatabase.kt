package emmanuelmuturia.database

import androidx.room.Database
import androidx.room.RoomDatabase
import emmanuelmuturia.dao.NotificationDAO
import emmanuelmuturia.entity.NotificationEntity

@Database(entities = [NotificationEntity::class], version = 1, exportSchema = false)
abstract class NotificationsDatabase : RoomDatabase() { abstract fun notificationDAO(): NotificationDAO }