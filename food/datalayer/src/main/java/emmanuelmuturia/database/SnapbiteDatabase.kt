package emmanuelmuturia.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import emmanuelmuturia.daos.FoodDAO
import emmanuelmuturia.daos.DayDAO
import emmanuelmuturia.entities.DayEntity
import emmanuelmuturia.entities.FoodEntity

@Database(entities = [DayEntity::class, FoodEntity::class], version = 1)
abstract class SnapbiteDatabase : RoomDatabase() {

    abstract fun dayDAO(): DayDAO

    abstract fun foodDAO(): FoodDAO

    companion object {

        @Volatile
        private var snapbiteDatabaseInstance: SnapbiteDatabase? = null

        fun getSnapbiteDatabase(context: Context): SnapbiteDatabase {
            return snapbiteDatabaseInstance ?: synchronized(lock = this) {
                val snapbiteDbInstance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = SnapbiteDatabase::class.java,
                    name = "snapbiteDatabase"
                ).build()
                snapbiteDatabaseInstance = snapbiteDbInstance
                snapbiteDbInstance
            }
        }

    }

}