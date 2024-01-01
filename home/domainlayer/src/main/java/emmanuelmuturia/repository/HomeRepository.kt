package emmanuelmuturia.repository

import emmanuelmuturia.entities.DayEntity
import kotlinx.coroutines.flow.Flow

fun interface HomeRepository {

    fun getAllDays(): Flow<List<DayEntity>>

}