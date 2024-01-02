package emmanuelmuturia.repository

import emmanuelmuturia.daos.DayDAO
import emmanuelmuturia.entities.DayEntity
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