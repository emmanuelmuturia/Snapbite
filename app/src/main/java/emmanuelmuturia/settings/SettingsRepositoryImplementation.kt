package emmanuelmuturia.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import timber.log.Timber

class SettingsRepositoryImplementation : SettingsRepository {

    override fun navigateToNotificationSettings(context: Context) {

        try {
            val notificationsSettingsIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
            } else {
                val notificationsSettingsIntent = Intent(Settings.ACTION_SETTINGS)
                notificationsSettingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                notificationsSettingsIntent.putExtra("app_package", context.packageName)
                notificationsSettingsIntent
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationsSettingsIntent.putExtra(
                    Settings.EXTRA_APP_PACKAGE,
                    context.packageName
                )
            } else {
                notificationsSettingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(notificationsSettingsIntent)
        } catch (e: Exception) {
            Timber.tag(tag = "Settings Intent Error")
                .e(message = "Could not launch the Settings Intent due to: %s", e.printStackTrace())
        }

    }

    override suspend fun rateUs(context: Context) {

        val rateUsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(""))

        try {
            rateUsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(rateUsIntent)
        } catch (e: Exception) {
            Timber.tag(tag = "Rate Us Exception")
                .e(message = "Failed to rate us due to: %s", e.printStackTrace())
        }

    }

}