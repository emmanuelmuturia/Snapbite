package emmanuelmuturia.repository

import emmanuelmuturia.daos.FoodDAO
import emmanuelmuturia.entities.FoodEntity
import kotlinx.coroutines.flow.Flow

class FoodRepositoryImplementation(private val foodDAO: FoodDAO) : FoodRepository {
    override suspend fun insertFood(foodEntity: FoodEntity) {
        return foodDAO.insertFood(foodEntity = foodEntity)
    }

    override fun getAllFoods(dayId: Int): Flow<List<FoodEntity>> {
        return foodDAO.getAllFoods(dayId = dayId)
    }

    override suspend fun getFoodById(foodId: Int): FoodEntity {
        return foodDAO.getFoodById(foodId = foodId)
    }
}