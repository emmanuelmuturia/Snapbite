package emmanuelmuturia.hilt

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.daos.DayDAO
import emmanuelmuturia.daos.FoodDAO
import emmanuelmuturia.database.SnapbiteDatabase
import emmanuelmuturia.repository.DayRepository
import emmanuelmuturia.repository.DayRepositoryImplementation
import emmanuelmuturia.repository.FoodRepository
import emmanuelmuturia.repository.FoodRepositoryImplementation
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodHiltModule {

    @Provides
    @Singleton
    fun providesSnapbiteDatabase(@ApplicationContext context: Context): SnapbiteDatabase {
        return Room.databaseBuilder(
                context = context.applicationContext,
                klass = SnapbiteDatabase::class.java,
                name = "snapbiteDatabase"
            ).build()
    }

    @Provides
    @Singleton
    fun providesFoodDao(snapbiteDatabase: SnapbiteDatabase): FoodDAO {
        return snapbiteDatabase.foodDAO()
    }

    @Provides
    fun providesFoodRepository(foodDAO: FoodDAO): FoodRepository {
        return FoodRepositoryImplementation(foodDAO = foodDAO)
    }

    @Provides
    fun providesDayRepository(dayDAO: DayDAO): DayRepository {
        return DayRepositoryImplementation(dayDAO = dayDAO)
    }

    @Provides
    fun providesDayDao(snapbiteDatabase: SnapbiteDatabase): DayDAO {
        return snapbiteDatabase.dayDAO()
    }

}