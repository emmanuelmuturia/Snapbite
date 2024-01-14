package snapbite.app.food.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import snapbite.app.core.data.ImageStorage
import snapbite.app.database.SnapbiteDatabase
import snapbite.app.food.domain.FoodDataSource
import snapbite.app.food.domain.FoodEntity

class SqlDelightFoodDataSource(
    snapbiteDatabase: SnapbiteDatabase,
    private val imageStorage: ImageStorage
) : FoodDataSource {

    private val queries = snapbiteDatabase.foodQueries

    override fun getFoods(): Flow<List<FoodEntity>> {
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

    override suspend fun insertFood(food: FoodEntity) {

        val foodImage = food.foodImage?.let {
            imageStorage.saveImage(bytes = it)
        }

        queries.insertFoodItem(
            foodId = food.foodId,
            foodName = food.foodName,
            foodImage = foodImage,
            foodCaption = food.foodCaption,
            foodEmoji = food.foodEmoji,
            foodDate = 7L
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
