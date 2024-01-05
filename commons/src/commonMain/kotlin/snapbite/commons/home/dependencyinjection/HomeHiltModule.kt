package snapbite.commons.home.dependencyinjection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.home.repository.HomeRepository
import emmanuelmuturia.home.repository.HomeRepositoryImplementation

@Module
@InstallIn(SingletonComponent::class)
object HomeHiltModule {

    @Provides
    fun providesHomeRepository(dayDAO: emmanuelmuturia.food.daos.DayDAO): HomeRepository {
        return HomeRepositoryImplementation(dayDAO = dayDAO)
    }

}