package snapbite.app.food.ui

import android.icu.util.Calendar
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import snapbite.app.core.ui.ImagePicker
import snapbite.app.food.components.AddFoodSheet
import snapbite.app.food.components.FoodDetailSheet
import snapbite.app.food.components.FoodListItem
import snapbite.app.food.components.SnapbiteBackgroundImage
import snapbite.app.food.domain.Food
import snapbite.app.theme.Caveat
import snapbite.app.theme.snapbiteMaroon
import snapbite.app.theme.snapbiteOrange

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FoodListScreen(
    state: FoodListState,
    newFood: Food?,
    onEvent: (FoodListEvent) -> Unit,
    imagePicker: ImagePicker,
    foodListViewModel: FoodListViewModel
) {

    imagePicker.registerPicker { imageBytes ->
        onEvent(FoodListEvent.OnFoodImagePicked(bytes = imageBytes))
    }

    val exitDialogState = rememberSaveable { mutableStateOf(value = false) }

    val context = LocalContext.current

    BackHandler(enabled = true) { exitDialogState.value = !exitDialogState.value }

    if (exitDialogState.value) {
        ExitConfirmationDialog(
            onConfirmExit = {
                (context as? ComponentActivity)?.finish()
            },
            onDismiss = {
                exitDialogState.value = false
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            HomeScreenHeader()

            Box(modifier = Modifier.weight(weight = 1f)) {
                if (state.foodList.isEmpty()) {
                    EmptyHomeScreenContent()
                } else {
                    FilledHomeScreenContent(state = state, onEvent = onEvent)
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
                        //.clickable(onClick = { navigator.push(item = SettingsScreen()) }),
                        , imageVector = Icons.Rounded.Settings,
                        tint = Color.Black,
                        contentDescription = "Settings Button"
                    )
                }

                item(key = 2) {

                    Icon(
                        modifier = Modifier
                            .size(size = 42.dp)
                            .clickable(onClick = {
                                onEvent(FoodListEvent.OnAddNewFoodClick)
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
                                /*navigator.push(
                                    item = ProfileScreen(
                                        userData = googleAuthUiClient.getSignedInUser()
                                    )
                                )*/
                            }),
                        imageVector = Icons.Rounded.AccountCircle,
                        tint = Color.Black,
                        contentDescription = "User Profile Button"
                    )
                }
            }

        }

    }

    FoodDetailSheet(
        isOpen = state.isSelectedFoodSheetOpen,
        selectedFood = state.selectedFood,
        onEvent = onEvent,
    )
    AddFoodSheet(
        state = state,
        newFood = newFood,
        isOpen = state.isAddFoodSheetOpen,
        onEvent = { event ->
            if (event is FoodListEvent.OnAddFoodImage) {
                imagePicker.pickImage()
            }
            onEvent(event)
        },
        foodListViewModel = foodListViewModel
    )

}


@Composable
fun HomeScreenHeader() {

    //val navigator = LocalNavigator.currentOrThrow

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
                //.clickable(onClick = { navigator.push(item = SearchScreen()) }),
                , imageVector = Icons.Rounded.Search,
                contentDescription = "Search Button",
                tint = Color.Black
            )

            Icon(
                modifier = Modifier
                    .size(size = 30.dp)
                //.clickable(onClick = { navigator.push(item = NotificationsScreen()) }),
                , imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications Button",
                tint = Color.Black
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FilledHomeScreenContent(state: FoodListState, onEvent: (FoodListEvent) -> Unit) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(state.foodList) { food ->
            FoodListItem(
                food = food,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(FoodListEvent.SelectFood(food = food))
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


@Composable
fun ExitConfirmationDialog(
    onConfirmExit: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 21.dp)),
        onDismissRequest = { onDismiss() },
        tonalElevation = 21.dp,
        text = {
            Text(
                text = "Are you sure you want to exit the app?",
                style = MaterialTheme.typography.titleLarge,
                lineHeight = 30.sp
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmExit()
                    onDismiss()
                },
                shape = RoundedCornerShape(size = 21.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = snapbiteMaroon)
            ) {
                Text(
                    text = "Yes",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                shape = RoundedCornerShape(size = 21.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = snapbiteMaroon)
            ) {
                Text(
                    text = "No",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        containerColor = snapbiteOrange,
        textContentColor = Color.Black,
        titleContentColor = Color.Black,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    )
}


private fun displayGreeting(): String {
    return when (Calendar.getInstance()[Calendar.HOUR_OF_DAY]) {
        in 0..11 -> "Good Morning!" // 0 to 11 is considered morning
        in 12..16 -> "Good Afternoon!" // 12 to 16 is considered afternoon
        else -> "Good Evening!" // After 16 is considered evening
    }
}