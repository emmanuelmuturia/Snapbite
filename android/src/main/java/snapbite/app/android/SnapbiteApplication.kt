package snapbite.app.android

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import snapbite.app.di.AppModule

class SnapbiteApplication(appModule: AppModule) : Application() {

    override fun onCreate() {

        super.onCreate()

        getFCMToken()

        createNotificationChannels(context = this)

    }

}


private fun getFCMToken() {

    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if (!task.isSuccessful) {
            return@OnCompleteListener
        }
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

    }

}