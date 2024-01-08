package snapbite.app.food.data

import snapbite.app.core.data.ImageStorage
import snapbite.app.database.FoodEntity
import snapbite.app.food.domain.Food

suspend fun FoodEntity.toFood(imageStorage: ImageStorage): Food {
    return Food(
        foodId = foodId,
        foodName = foodName,
        foodCaption = foodCaption,
        foodImage = foodImage?.let { imageStorage.getImage(fileName = it) },
        foodDate = foodDate,
        foodEmoji = foodEmoji
    )
}
