package snapbite.app.dependencyinjection

import snapbite.app.food.domain.FoodDataSource
import snapbite.app.food.domain.FoodRepository
import snapbite.app.notifications.domain.NotificationRepository

expect class AppModule {

    val foodDataSource: FoodDataSource

    val notificationRepository: NotificationRepository

    val foodRepository: FoodRepository

}