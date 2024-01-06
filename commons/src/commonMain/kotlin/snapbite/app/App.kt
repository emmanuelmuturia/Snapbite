package snapbite.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import snapbite.app.core.theme.SnapbiteTheme
import snapbite.app.food.ui.FoodListScreen
import snapbite.app.food.ui.FoodListViewModel

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {

    SnapbiteTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {

        val foodListViewModel: FoodListViewModel = getViewModel(
            key = "food-list-viewModel",
            factory = viewModelFactory {
                FoodListViewModel()
            }
        )

        val state by foodListViewModel.state.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {

            FoodListScreen(
                state = state,
                newFood = foodListViewModel.newFood,
                onEvent = foodListViewModel::onEvent
            )

        }

    }

}