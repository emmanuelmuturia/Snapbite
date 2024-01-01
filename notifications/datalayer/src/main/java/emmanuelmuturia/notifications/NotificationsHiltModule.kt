package emmanuelmuturia.notifications

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.dao.NotificationDAO
import emmanuelmuturia.database.NotificationsDatabase
import emmanuelmuturia.repository.NotificationRepository
import emmanuelmuturia.repository.NotificationRepositoryImplementation
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationsHiltModule {

    @Provides
    @Singleton
    fun providesNotificationsDatabase(@ApplicationContext context: Context): NotificationsDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = NotificationsDatabase::class.java,
            name = "notificationsDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun providesNotificationDao(notificationsDatabase: NotificationsDatabase): NotificationDAO {
        return notificationsDatabase.notificationDAO()
    }

    @Provides
    fun providesNotificationsRepository(notificationDAO: NotificationDAO): NotificationRepository {
        return NotificationRepositoryImplementation(notificationDAO = notificationDAO)
    }

}