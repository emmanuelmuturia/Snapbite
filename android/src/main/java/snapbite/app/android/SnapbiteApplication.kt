package snapbite.app.android

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import snapbite.app.di.AppModule

class SnapbiteApplication : Application() {

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

            val notificationManager: NotificationManager? =
                ContextCompat.getSystemService(context, NotificationManager::class.java)

            notificationManager?.createNotificationChannels(
                listOf(
                    generalNotificationsChannel
                )
            )
        }
    } catch (e: SecurityException) {

    }

}