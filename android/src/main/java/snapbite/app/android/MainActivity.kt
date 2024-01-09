package snapbite.app.android

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import snapbite.app.App
import snapbite.app.core.ui.ImagePickerFactory
import snapbite.app.di.AppModule
import snapbite.app.notifications.domain.NotificationRepository
import snapbite.app.notifications.handler.NotificationsHandler
import java.io.IOException

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        WindowCompat.setDecorFitsSystemWindows(window, false)

        super.onCreate(savedInstanceState)

        CoroutineScope(context = Dispatchers.Main).launch {

            val notificationsRepository: NotificationRepository =
                AppModule(context = this@MainActivity).notificationRepository

            try {
                NotificationsHandler(notificationRepository = notificationsRepository).handleNotificationIntent(
                    intent = intent
                )
            } catch (e: IOException) {

            }
        }

        setContent {
            App(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true,
                appModule = AppModule(context = LocalContext.current.applicationContext),
                imagePicker = ImagePickerFactory().createPicker()
            )
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        CoroutineScope(context = Dispatchers.Main).launch {

            val notificationsRepository: NotificationRepository =
                AppModule(context = this@MainActivity).notificationRepository

            try {
                intent?.let {
                    NotificationsHandler(notificationRepository = notificationsRepository).handleNotificationIntent(
                        intent = it
                    )
                }
            } catch (e: IOException) {

            }
        }
    }

}
