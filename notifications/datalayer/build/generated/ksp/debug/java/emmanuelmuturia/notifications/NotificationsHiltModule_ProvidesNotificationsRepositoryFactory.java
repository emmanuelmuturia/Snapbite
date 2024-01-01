package emmanuelmuturia.notifications;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import emmanuelmuturia.dao.NotificationDAO;
import emmanuelmuturia.repository.NotificationRepository;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class NotificationsHiltModule_ProvidesNotificationsRepositoryFactory implements Factory<NotificationRepository> {
  private final Provider<NotificationDAO> notificationDAOProvider;

  public NotificationsHiltModule_ProvidesNotificationsRepositoryFactory(
      Provider<NotificationDAO> notificationDAOProvider) {
    this.notificationDAOProvider = notificationDAOProvider;
  }

  @Override
  public NotificationRepository get() {
    return providesNotificationsRepository(notificationDAOProvider.get());
  }

  public static NotificationsHiltModule_ProvidesNotificationsRepositoryFactory create(
      Provider<NotificationDAO> notificationDAOProvider) {
    return new NotificationsHiltModule_ProvidesNotificationsRepositoryFactory(notificationDAOProvider);
  }

  public static NotificationRepository providesNotificationsRepository(
      NotificationDAO notificationDAO) {
    return Preconditions.checkNotNullFromProvides(NotificationsHiltModule.INSTANCE.providesNotificationsRepository(notificationDAO));
  }
}
