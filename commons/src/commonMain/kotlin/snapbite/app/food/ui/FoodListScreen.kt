package snapbite.app.food.ui

import android.icu.util.Calendar
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.koin.androidx.compose.koinViewModel
import snapbite.app.core.ui.ImagePicker
import snapbite.app.core.ui.ImagePickerFactory
import snapbite.app.commons.FoodListItem
import snapbite.app.commons.SnapbiteBackgroundImage
import snapbite.app.commons.backHandler
import snapbite.app.notifications.ui.NotificationsScreen
import snapbite.app.profile.ui.SignInScreen
import snapbite.app.search.SearchScreen
import snapbite.app.settings.ui.SettingsScreen
import snapbite.app.theme.Caveat

class FoodListScreen : Screen {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {

        val imagePicker: ImagePicker = ImagePickerFactory().createPicker()

        val foodListViewModel: FoodListViewModel = koinViewModel()

        val onEvent: (FoodListEvent) -> Unit = foodListViewModel::onEvent

        val state: FoodListState by foodListViewModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        val foodList by foodListViewModel.foods.collectAsState()

        val exitDialogState = rememberSaveable { mutableStateOf(value = false) }

        backHandler(onBack = {
            exitDialogState.value = !exitDialogState.value
        }, onDismiss = {
            exitDialogState.value = false
        }, exitDialogState = exitDialogState.value)

        LaunchedEffect(key1 = foodList) {}

        Box(modifier = Modifier.fillMaxSize()) {

            SnapbiteBackgroundImage()

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                HomeScreenHeader()

                Box(modifier = Modifier.weight(weight = 1f)) {
                    if (foodList.isEmpty()) {
                        EmptyHomeScreenContent()
                    } else {
                        FilledHomeScreenContent(
                            foodListViewModel = foodListViewModel,
                            onEvent = onEvent,
                            imagePicker = imagePicker,
                            state = state
                        )
                    }
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 21.dp),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    item(key = 1) {
                        Icon(
                            modifier = Modifier
                                .size(size = 40.dp)
                                .clickable(onClick = { navigator.push(item = SettingsScreen()) }),
                            imageVector = Icons.Rounded.Settings,
                            tint = Color.Black,
                            contentDescription = "Settings Button"
                        )
                    }

                    item(key = 2) {

                        Icon(
                            modifier = Modifier
                                .size(size = 42.dp)
                                .clickable(onClick = {
                                    foodListViewModel.onEvent(event = FoodListEvent.OnAddNewFoodClick)
                                    navigator.push(
                                        item = AddNewFood(
                                            imagePicker = imagePicker,
                                            foodListViewModel = foodListViewModel,
                                            onEvent = { event ->
                                                if (event is FoodListEvent.OnAddFoodImage) {
                                                    imagePicker.pickImage()
                                                }
                                                onEvent(event)
                                            }
                                        )
                                    )
                                }),
                            imageVector = Icons.Rounded.AddCircle,
                            tint = Color.Black,
                            contentDescription = "Add Food Entry Button"
                        )
                    }

                    item(key = 3) {
                        Icon(
                            modifier = Modifier
                                .size(size = 40.dp)
                                .clickable(onClick = {
                                    navigator.push(
                                        item = SignInScreen()
                                    )
                                }),
                            imageVector = Icons.Rounded.AccountCircle,
                            tint = Color.Black,
                            contentDescription = "User Profile Button"
                        )
                    }
                }

            }

        }

    }

}


@Composable
fun HomeScreenHeader() {

    val navigator = LocalNavigator.currentOrThrow

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp, start = 14.dp, end = 14.dp, bottom = 21.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = displayGreeting(),
            textAlign = TextAlign.Start,
            fontFamily = Caveat,
            fontSize = 28.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 21.dp)
                    .size(size = 30.dp)
                    .clickable(onClick = { navigator.push(item = SearchScreen()) }),
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search Button",
                tint = Color.Black
            )

            Icon(
                modifier = Modifier
                    .size(size = 30.dp)
                    .clickable(onClick = {
                        navigator.push(
                            item = NotificationsScreen()
                        )
                    }),
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications Button",
                tint = Color.Black
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FilledHomeScreenContent(
    foodListViewModel: FoodListViewModel,
    onEvent: (FoodListEvent) -> Unit,
    imagePicker: ImagePicker,
    state: FoodListState
) {

    val foodList by foodListViewModel.foods.collectAsState()

    val navigator = LocalNavigator.currentOrThrow

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(foodList) { food ->

            FoodListItem(
                food = food,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(FoodListEvent.SelectFood(food = food))
                        navigator.push(
                            item = EditFood(
                                selectedFood = food,
                                onEvent = onEvent,
                                modifier = Modifier,
                                state = state,
                                imagePicker = imagePicker,
                                foodListViewModel = foodListViewModel
                            )
                        )
                    }
                    .padding(horizontal = 16.dp)
            )
        }
    }

}


@Composable
fun EmptyHomeScreenContent() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        item {
            Text(
                text = "You have no food items...",
                style = MaterialTheme.typography.titleLarge
            )
        }

    }

}


private fun displayGreeting(): String {
    return when (Calendar.getInstance()[Calendar.HOUR_OF_DAY]) {
        in 0..11 -> "Good Morning!" // 0 to 11 is considered morning
        in 12..16 -> "Good Afternoon!" // 12 to 16 is considered afternoon
        else -> "Good Evening!" // After 16 is considered evening
    }
}