package emmanuelmuturia.home

import emmanuelmuturia.food.entities.DayEntity
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun getAllDays(): Flow<List<emmanuelmuturia.food.entities.DayEntity>>

    suspend fun insertDay(dayEntity: emmanuelmuturia.food.entities.DayEntity)

}