package emmanuelmuturia.food

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.daos.DayDAO
import emmanuelmuturia.daos.FoodDAO
import emmanuelmuturia.food.database.SnapbiteDatabase
import emmanuelmuturia.repository.DayRepository
import emmanuelmuturia.food.repository.DayRepositoryImplementation
import emmanuelmuturia.repository.FoodRepository
import emmanuelmuturia.food.repository.FoodRepositoryImplementation
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodHiltModule {

    @Provides
    @Singleton
    fun providesSnapbiteDatabase(@ApplicationContext context: Context): emmanuelmuturia.food.database.SnapbiteDatabase {
        return Room.databaseBuilder(
                context = context.applicationContext,
                klass = emmanuelmuturia.food.database.SnapbiteDatabase::class.java,
                name = "snapbiteDatabase"
            ).build()
    }

    @Provides
    @Singleton
    fun providesFoodDao(snapbiteDatabase: emmanuelmuturia.food.database.SnapbiteDatabase): FoodDAO {
        return snapbiteDatabase.foodDAO()
    }

    @Provides
    fun providesFoodRepository(foodDAO: FoodDAO): FoodRepository {
        return emmanuelmuturia.food.repository.FoodRepositoryImplementation(foodDAO = foodDAO)
    }

    @Provides
    fun providesDayRepository(dayDAO: DayDAO): DayRepository {
        return emmanuelmuturia.food.repository.DayRepositoryImplementation(dayDAO = dayDAO)
    }

    @Provides
    fun providesDayDao(snapbiteDatabase: emmanuelmuturia.food.database.SnapbiteDatabase): DayDAO {
        return snapbiteDatabase.dayDAO()
    }

}