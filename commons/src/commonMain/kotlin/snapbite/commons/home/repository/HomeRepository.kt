package snapbite.commons.home.repository

import emmanuelmuturia.food.entities.DayEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getAllDays(): Flow<List<DayEntity>>

    suspend fun insertDay(dayEntity: DayEntity)

}