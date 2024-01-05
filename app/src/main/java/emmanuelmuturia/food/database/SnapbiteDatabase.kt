package emmanuelmuturia.food.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import emmanuelmuturia.food.converter.SnapbiteTypeConverter
import emmanuelmuturia.food.daos.DayDAO
import emmanuelmuturia.food.daos.FoodDAO
import emmanuelmuturia.food.entities.DayEntity
import emmanuelmuturia.food.entities.FoodEntity

@Database(entities = [DayEntity::class, FoodEntity::class], version = 1, exportSchema = false)
@TypeConverters(SnapbiteTypeConverter::class)
abstract class SnapbiteDatabase : RoomDatabase() {

    abstract fun dayDAO(): DayDAO

    abstract fun foodDAO(): FoodDAO

    companion object {
        @Volatile
        private var instance: SnapbiteDatabase? = null

        fun getInstance(context: Context): SnapbiteDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): SnapbiteDatabase {
            return Room.databaseBuilder(
                context = context.applicationContext,
                klass = SnapbiteDatabase::class.java,
                name = "snapbiteDatabase"
            ).build()
        }
    }
}