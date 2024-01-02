package emmanuelmuturia.firebasecloudmessaging;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import emmanuelmuturia.repository.NotificationRepository;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class FCMService_MembersInjector implements MembersInjector<FCMService> {
  private final Provider<NotificationRepository> notificationRepositoryProvider;

  public FCMService_MembersInjector(
      Provider<NotificationRepository> notificationRepositoryProvider) {
    this.notificationRepositoryProvider = notificationRepositoryProvider;
  }

  public static MembersInjector<FCMService> create(
      Provider<NotificationRepository> notificationRepositoryProvider) {
    return new FCMService_MembersInjector(notificationRepositoryProvider);
  }

  @Override
  public void injectMembers(FCMService instance) {
    injectNotificationRepository(instance, notificationRepositoryProvider.get());
  }

  @InjectedFieldSignature("emmanuelmuturia.firebasecloudmessaging.FCMService.notificationRepository")
  public static void injectNotificationRepository(FCMService instance,
      NotificationRepository notificationRepository) {
    instance.notificationRepository = notificationRepository;
  }
}
