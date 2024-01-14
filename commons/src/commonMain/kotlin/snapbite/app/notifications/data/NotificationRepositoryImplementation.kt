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
import timber.log.Timber

class NotificationRepositoryImplementation(
    snapbiteDatabase: SnapbiteDatabase
) : NotificationRepository {

    private val queries = snapbiteDatabase.notificationsQueries

    override suspend fun addNotification(notification: Notification) {
        try {
            queries.insertNotification(
                notificationId = notification.notificationId,
                notificationTitle = notification.notificationTitle!!,
                notificationBody = notification.notificationBody!!,
                notificationTimestamp = notification.notificationTimestamp
            )
        } catch (e: Exception) {
            Timber.tag(tag = "Notification Insertion Exception")
                .e(
                    message = "Could not insert the notification due to: %s",
                    e.message
                )
        }
    }

    override fun getAllNotifications(): Flow<List<Notification>> {
        return try {
            queries
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
        } catch (e: Exception) {
            Timber.tag(tag = "Notification Deletion Exception")
                .e(
                    message = "Could not delete the notification due to: %s",
                    e.message
                )
            throw e
        }
    }

    override suspend fun deleteNotification(notification: Notification) {
        try {
            queries.deleteNotification(
                notificationId = notification.notificationId
            )
        } catch (e: Exception) {
            Timber.tag(tag = "Notification Deletion Exception")
                .e(
                    message = "Could not delete the notification due to: %s",
                    e.message
                )
        }
    }

}
