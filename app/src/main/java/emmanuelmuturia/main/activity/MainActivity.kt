package emmanuelmuturia.main.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import cafe.adriel.voyager.navigator.Navigator
import emmanuelmuturia.home.ui.HomeScreen
import emmanuelmuturia.home.ui.WelcomeScreen
import emmanuelmuturia.notifications.handler.NotificationsHandler
import emmanuelmuturia.notifications.repository.NotificationRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import emmanuelmuturia.commons.sharedpreferences.SnapbiteSharedPreferences
import emmanuelmuturia.notifications.dao.NotificationDAO
import timber.log.Timber
import java.io.IOException

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        CoroutineScope(context = Dispatchers.Main).launch {

            val notificationDAO: NotificationDAO by inject()

            val notificationsRepository: NotificationRepository by inject()

            try {
                NotificationsHandler(notificationRepository = notificationsRepository).handleNotificationIntent(
                    intent = intent
                )
            } catch (e: IOException) {
                Timber.tag(tag = "Notifications Handling Exception")
                    .e(message = "Could not handle notifications due to ${e.printStackTrace()}")
            }
        }

        val isFirstTimeUser =
            SnapbiteSharedPreferences(context = this).isFirstTimeUser

        setContent {
            Navigator(
                screen = if (isFirstTimeUser) {
                    WelcomeScreen()
                } else {
                    HomeScreen()
                }
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        CoroutineScope(context = Dispatchers.Main).launch {

            val notificationDAO: NotificationDAO by inject()

            val notificationsRepository: NotificationRepository by inject()

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