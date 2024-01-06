package snapbite.app.food.data

import kotlinx.coroutines.flow.Flow
import snapbite.app.food.domain.Food
import snapbite.app.food.domain.FoodDataSource

class SqlDelightFoodDataSource(

) : FoodDataSource {

    override fun getFoods(): Flow<List<Food>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertFood(food: Food) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFood(foodId: Long) {
        TODO("Not yet implemented")
    }

}