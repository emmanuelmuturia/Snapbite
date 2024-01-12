package snapbite.app

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import cafe.adriel.voyager.navigator.Navigator
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import snapbite.app.about.ui.AboutScreenViewModel
import snapbite.app.core.theme.SnapbiteTheme
import snapbite.app.core.ui.ImagePicker
import snapbite.app.di.AppModule
import snapbite.app.faq.ui.FAQScreenViewModel
import snapbite.app.food.ui.FoodListScreen
import snapbite.app.food.ui.FoodListViewModel
import snapbite.app.settings.ui.SettingsScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    imagePicker: ImagePicker
) {

    SnapbiteTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {

        val context = LocalContext.current

        val appModule = AppModule(context = context)

        val foodListViewModel: FoodListViewModel = getViewModel(
            key = "food-list-viewModel",
            factory = viewModelFactory {
                FoodListViewModel(
                    foodDataSource = appModule.foodDataSource,
                    foodRepository = appModule.foodRepository
                )
            }
        )

        val aboutScreenViewModel: AboutScreenViewModel = getViewModel(
            key = "aboutScreenViewModel",
            factory = viewModelFactory<AboutScreenViewModel> {
                AboutScreenViewModel(aboutRepository = appModule.aboutRepository)
            }
        )

        val faqScreenViewModel: FAQScreenViewModel = getViewModel(
            key = "faqScreenViewModel",
            factory = viewModelFactory<FAQScreenViewModel> {
                FAQScreenViewModel(faqRepository = appModule.faqRepository)
            }
        )

        val settingsScreenViewModel: SettingsScreenViewModel = getViewModel(
            key = "settingsScreenViewModel",
            factory = viewModelFactory<SettingsScreenViewModel> {
                SettingsScreenViewModel(settingsRepository = appModule.settingsRepository)
            }
        )

        val state by foodListViewModel.state.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {

            Navigator(
                screen = FoodListScreen(
                    state = state,
                    imagePicker = imagePicker,
                    foodListViewModel = foodListViewModel,
                    onEvent = foodListViewModel::onEvent,
                    aboutScreenViewModel = aboutScreenViewModel,
                    faqScreenViewModel = faqScreenViewModel,
                    settingsScreenViewModel = settingsScreenViewModel
                )
            )

        }

    }

}
