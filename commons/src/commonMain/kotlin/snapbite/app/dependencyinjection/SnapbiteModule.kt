package snapbite.app.dependencyinjection

import com.google.ai.client.generativeai.GenerativeModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import snapbite.app.BuildConfig
import snapbite.app.about.data.AboutRepositoryImplementation
import snapbite.app.about.domain.AboutRepository
import snapbite.app.about.ui.AboutScreenViewModel
import snapbite.app.core.data.DatabaseDriverFactory
import snapbite.app.core.data.ImageStorage
import snapbite.app.database.SnapbiteDatabase
import snapbite.app.faq.data.FAQRepositoryImplementation
import snapbite.app.faq.domain.FAQRepository
import snapbite.app.faq.ui.FAQScreenViewModel
import snapbite.app.food.data.FoodRepositoryImplementation
import snapbite.app.food.data.SqlDelightFoodDataSource
import snapbite.app.food.domain.FoodDataSource
import snapbite.app.food.domain.FoodRepository
import snapbite.app.food.ui.FoodListViewModel
import snapbite.app.notifications.data.NotificationRepositoryImplementation
import snapbite.app.notifications.domain.NotificationRepository
import snapbite.app.notifications.ui.NotificationsScreenViewModel
import snapbite.app.settings.data.SettingsRepositoryImplementation
import snapbite.app.settings.domain.SettingsRepository
import snapbite.app.settings.ui.SettingsScreenViewModel

val snapbiteModule = module {

    single<SnapbiteDatabase> {
        SnapbiteDatabase(
            driver = DatabaseDriverFactory(context = get()).createSnapbiteDatabase()
        )
    }

    single<ImageStorage> {
        ImageStorage(context = get())
    }

    single<FoodDataSource> {
        SqlDelightFoodDataSource(
        snapbiteDatabase = get(),
        imageStorage = get()
    ) }

    single<GenerativeModel> {
        GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = BuildConfig.geminiApiKey,
        )
    }

    single<AboutRepository> { AboutRepositoryImplementation(context = get()) }
    viewModel { AboutScreenViewModel(aboutRepository = get()) }

    single<FAQRepository> { FAQRepositoryImplementation() }
    viewModel { FAQScreenViewModel(faqRepository = get()) }

    single<FoodRepository> { FoodRepositoryImplementation(generativeModel = get()) }
    viewModel { FoodListViewModel(foodDataSource = get(), foodRepository = get()) }

    single<NotificationRepository> { NotificationRepositoryImplementation(snapbiteDatabase = get()) }
    viewModel { NotificationsScreenViewModel(notificationRepository = get()) }

    single<SettingsRepository> { SettingsRepositoryImplementation(context = get()) }
    viewModel { SettingsScreenViewModel(settingsRepository = get()) }

}