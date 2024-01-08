package snapbite.app.notifications.domain

import kotlinx.coroutines.flow.Flow

interface NotificationRepository {

    suspend fun addNotification(notification: Notification)

    fun getAllNotifications(): Flow<List<Notification>>

    suspend fun deleteNotification(notification: Notification)

}