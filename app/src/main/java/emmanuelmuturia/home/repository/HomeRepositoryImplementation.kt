package emmanuelmuturia.home.repository

import emmanuelmuturia.food.daos.DayDAO
import emmanuelmuturia.food.entities.DayEntity
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImplementation(
    private val dayDAO: DayDAO
) : HomeRepository {

    override fun getAllDays(): Flow<List<DayEntity>> {
        return dayDAO.getAllDays()
    }

    override suspend fun insertDay(dayEntity: DayEntity) {
        dayDAO.insertDay(dayEntity = dayEntity)
    }

}