package emmanuelmuturia.home.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.core.app.ActivityCompat
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.gms.auth.api.identity.Identity
import emmanuelmuturia.food.day.DayScreenViewModel
import emmanuelmuturia.food.photography.PhotoScreen
import emmanuelmuturia.food.ui.EditFoodScreen
import emmanuelmuturia.notifications.ui.NotificationsScreen
import emmanuelmuturia.profile.google.GoogleAuthUiClient
import emmanuelmuturia.profile.ui.ProfileScreen
import emmanuelmuturia.search.ui.SearchScreen
import emmanuelmuturia.settings.ui.SettingsScreen
import org.koin.androidx.compose.koinViewModel
import emmanuelmuturia.commons.components.SnapbiteBackgroundImage
import emmanuelmuturia.commons.state.ErrorScreen
import emmanuelmuturia.commons.state.LoadingScreen
import emmanuelmuturia.commons.state.SnapbiteState
import emmanuelmuturia.commons.theme.snapbiteMaroon
import emmanuelmuturia.commons.theme.snapbiteOrange
import java.time.LocalDate


class HomeScreen : Screen {

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        val context = LocalContext.current

        val googleAuthUiClient by lazy {
            GoogleAuthUiClient(
                oneTapClient = Identity.getSignInClient(context)
            )
        }

        val dayScreenViewModel: DayScreenViewModel = koinViewModel()

        val cameraPermissionQueue = dayScreenViewModel.visiblePermissionDialogQueue

        val cameraPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = { isGranted ->
                dayScreenViewModel.onPermissionResult(
                    permission = Manifest.permission.CAMERA,
                    isGranted = isGranted
                )
                if (isGranted) {
                    navigator.push(item = PhotoScreen(dayScreenViewModel = dayScreenViewModel))
                }
            }
        )

        val foodList by dayScreenViewModel.foodList.collectAsState()

        val foodState by dayScreenViewModel.daysState.collectAsState()

        val exitDialogState = rememberSaveable { mutableStateOf(value = false) }

        val isLoading by dayScreenViewModel.isLoading.collectAsState()

        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

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

        when (foodState) {

            is SnapbiteState.Error -> ErrorScreen()
            is SnapbiteState.Loading -> LoadingScreen()
            else -> SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = dayScreenViewModel::refreshFoodList,
                indicator = { state, refreshTrigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = refreshTrigger,
                        backgroundColor = snapbiteOrange,
                        contentColor = Color.Black
                    )
                }) {

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
                                FilledHomeScreenContent()
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
                                            //navController.navigate(route = "photoScreen")
                                            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
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
                                                item = ProfileScreen(
                                                    userData = googleAuthUiClient.getSignedInUser()
                                                )
                                            )
                                        }),
                                    imageVector = Icons.Rounded.AccountCircle,
                                    tint = Color.Black,
                                    contentDescription = "User Profile Button"
                                )
                            }
                        }

                        cameraPermissionQueue.reversed().forEach { permission ->
                            emmanuelmuturia.food.day.PermissionDialog(
                                permissionTextProvider = when (permission) {
                                    Manifest.permission.CAMERA -> emmanuelmuturia.food.day.CameraPermissionTextProvider()
                                    else -> return@forEach
                                },
                                isPermanentlyDeclined = !ActivityCompat.shouldShowRequestPermissionRationale(
                                    LocalContext.current as Activity, permission
                                ),
                                onDismiss = dayScreenViewModel::dismissDialog,
                                onOkClick = { dayScreenViewModel.dismissDialog() },
                                onGoToAppSettingsClick = { (context as Activity).openAppSettings() }

                            )
                        }

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
            fontFamily = emmanuelmuturia.commons.theme.Caveat,
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
                    .clickable(onClick = { navigator.push(item = NotificationsScreen()) }),
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications Button",
                tint = Color.Black
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FoodCard(foodEntity: emmanuelmuturia.food.entities.FoodEntity, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(height = 121.dp)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(7.dp),
        colors = CardDefaults.cardColors(containerColor = snapbiteOrange)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Use SpaceBetween here
        ) {
            Column(
                modifier = Modifier.padding(7.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = formatCurrentDate(), style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(height = 7.dp))
                Text(text = foodEntity.foodName, style = MaterialTheme.typography.bodyLarge)
            }

            // Move the Box outside the Column and use horizontal alignment
            Box(
                modifier = Modifier
                    .background(color = Color.Transparent)
                    .padding(end = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(text = foodEntity.foodEmoji)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FilledHomeScreenContent() {

    val navigator = LocalNavigator.currentOrThrow

    val dayScreenViewModel: DayScreenViewModel = koinViewModel()

    val foodList by dayScreenViewModel.foodList.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 7.dp)
    ) {

        items(foodList) { food ->
            FoodCard(
                foodEntity = food
            ) {
                /*navController.navigate(
                    route = "editFoodScreen/${food.foodId}"
                )*/
                navigator.push(item = EditFoodScreen(foodId = food.foodId))
            }
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

@RequiresApi(Build.VERSION_CODES.O)
fun formatCurrentDate(): String {
    val currentDate = LocalDate.now()
    val dayOfMonth = currentDate.dayOfMonth
    val month =
        currentDate.month.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH)

    val daySuffix = when (dayOfMonth) {
        1, 21, 31 -> "st"
        2, 22 -> "nd"
        3, 23 -> "rd"
        else -> "th"
    }

    return "$dayOfMonth$daySuffix $month"
}

private fun displayGreeting(): String {
    return when (Calendar.getInstance()[Calendar.HOUR_OF_DAY]) {
        in 0..11 -> "Good Morning!" // 0 to 11 is considered morning
        in 12..16 -> "Good Afternoon!" // 12 to 16 is considered afternoon
        else -> "Good Evening!" // After 16 is considered evening
    }
}

fun Activity.openAppSettings() {
    Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts("package", packageName, null)
    ).also(::startActivity)
}