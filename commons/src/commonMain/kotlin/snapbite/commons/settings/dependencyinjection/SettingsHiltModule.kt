package snapbite.commons.settings.dependencyinjection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.settings.repository.SettingsRepository
import emmanuelmuturia.settings.repository.SettingsRepositoryImplementation

@Module
@InstallIn(SingletonComponent::class)
object SettingsHiltModule {

    @Provides
    fun providesSettingsRepository(): SettingsRepository {
        return SettingsRepositoryImplementation()
    }

}