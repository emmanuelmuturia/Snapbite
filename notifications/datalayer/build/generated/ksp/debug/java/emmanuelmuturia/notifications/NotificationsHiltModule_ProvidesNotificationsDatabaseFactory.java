package emmanuelmuturia.notifications;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import emmanuelmuturia.database.NotificationsDatabase;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class NotificationsHiltModule_ProvidesNotificationsDatabaseFactory implements Factory<NotificationsDatabase> {
  private final Provider<Context> contextProvider;

  public NotificationsHiltModule_ProvidesNotificationsDatabaseFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public NotificationsDatabase get() {
    return providesNotificationsDatabase(contextProvider.get());
  }

  public static NotificationsHiltModule_ProvidesNotificationsDatabaseFactory create(
      Provider<Context> contextProvider) {
    return new NotificationsHiltModule_ProvidesNotificationsDatabaseFactory(contextProvider);
  }

  public static NotificationsDatabase providesNotificationsDatabase(Context context) {
    return Preconditions.checkNotNullFromProvides(NotificationsHiltModule.INSTANCE.providesNotificationsDatabase(context));
  }
}
