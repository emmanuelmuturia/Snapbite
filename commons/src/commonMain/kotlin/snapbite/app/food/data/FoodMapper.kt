package snapbite.app.food.data

import snapbite.app.core.data.ImageStorage
import snapbite.app.database.FoodEntity

suspend fun FoodEntity.toFood(imageStorage: ImageStorage): snapbite.app.food.domain.FoodEntity {
    return snapbite.app.food.domain.FoodEntity(
        foodId = foodId,
        foodName = foodName,
        foodCaption = foodCaption,
        foodImage = foodImage?.let { imageStorage.getImage(fileName = it) },
        foodDate = foodDate,
        foodEmoji = foodEmoji
    )
}
