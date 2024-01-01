package emmanuelmuturia.home

import android.icu.util.Calendar
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import emmanuelmuturia.components.SnapbiteBackgroundImage
import emmanuelmuturia.entities.DayEntity
import emmanuelmuturia.state.ErrorScreen
import emmanuelmuturia.state.LoadingScreen
import emmanuelmuturia.state.SnapbiteState
import emmanuelmuturia.theme.Caveat
import emmanuelmuturia.theme.snapbiteMaroon
import emmanuelmuturia.theme.snapbiteOrange


@Composable
fun HomeScreen(navController: NavHostController) {

    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()

    val dayList by homeScreenViewModel.daysList.collectAsStateWithLifecycle()

    val daysState by homeScreenViewModel.daysState.collectAsStateWithLifecycle()

    val exitDialogState = rememberSaveable { mutableStateOf(value = false) }

    val context = LocalContext.current

    val isLoading by homeScreenViewModel.isLoading.collectAsStateWithLifecycle()

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

    when(daysState) {

        is SnapbiteState.Error -> ErrorScreen()
        is SnapbiteState.Loading -> LoadingScreen()
        else -> SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = homeScreenViewModel::refreshDaysList,
            indicator = { state, refreshTrigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = refreshTrigger,
                    backgroundColor = snapbiteOrange,
                    contentColor = Color.Black
                )
            }) {

            dayList.takeIf { it.isNotEmpty() }?.let {
               FilledHomeScreen(navController = navController)
            } ?: EmptyHomeScreen(navController = navController)

        }

    }

}


@Composable
fun FilledHomeScreen(
    navController: NavHostController
) {

    val homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
    val dayList = homeScreenViewModel.daysList.collectAsState(initial = listOf())

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            HomeScreenHeader(navController = navController)

            // Lazy Column with FoodCard items...
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp)
                    .weight(weight = 1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                items(dayList.value) { day ->
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        DayCard(dayEntity = day)
                    }
                }
            }
        }

    }

    HomeScreenFooter(navController = navController, dayEntity = null)

}

@Composable
fun EmptyHomeScreen(
    navController: NavHostController
) {

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

        Column {

            HomeScreenHeader(navController = navController)

            Spacer(modifier = Modifier.weight(weight = 1f))

            HomeScreenFooter(navController = navController, dayEntity = null)

        }

    }

}


@Composable
fun HomeScreenHeader(navController: NavHostController) {

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
                    .clickable { navController.navigate(route = "searchScreen") },
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search Button",
                tint = Color.Black
            )

            Icon(
                modifier = Modifier
                    .size(size = 30.dp)
                    .clickable { navController.navigate(route = "notificationsScreen") },
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Notifications Button",
                tint = Color.Black
            )
        }
    }
}


@Composable
fun DayCard(dayEntity: DayEntity) {
    Card(
        modifier = Modifier
            .height(height = 121.dp)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
        elevation = CardDefaults.cardElevation(7.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Red)
    ) {
        Column(
            modifier = Modifier.padding(7.dp),
            verticalArrangement = Arrangement.spacedBy(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${dayEntity.dayDate}", textAlign = TextAlign.Start,
                fontFamily = Caveat,
                fontSize = 21.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun HomeScreenFooter(navController: NavHostController, dayEntity: DayEntity?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 21.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Icon(
            modifier = Modifier
                .size(size = 40.dp)
                .clickable { navController.navigate(route = "settingsScreen") },
            imageVector = Icons.Rounded.Settings,
            tint = Color.Black,
            contentDescription = "Settings Button"
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        Icon(
            modifier = Modifier
                .size(size = 42.dp)
                .clickable { navController.navigate(route = "dayScreen/${dayEntity?.dayId}") },
            imageVector = Icons.Rounded.AddCircle,
            tint = Color.Black,
            contentDescription = "Add Food Entry Button"
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        Icon(
            modifier = Modifier
                .size(size = 40.dp)
                .clickable { navController.navigate(route = "profileScreen") },
            imageVector = Icons.Rounded.AccountCircle,
            tint = Color.Black,
            contentDescription = "User Profile Button"
        )
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
