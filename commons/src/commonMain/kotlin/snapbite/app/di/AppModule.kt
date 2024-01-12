package snapbite.app.di

import snapbite.app.about.domain.AboutRepository
import snapbite.app.food.domain.FoodDataSource
import snapbite.app.notifications.domain.NotificationRepository

expect class AppModule {

    val foodDataSource: FoodDataSource

    val notificationRepository: NotificationRepository

    val aboutRepository: AboutRepository

}
