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
import org.koin.androidx.compose.koinViewModel
import snapbite.app.core.theme.SnapbiteTheme
import snapbite.app.core.ui.ImagePicker
import snapbite.app.dependencyinjection.AppModule
import snapbite.app.food.ui.FoodListScreen
import snapbite.app.food.ui.FoodListViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {

    SnapbiteTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {

        val foodListViewModel: FoodListViewModel = koinViewModel()

        val state by foodListViewModel.state.collectAsState()

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.Transparent
        ) {

            Navigator(
                screen = FoodListScreen()
            )

        }

    }

}
