package emmanuelmuturia.notifications.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import emmanuelmuturia.notifications.dao.NotificationDAO
import emmanuelmuturia.notifications.entity.NotificationEntity

@Database(entities = [NotificationEntity::class], version = 1, exportSchema = false)
abstract class NotificationsDatabase : RoomDatabase() {

    abstract fun notificationDAO(): NotificationDAO

    companion object {
        @Volatile
        private var instance: NotificationsDatabase? = null

        fun getInstance(context: Context): NotificationsDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): NotificationsDatabase {
            return Room.databaseBuilder(
                context = context.applicationContext,
                klass = NotificationsDatabase::class.java,
                name = "notificationsDatabase"
            ).build()
        }
    }
}