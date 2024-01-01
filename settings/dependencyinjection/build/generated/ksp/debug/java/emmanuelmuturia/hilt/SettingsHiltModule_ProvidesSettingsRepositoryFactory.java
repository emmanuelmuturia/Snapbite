package emmanuelmuturia.hilt;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import emmanuelmuturia.repository.SettingsRepository;
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
public final class SettingsHiltModule_ProvidesSettingsRepositoryFactory implements Factory<SettingsRepository> {
  @Override
  public SettingsRepository get() {
    return providesSettingsRepository();
  }

  public static SettingsHiltModule_ProvidesSettingsRepositoryFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SettingsRepository providesSettingsRepository() {
    return Preconditions.checkNotNullFromProvides(SettingsHiltModule.INSTANCE.providesSettingsRepository());
  }

  private static final class InstanceHolder {
    private static final SettingsHiltModule_ProvidesSettingsRepositoryFactory INSTANCE = new SettingsHiltModule_ProvidesSettingsRepositoryFactory();
  }
}
