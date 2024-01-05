package emmanuelmuturia.home

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.food.daos.DayDAO
import emmanuelmuturia.repository.HomeRepository
import emmanuelmuturia.home.HomeRepositoryImplementation

@Module
@InstallIn(SingletonComponent::class)
object HomeHiltModule {

    @Provides
    fun providesHomeRepository(dayDAO: emmanuelmuturia.food.daos.DayDAO): HomeRepository {
        return emmanuelmuturia.home.HomeRepositoryImplementation(dayDAO = dayDAO)
    }

}