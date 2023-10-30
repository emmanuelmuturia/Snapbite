package emmanuelmuturia.repository

import emmanuelmuturia.entities.DayEntity
import kotlinx.coroutines.flow.Flow

class DayRepositoryImplementation(private val dayDAO: emmanuelmuturia.daos.DayDAO) : DayRepository {
    override suspend fun insertDay(dayEntity: DayEntity) {
        return dayDAO.insertDay(dayEntity = dayEntity)
    }

    override fun getAllDays(): Flow<List<DayEntity>> {
        return dayDAO.getAllDays()
    }

    override suspend fun getDayById(dayId: Int): DayEntity {
        return dayDAO.getDayById(dayId = dayId)
    }
}