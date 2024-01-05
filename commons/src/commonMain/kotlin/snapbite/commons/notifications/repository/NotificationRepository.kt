package snapbite.commons.notifications.repository

import emmanuelmuturia.notifications.entity.NotificationEntity

interface NotificationRepository {

    suspend fun addNotification(notificationEntity: NotificationEntity)

    suspend fun getAllNotifications(): List<NotificationEntity>

    suspend fun deleteNotification(notificationEntity: NotificationEntity)

}