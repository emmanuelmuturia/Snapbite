package emmanuelmuturia.database

import androidx.room.Database
import androidx.room.RoomDatabase
import emmanuelmuturia.daos.DayDAO
import emmanuelmuturia.daos.FoodDAO
import emmanuelmuturia.entities.DayEntity
import emmanuelmuturia.entities.FoodEntity

@Database(entities = [DayEntity::class, FoodEntity::class], version = 1)
abstract class SnapbiteDatabase : RoomDatabase() {

    abstract fun dayDAO(): DayDAO

    abstract fun foodDAO(): FoodDAO

}