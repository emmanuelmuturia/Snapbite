package snapbite.app.food.domain

import kotlinx.coroutines.flow.Flow

interface FoodDataSource {

    fun getFoods(): Flow<List<Food>>

    suspend fun insertFood(food: Food)

    suspend fun deleteFood(foodId: Long)

}