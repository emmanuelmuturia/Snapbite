package snapbite.app.di

import android.content.Context
import com.google.ai.client.generativeai.GenerativeModel
import snapbite.app.BuildConfig
import snapbite.app.about.data.AboutRepositoryImplementation
import snapbite.app.about.domain.AboutRepository
import snapbite.app.core.data.DatabaseDriverFactory
import snapbite.app.core.data.ImageStorage
import snapbite.app.database.SnapbiteDatabase
import snapbite.app.faq.data.FAQRepositoryImplementation
import snapbite.app.faq.domain.FAQRepository
import snapbite.app.food.data.FoodRepositoryImplementation
import snapbite.app.food.data.SqlDelightFoodDataSource
import snapbite.app.food.domain.FoodDataSource
import snapbite.app.food.domain.FoodRepository
import snapbite.app.notifications.data.NotificationRepositoryImplementation
import snapbite.app.notifications.domain.NotificationRepository
import snapbite.app.settings.data.SettingsRepositoryImplementation
import snapbite.app.settings.domain.SettingsRepository

actual class AppModule(
    private val context: Context
) {

    actual val foodDataSource: FoodDataSource by lazy {
        SqlDelightFoodDataSource(
            snapbiteDatabase = SnapbiteDatabase(
                driver = DatabaseDriverFactory(context = context).createSnapbiteDatabase()
            ),
            imageStorage = ImageStorage(context = context)
        )
    }

    actual val notificationRepository: NotificationRepository by lazy {
        NotificationRepositoryImplementation(
            snapbiteDatabase = SnapbiteDatabase(
                driver = DatabaseDriverFactory(context = context).createSnapbiteDatabase()
            )
        )
    }

    actual val aboutRepository: AboutRepository by lazy {
        AboutRepositoryImplementation(context = context)
    }

    actual val faqRepository: FAQRepository by lazy {
        FAQRepositoryImplementation()
    }

    actual val settingsRepository: SettingsRepository by lazy {
        SettingsRepositoryImplementation(context = context)
    }

    actual val foodRepository: FoodRepository by lazy {
        FoodRepositoryImplementation(
            generativeModel = GenerativeModel(
                modelName = "gemini-pro-vision",
                apiKey = BuildConfig.geminiApiKey,
            )
        )
    }

}
