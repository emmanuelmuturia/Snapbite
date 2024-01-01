package emmanuelmuturia.repository

import emmanuelmuturia.entity.NotificationEntity

interface NotificationRepository {

    suspend fun addNotification(notificationEntity: NotificationEntity)

    suspend fun getAllNotifications(): List<NotificationEntity>

    suspend fun deleteNotification(notificationEntity: NotificationEntity)

}