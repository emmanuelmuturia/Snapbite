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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import snapbite.app.core.theme.SnapbiteTheme
import snapbite.app.core.ui.ImagePicker
import snapbite.app.di.AppModule
import snapbite.app.food.ui.FoodListScreen
import snapbite.app.food.ui.FoodListViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    appModule: AppModule,
    imagePicker: ImagePicker
) {

    SnapbiteTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {

        val foodListViewModel: FoodListViewModel = getViewModel(
            key = "food-list-viewModel",
            factory = viewModelFactory {
                FoodListViewModel(foodDataSource = appModule.foodDataSource)
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
                    //newFood = foodListViewModel.newFood,
                    imagePicker = imagePicker,
                    foodListViewModel = foodListViewModel,
                    appModule = appModule,
                    onEvent = foodListViewModel::onEvent
                )
            )

        }

    }

}
