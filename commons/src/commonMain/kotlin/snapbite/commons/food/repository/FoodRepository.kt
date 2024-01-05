package snapbite.commons.food.repository

import emmanuelmuturia.food.entities.FoodEntity
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    suspend fun insertFood(foodEntity: FoodEntity)

    suspend fun updateFood(foodEntity: FoodEntity)

    fun getAllFoods(): Flow<List<FoodEntity>>

    suspend fun getFoodById(foodId: Int): FoodEntity

}