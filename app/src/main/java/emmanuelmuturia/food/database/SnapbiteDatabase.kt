package emmanuelmuturia.food.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import emmanuelmuturia.food.converter.SnapbiteTypeConverter
import emmanuelmuturia.daos.DayDAO
import emmanuelmuturia.daos.FoodDAO
import emmanuelmuturia.entities.DayEntity
import emmanuelmuturia.entities.FoodEntity

@Database(entities = [DayEntity::class, FoodEntity::class], version = 1, exportSchema = false)
@TypeConverters(SnapbiteTypeConverter::class)
abstract class SnapbiteDatabase : RoomDatabase() {

    abstract fun dayDAO(): DayDAO

    abstract fun foodDAO(): FoodDAO

}