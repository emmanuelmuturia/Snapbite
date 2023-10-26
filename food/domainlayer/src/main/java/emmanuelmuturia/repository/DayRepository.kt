package emmanuelmuturia.repository

import androidx.room.Query
import emmanuelmuturia.entities.DayEntity
import kotlinx.coroutines.flow.Flow

interface DayRepository {

    suspend fun insertDay(dayEntity: DayEntity)

    fun getAllDays(): Flow<List<DayEntity>>

    suspend fun deleteDays()

    suspend fun getDayById(dayId: Int): DayEntity

}