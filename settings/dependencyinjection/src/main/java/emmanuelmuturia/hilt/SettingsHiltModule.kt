package emmanuelmuturia.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.repository.SettingsRepository
import emmanuelmuturia.repository.SettingsRepositoryImplementation

@Module
@InstallIn(SingletonComponent::class)
object SettingsHiltModule {

    @Provides
    fun providesSettingsRepository(): SettingsRepository {
        return SettingsRepositoryImplementation()
    }

}