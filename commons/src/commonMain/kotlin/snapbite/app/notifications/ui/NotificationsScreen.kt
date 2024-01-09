package snapbite.app.notifications.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import snapbite.app.commons.SnapbiteState
import snapbite.app.commons.ErrorScreen
import snapbite.app.commons.LoadingScreen
import snapbite.app.commons.SnapbiteHeader
import snapbite.app.di.AppModule
import snapbite.app.food.components.SnapbiteBackgroundImage
import snapbite.app.notifications.domain.Notification
import java.text.SimpleDateFormat
import java.util.Locale

data class NotificationsScreen(val appModule: AppModule) : Screen {


    @Composable
    override fun Content() {

        val notificationsScreenViewModel: NotificationsScreenViewModel  = getViewModel(
            key = "notificationsScreenViewModel",
            factory = viewModelFactory<NotificationsScreenViewModel> {
                NotificationsScreenViewModel(notificationRepository = appModule.notificationRepository)
            }
        )

        val notificationsList by notificationsScreenViewModel.notificationsList.collectAsState()

        val notificationsState by notificationsScreenViewModel.notificationsState.collectAsState()

        when (notificationsState) {

            is SnapbiteState.Error -> ErrorScreen()
            is SnapbiteState.Loading -> LoadingScreen()
            else -> Box(modifier = Modifier.fillMaxSize()) {

                SnapbiteBackgroundImage()

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    SnapbiteHeader(
                        headerTitle = "Notifications"
                    )

                    if (notificationsList.isNotEmpty()) {
                        NotificationScreenContent(notificationsScreenViewModel = notificationsScreenViewModel)
                    } else {
                        EmptyNotificationsScreen()
                    }


                }
            }

        }

    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NotificationScreenContent(notificationsScreenViewModel: NotificationsScreenViewModel) {

    val notificationsList by notificationsScreenViewModel.notificationsList.collectAsState()

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(space = 7.dp)
        ) {
            items(notificationsList, key = { notificationEntity ->
                notificationEntity.notificationId
            }) { notification ->

                val swipeToDismissState = rememberDismissState()

                DisposableEffect(swipeToDismissState) {
                    onDispose {
                        if (swipeToDismissState.isDismissed(direction = DismissDirection.EndToStart)) {
                            notificationsScreenViewModel.deleteNotification(notification = notification)
                        }
                    }
                }

                AnimatedVisibility(
                    visible = !swipeToDismissState.isDismissed(direction = DismissDirection.EndToStart),
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    SwipeToDismiss(
                        modifier = Modifier.fillMaxWidth(),
                        state = swipeToDismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        background = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Transparent),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.White
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        },
                        dismissContent = {
                            NotificationsCard(notification = notification)
                        },
                    )
                }
            }
        }

}


@Composable
private fun NotificationsCard(notification: Notification) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, end = 3.dp),
        shape = RoundedCornerShape(size = 21.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
        border = BorderStroke(width = 3.dp, color = Color.White)
    ) {

        Column(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = notification.notificationTitle!!,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = notification.notificationBody!!,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Received on ${
                    SimpleDateFormat(
                        "MMM dd, yyyy hh:mm a",
                        Locale.getDefault()
                    ).format(notification.notificationTimestamp)
                }",
                style = MaterialTheme.typography.labelLarge
            )
        }

    }

}


@Composable
private fun EmptyNotificationsScreen() {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        item {
            Text(
                text = "You have no notifications",
                style = MaterialTheme.typography.titleLarge
            )
        }

    }

}
