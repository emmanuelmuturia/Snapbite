package snapbite.commons.food.repository

import emmanuelmuturia.food.entities.DayEntity
import kotlinx.coroutines.flow.Flow

interface DayRepository {

    suspend fun insertDay(dayEntity: DayEntity)

    fun getAllDays(): Flow<List<DayEntity>>

    suspend fun getDayById(dayId: Int): DayEntity

}