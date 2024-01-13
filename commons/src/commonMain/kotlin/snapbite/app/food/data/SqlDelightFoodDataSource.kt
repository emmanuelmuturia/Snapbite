package snapbite.app.food.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock
import snapbite.app.core.data.ImageStorage
import snapbite.app.database.SnapbiteDatabase
import snapbite.app.food.domain.Food
import snapbite.app.food.domain.FoodDataSource

class SqlDelightFoodDataSource(
    snapbiteDatabase: SnapbiteDatabase,
    private val imageStorage: ImageStorage
) : FoodDataSource {

    private val queries = snapbiteDatabase.foodQueries

    override fun getFoods(): Flow<List<Food>> {
        return queries
            .getFoods()
            .asFlow()
            .mapToList(context = Dispatchers.IO)
            .map { foodEntities ->
                supervisorScope {
                    foodEntities.map {
                        async {
                            it.toFood(imageStorage = imageStorage)
                        }
                    }.map { it.await() }
                }
            }
    }

    override suspend fun insertFood(food: Food) {

        val foodImage = food.foodImage?.let {
            imageStorage.saveImage(bytes = it)
        }

        queries.insertFoodItem(
            foodId = food.foodId,
            foodName = food.foodName,
            foodImage = foodImage,
            foodCaption = food.foodCaption,
            foodEmoji = food.foodEmoji,
            foodDate = Clock.System.now().toEpochMilliseconds()
        )
    }

    override suspend fun deleteFood(foodId: Long) {
        val entity = queries.getFoodById(foodId = foodId).executeAsOne()
        entity.foodImage?.let {
            imageStorage.deleteImage(fileName = it)
        }
        queries.deleteFood(foodId = foodId)
    }

    override suspend fun getFoodById(foodId: Long) {
        queries.getFoodById(foodId = foodId)
    }

}
