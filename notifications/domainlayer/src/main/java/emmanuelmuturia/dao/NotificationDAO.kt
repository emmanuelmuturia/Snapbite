package emmanuelmuturia.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import emmanuelmuturia.entity.NotificationEntity

@Dao
interface NotificationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotification(notificationEntity: NotificationEntity)

    @Query("SELECT * FROM notifications")
    suspend fun getAllNotifications(): List<NotificationEntity>

    /*@Query("UPDATE notifications SET isRead = 1")
    suspend fun markAllAsRead()*/

    @Delete
    suspend fun deleteNotification(notificationEntity: NotificationEntity)

}