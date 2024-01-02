package emmanuelmuturia.repository

import emmanuelmuturia.entities.DayEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getAllDays(): Flow<List<DayEntity>>

    suspend fun insertDay(dayEntity: DayEntity)

}