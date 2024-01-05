package emmanuelmuturia.settings

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.repository.SettingsRepository
import emmanuelmuturia.settings.SettingsRepositoryImplementation

@Module
@InstallIn(SingletonComponent::class)
object SettingsHiltModule {

    @Provides
    fun providesSettingsRepository(): SettingsRepository {
        return emmanuelmuturia.settings.SettingsRepositoryImplementation()
    }

}