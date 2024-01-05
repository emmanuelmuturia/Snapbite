package emmanuelmuturia.dependencyinjection

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import emmanuelmuturia.about.repository.AboutRepository
import emmanuelmuturia.about.repository.AboutRepositoryImplementation
import emmanuelmuturia.about.ui.AboutScreenViewModel
import emmanuelmuturia.faq.repository.FAQRepository
import emmanuelmuturia.faq.repository.FAQRepositoryImplementation
import emmanuelmuturia.faq.ui.FAQScreenViewModel
import emmanuelmuturia.food.ui.FoodScreenViewModel
import emmanuelmuturia.food.repository.FoodRepository
import emmanuelmuturia.food.repository.FoodRepositoryImplementation
import emmanuelmuturia.home.repository.HomeRepository
import emmanuelmuturia.home.repository.HomeRepositoryImplementation
import emmanuelmuturia.notifications.dao.NotificationDAO
import emmanuelmuturia.notifications.database.NotificationsDatabase
import emmanuelmuturia.notifications.repository.NotificationRepository
import emmanuelmuturia.notifications.repository.NotificationRepositoryImplementation
import emmanuelmuturia.notifications.ui.NotificationsScreenViewModel
import emmanuelmuturia.profile.ui.ProfileScreenViewModel
import emmanuelmuturia.search.ui.SearchScreenViewModel
import emmanuelmuturia.settings.repository.SettingsRepository
import emmanuelmuturia.settings.repository.SettingsRepositoryImplementation
import emmanuelmuturia.settings.ui.SettingsScreenViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val snapbiteModule = module {

    single<AboutRepository> { AboutRepositoryImplementation() }
    viewModel { (application: Application) -> AboutScreenViewModel(application, get()) }

    single<FAQRepository> { FAQRepositoryImplementation(get()) }
    viewModel { (application: Application) -> FAQScreenViewModel(application, get()) }

    single<HomeRepository> { HomeRepositoryImplementation(get()) }

    single<NotificationRepository> { NotificationRepositoryImplementation(get()) }
    viewModel { (application: Application) -> NotificationsScreenViewModel(application, get()) }
    single<NotificationDAO> { NotificationsDatabase.getInstance(context = androidContext()).notificationDAO() }

    viewModel { (application: Application) -> ProfileScreenViewModel(application) }
    viewModel { (application: Application) -> SearchScreenViewModel(application) }

    single<SettingsRepository> { SettingsRepositoryImplementation() }
    viewModel { (application: Application) -> SettingsScreenViewModel(application, get()) }

    single<FirebaseFirestore> { FirebaseFirestore.getInstance() }

    single<FoodRepository> { FoodRepositoryImplementation(get()) }
    viewModel { FoodScreenViewModel(androidApplication(), get()) }

}