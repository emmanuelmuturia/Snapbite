package snapbite.commons.food.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import emmanuelmuturia.food.entities.DayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(dayEntity: DayEntity)

    @Query(value = "SELECT * FROM snapbiteDays ORDER BY dayDate DESC")
    fun getAllDays(): Flow<List<DayEntity>>

    @Query(value = "SELECT * FROM snapbiteDays WHERE dayId = :dayId")
    suspend fun getDayById(dayId: Int): DayEntity

}