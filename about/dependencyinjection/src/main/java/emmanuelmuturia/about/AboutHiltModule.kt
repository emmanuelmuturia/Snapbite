package emmanuelmuturia.about

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.about.repository.AboutRepository

@Module
@InstallIn(SingletonComponent::class)
object AboutHiltModule {

    @Provides
    fun providesAboutRepository(): AboutRepository {
        return AboutRepositoryImplementation()
    }

}