package emmanuelmuturia.repository

import emmanuelmuturia.entities.DayEntity
import kotlinx.coroutines.flow.Flow

interface DayRepository {

    suspend fun insertDay(dayEntity: emmanuelmuturia.entities.DayEntity)

    fun getAllDays(): Flow<List<emmanuelmuturia.entities.DayEntity>>

    suspend fun getDayById(dayId: Int): emmanuelmuturia.entities.DayEntity

}