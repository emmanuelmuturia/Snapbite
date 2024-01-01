package emmanuelmuturia.notifications;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import emmanuelmuturia.dao.NotificationDAO;
import emmanuelmuturia.database.NotificationsDatabase;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class NotificationsHiltModule_ProvidesNotificationDaoFactory implements Factory<NotificationDAO> {
  private final Provider<NotificationsDatabase> notificationsDatabaseProvider;

  public NotificationsHiltModule_ProvidesNotificationDaoFactory(
      Provider<NotificationsDatabase> notificationsDatabaseProvider) {
    this.notificationsDatabaseProvider = notificationsDatabaseProvider;
  }

  @Override
  public NotificationDAO get() {
    return providesNotificationDao(notificationsDatabaseProvider.get());
  }

  public static NotificationsHiltModule_ProvidesNotificationDaoFactory create(
      Provider<NotificationsDatabase> notificationsDatabaseProvider) {
    return new NotificationsHiltModule_ProvidesNotificationDaoFactory(notificationsDatabaseProvider);
  }

  public static NotificationDAO providesNotificationDao(
      NotificationsDatabase notificationsDatabase) {
    return Preconditions.checkNotNullFromProvides(NotificationsHiltModule.INSTANCE.providesNotificationDao(notificationsDatabase));
  }
}
