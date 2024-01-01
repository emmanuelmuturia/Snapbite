package emmanuelmuturia.about;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import emmanuelmuturia.about.repository.AboutRepository;
import javax.annotation.processing.Generated;

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
public final class AboutHiltModule_ProvidesAboutRepositoryFactory implements Factory<AboutRepository> {
  @Override
  public AboutRepository get() {
    return providesAboutRepository();
  }

  public static AboutHiltModule_ProvidesAboutRepositoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AboutRepository providesAboutRepository() {
    return Preconditions.checkNotNullFromProvides(AboutHiltModule.INSTANCE.providesAboutRepository());
  }

  private static final class InstanceHolder {
    private static final AboutHiltModule_ProvidesAboutRepositoryFactory INSTANCE = new AboutHiltModule_ProvidesAboutRepositoryFactory();
  }
}
