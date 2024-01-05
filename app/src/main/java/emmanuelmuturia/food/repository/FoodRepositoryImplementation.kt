package emmanuelmuturia.food.repository

import emmanuelmuturia.food.daos.FoodDAO
import emmanuelmuturia.food.entities.FoodEntity
import kotlinx.coroutines.flow.Flow

class FoodRepositoryImplementation(private val foodDAO: FoodDAO) : FoodRepository {
    override suspend fun insertFood(foodEntity: FoodEntity) {
        return foodDAO.insertFood(foodEntity = foodEntity)
    }

    override suspend fun updateFood(foodEntity: FoodEntity) {
        return foodDAO.updateFood(foodEntity = foodEntity)
    }

    override fun getAllFoods(): Flow<List<FoodEntity>> {
        return foodDAO.getAllFoods()
    }

    override suspend fun getFoodById(foodId: Int): FoodEntity {
        return foodDAO.getFoodById(foodId = foodId)
    }

}