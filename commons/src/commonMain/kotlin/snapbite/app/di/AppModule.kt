package snapbite.app.di

import snapbite.app.about.domain.AboutRepository
import snapbite.app.faq.domain.FAQRepository
import snapbite.app.food.domain.FoodDataSource
import snapbite.app.food.domain.FoodRepository
import snapbite.app.notifications.domain.NotificationRepository
import snapbite.app.settings.domain.SettingsRepository

expect class AppModule {

    val foodDataSource: FoodDataSource

    val notificationRepository: NotificationRepository

    val aboutRepository: AboutRepository

    val faqRepository: FAQRepository

    val settingsRepository: SettingsRepository

    val foodRepository: FoodRepository

}
