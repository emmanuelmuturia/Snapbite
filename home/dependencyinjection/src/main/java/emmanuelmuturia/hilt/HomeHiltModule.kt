package emmanuelmuturia.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.daos.DayDAO
import emmanuelmuturia.repository.HomeRepository
import emmanuelmuturia.repository.HomeRepositoryImplementation

@Module
@InstallIn(SingletonComponent::class)
object HomeHiltModule {

    @Provides
    fun providesHomeRepository(dayDAO: DayDAO): HomeRepository {
        return HomeRepositoryImplementation(dayDAO = dayDAO)
    }

}