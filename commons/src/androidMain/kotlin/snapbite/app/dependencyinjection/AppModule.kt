package snapbite.app.dependencyinjection

import android.content.Context
import com.google.ai.client.generativeai.GenerativeModel
import snapbite.app.BuildConfig
import snapbite.app.core.data.DatabaseDriverFactory
import snapbite.app.core.data.ImageStorage
import snapbite.app.database.SnapbiteDatabase
import snapbite.app.food.data.FoodRepositoryImplementation
import snapbite.app.food.data.SqlDelightFoodDataSource
import snapbite.app.food.domain.FoodDataSource
import snapbite.app.food.domain.FoodRepository
import snapbite.app.notifications.data.NotificationRepositoryImplementation
import snapbite.app.notifications.domain.NotificationRepository

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

    actual val foodRepository: FoodRepository by lazy {
        FoodRepositoryImplementation(generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = BuildConfig.geminiApiKey,
        )
        )
    }

}