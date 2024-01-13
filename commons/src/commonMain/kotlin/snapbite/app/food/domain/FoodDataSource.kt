package snapbite.app.food.domain

import kotlinx.coroutines.flow.Flow

interface FoodDataSource {

    fun getFoods(): Flow<List<FoodEntity>>

    suspend fun insertFood(food: FoodEntity)

    suspend fun deleteFood(foodId: Long)

    suspend fun getFoodById(foodId: Long)

}