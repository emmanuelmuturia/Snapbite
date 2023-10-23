package emmanuelmuturia.hilt

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.home.HomeScreenViewModel

@Module
@InstallIn(SingletonComponent::class)
object HomeHiltModule {

    @Provides
    fun providesHomeScreenViewModel(application: Application): HomeScreenViewModel {
        return HomeScreenViewModel(application = application)
    }

}