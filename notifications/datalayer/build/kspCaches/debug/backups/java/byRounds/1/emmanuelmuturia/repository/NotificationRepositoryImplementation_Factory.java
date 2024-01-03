package emmanuelmuturia.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import emmanuelmuturia.dao.NotificationDAO;
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
public final class NotificationRepositoryImplementation_Factory implements Factory<NotificationRepositoryImplementation> {
  private final Provider<NotificationDAO> notificationDAOProvider;

  public NotificationRepositoryImplementation_Factory(
      Provider<NotificationDAO> notificationDAOProvider) {
    this.notificationDAOProvider = notificationDAOProvider;
  }

  @Override
  public NotificationRepositoryImplementation get() {
    return newInstance(notificationDAOProvider.get());
  }

  public static NotificationRepositoryImplementation_Factory create(
      Provider<NotificationDAO> notificationDAOProvider) {
    return new NotificationRepositoryImplementation_Factory(notificationDAOProvider);
  }

  public static NotificationRepositoryImplementation newInstance(NotificationDAO notificationDAO) {
    return new NotificationRepositoryImplementation(notificationDAO);
  }
}
