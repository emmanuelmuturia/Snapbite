package emmanuelmuturia.dependencyinjection

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.HiltAndroidApp
import snapbite.app.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class SnapbiteApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        getFCMToken()

        createNotificationChannels(context = this)

        if (BuildConfig.DEBUG) Timber.plant(tree = Timber.DebugTree())

    }

}

private fun getFCMToken() {

    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if (!task.isSuccessful) {
            // Handle the error
            Timber.tag(tag = "FCM Token Error")
                .e(message = "Could not retrieve your FCM Token due to ${task.exception}")
            return@OnCompleteListener
        }

        Timber.tag(tag = "FCM Token").d(message = "Here is your token: ${task.result}")
    })

}

private fun createNotificationChannels(context: Context) {

    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val generalNotificationsChannelId = "general"
            val generalNotificationsChannelName = "General"
            val generalNotificationsImportance = NotificationManager.IMPORTANCE_HIGH
            val generalNotificationsChannel = NotificationChannel(
                generalNotificationsChannelId,
                generalNotificationsChannelName,
                generalNotificationsImportance
            )

            val upcomingEventsChannelId = "upcomingEvents"
            val upcomingEventsChannelName = "Upcoming Events"
            val upcomingEventsImportance = NotificationManager.IMPORTANCE_HIGH
            val upcomingEventsChannel = NotificationChannel(
                upcomingEventsChannelId,
                upcomingEventsChannelName,
                upcomingEventsImportance
            )

            val merchNotificationsChannelId = "merch"
            val merchNotificationsChannelName = "Merch"
            val merchNotificationsImportance = NotificationManager.IMPORTANCE_HIGH
            val merchNotificationsChannel = NotificationChannel(
                merchNotificationsChannelId,
                merchNotificationsChannelName,
                merchNotificationsImportance
            )

            val notificationManager: NotificationManager? =
                ContextCompat.getSystemService(context, NotificationManager::class.java)

            notificationManager?.createNotificationChannels(
                listOf(
                    generalNotificationsChannel,
                    upcomingEventsChannel,
                    merchNotificationsChannel
                )
            )
        }
    } catch (e: SecurityException) {
        Timber.tag(tag = "Security Exception (Notification Channels)")
            .e(message = "Could not create Notification Channel(s) due to ${e.printStackTrace()}")
    }

}