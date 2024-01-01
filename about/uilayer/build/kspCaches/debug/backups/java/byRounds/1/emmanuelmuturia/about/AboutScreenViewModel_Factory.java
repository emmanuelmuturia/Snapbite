package emmanuelmuturia.about;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import emmanuelmuturia.about.repository.AboutRepository;
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
public final class AboutScreenViewModel_Factory implements Factory<AboutScreenViewModel> {
  private final Provider<Application> applicationProvider;

  private final Provider<AboutRepository> aboutRepositoryProvider;

  public AboutScreenViewModel_Factory(Provider<Application> applicationProvider,
      Provider<AboutRepository> aboutRepositoryProvider) {
    this.applicationProvider = applicationProvider;
    this.aboutRepositoryProvider = aboutRepositoryProvider;
  }

  @Override
  public AboutScreenViewModel get() {
    return newInstance(applicationProvider.get(), aboutRepositoryProvider.get());
  }

  public static AboutScreenViewModel_Factory create(Provider<Application> applicationProvider,
      Provider<AboutRepository> aboutRepositoryProvider) {
    return new AboutScreenViewModel_Factory(applicationProvider, aboutRepositoryProvider);
  }

  public static AboutScreenViewModel newInstance(Application application,
      AboutRepository aboutRepository) {
    return new AboutScreenViewModel(application, aboutRepository);
  }
}
