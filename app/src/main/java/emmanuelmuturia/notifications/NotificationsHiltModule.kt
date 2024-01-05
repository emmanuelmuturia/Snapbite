package emmanuelmuturia.notifications

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.notifications.dao.NotificationDAO
import emmanuelmuturia.notifications.database.NotificationsDatabase
import emmanuelmuturia.notifications.repository.NotificationRepository
import emmanuelmuturia.notifications.repository.NotificationRepositoryImplementation
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationsHiltModule {

    @Provides
    @Singleton
    fun providesNotificationsDatabase(@ApplicationContext context: Context): emmanuelmuturia.notifications.database.NotificationsDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = emmanuelmuturia.notifications.database.NotificationsDatabase::class.java,
            name = "notificationsDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun providesNotificationDao(notificationsDatabase: emmanuelmuturia.notifications.database.NotificationsDatabase): NotificationDAO {
        return notificationsDatabase.notificationDAO()
    }

    @Provides
    fun providesNotificationsRepository(notificationDAO: NotificationDAO): NotificationRepository {
        return emmanuelmuturia.notifications.repository.NotificationRepositoryImplementation(
            notificationDAO = notificationDAO
        )
    }

}