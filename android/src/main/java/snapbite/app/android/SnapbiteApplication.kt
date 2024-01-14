package snapbite.app.android

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import snapbite.app.BuildConfig
import snapbite.app.dependencyinjection.snapbiteModule
import timber.log.Timber

class SnapbiteApplication : Application() {

    override fun onCreate() {

        super.onCreate()

        startKoin {
            androidContext(androidContext = this@SnapbiteApplication)
            modules(modules = snapbiteModule)
        }

        getFCMToken()

        createNotificationChannels(context = this)

        if (BuildConfig.DEBUG) Timber.plant(tree = Timber.DebugTree())

    }

}


private fun getFCMToken() {

    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if (!task.isSuccessful) {
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

            val notificationManager: NotificationManager? =
                ContextCompat.getSystemService(context, NotificationManager::class.java)

            notificationManager?.createNotificationChannels(
                listOf(
                    generalNotificationsChannel
                )
            )
        }
    } catch (e: SecurityException) {
        Timber.tag(tag = "Security Exception (Notification Channels)")
            .e(message = "Could not create Notification Channel(s) due to ${e.message}")
    }

}