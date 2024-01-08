package snapbite.app.notifications.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import snapbite.app.database.SnapbiteDatabase
import snapbite.app.notifications.domain.Notification
import snapbite.app.notifications.domain.NotificationRepository

class NotificationRepositoryImplementation(
    snapbiteDatabase: SnapbiteDatabase
) : NotificationRepository {

    private val queries = snapbiteDatabase.notificationsQueries

    override suspend fun addNotification(notification: Notification) {
        queries.insertNotification(
            notificationId = notification.notificationId,
            notificationTitle = notification.notificationTitle!!,
            notificationBody = notification.notificationBody!!,
            notificationTimestamp = notification.notificationTimestamp
        )
    }

    override fun getAllNotifications(): Flow<List<Notification>> {
        return queries
            .getAllNotifications()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { notificationsEntities ->
                supervisorScope {
                    notificationsEntities.map {
                        async {
                            it.toNotification()
                        }
                    }.map { it.await() }
                }
            }
    }

    override suspend fun deleteNotification(notification: Notification) {
        queries.deleteNotification(
            notificationId = notification.notificationId
        )
    }

}
