package snapbite.app.di

import snapbite.app.food.domain.FoodDataSource

expect class AppModule {

    val foodDataSource: FoodDataSource

}