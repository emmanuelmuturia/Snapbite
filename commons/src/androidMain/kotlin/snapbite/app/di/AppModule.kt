package snapbite.app.di

import android.content.Context
import snapbite.app.core.data.DatabaseDriverFactory
import snapbite.app.core.data.ImageStorage
import snapbite.app.database.FoodDatabase
import snapbite.app.food.data.SqlDelightFoodDataSource
import snapbite.app.food.domain.FoodDataSource

actual class AppModule(
    private val context: Context
) {

    actual val foodDataSource: FoodDataSource by lazy {
        SqlDelightFoodDataSource(
            foodDatabase = FoodDatabase(
                driver = DatabaseDriverFactory(context = context).create()
            ),
            imageStorage = ImageStorage(context = context)
        )
    }

}