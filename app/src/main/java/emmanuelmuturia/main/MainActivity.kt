package emmanuelmuturia.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import emmanuelmuturia.notifications.handler.NotificationsHandler
import emmanuelmuturia.navigation.navgraph.NavGraph
import emmanuelmuturia.notifications.NotificationsHiltModule
import emmanuelmuturia.commons.theme.SnapbiteTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        CoroutineScope(context = Dispatchers.Main).launch {

            val notificationsDatabase =
                NotificationsHiltModule.providesNotificationsDatabase(context = this@MainActivity)

            val notificationDAO =
                NotificationsHiltModule.providesNotificationDao(notificationsDatabase = notificationsDatabase)

            val notificationsRepository =
                NotificationsHiltModule.providesNotificationsRepository(notificationDAO = notificationDAO)

            try {
                NotificationsHandler(notificationRepository = notificationsRepository).handleNotificationIntent(
                    intent = intent
                )
            } catch (e: IOException) {
                Timber.tag(tag = "Notifications Handling Exception")
                    .e(message = "Could not handle notifications due to ${e.printStackTrace()}")
            }
        }

        setContent {
            SnapbiteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph(navController = rememberNavController())
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        CoroutineScope(context = Dispatchers.Main).launch {

            val notificationsDatabase =
                NotificationsHiltModule.providesNotificationsDatabase(context = this@MainActivity)

            val notificationDAO =
                NotificationsHiltModule.providesNotificationDao(notificationsDatabase = notificationsDatabase)

            val notificationsRepository =
                NotificationsHiltModule.providesNotificationsRepository(notificationDAO = notificationDAO)

            try {
                intent?.let {
                    NotificationsHandler(notificationRepository = notificationsRepository).handleNotificationIntent(
                        intent = it
                    )
                }
            } catch (e: IOException) {
                Timber.tag(tag = "Notifications Handling Exception")
                    .e(message = "Could not handle notifications due to ${e.printStackTrace()}")
            }
        }
    }

}