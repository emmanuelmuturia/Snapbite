package snapbite.commons.about.dependencyinjection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.about.AboutRepository

@Module
@InstallIn(SingletonComponent::class)
object AboutHiltModule {

    @Provides
    fun providesAboutRepository(): emmanuelmuturia.about.AboutRepository {
        return emmanuelmuturia.about.AboutRepositoryImplementation()
    }

}