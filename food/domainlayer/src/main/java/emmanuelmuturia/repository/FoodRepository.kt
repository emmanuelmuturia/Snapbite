package emmanuelmuturia.repository

import emmanuelmuturia.entities.FoodEntity
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    suspend fun insertFood(foodEntity: FoodEntity)

    suspend fun updateFood(foodEntity: FoodEntity)

    fun getAllFoods(dayId: Int): Flow<List<FoodEntity>>

    suspend fun getFoodById(foodId: Int): FoodEntity

}