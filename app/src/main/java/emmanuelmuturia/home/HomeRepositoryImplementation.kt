package emmanuelmuturia.home

import emmanuelmuturia.food.daos.DayDAO
import emmanuelmuturia.food.entities.DayEntity
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImplementation(
    private val dayDAO: emmanuelmuturia.food.daos.DayDAO
) : HomeRepository {

    override fun getAllDays(): Flow<List<emmanuelmuturia.food.entities.DayEntity>> {
        return dayDAO.getAllDays()
    }

    override suspend fun insertDay(dayEntity: emmanuelmuturia.food.entities.DayEntity) {
        dayDAO.insertDay(dayEntity = dayEntity)
    }

}