package snapbite.app.settings.data

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import snapbite.app.BuildConfig
import snapbite.app.settings.domain.SettingsRepository
import timber.log.Timber

class SettingsRepositoryImplementation(
    private val context: Context
) : SettingsRepository {

    override suspend fun navigateToNotificationsSettings() {
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

    override suspend fun rateUs() {
        val rateUsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.github))

        try {
            rateUsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(rateUsIntent)
        } catch (e: Exception) {
            Timber.tag(tag = "Rate Us Exception")
                .e(message = "Failed to rate us due to: %s", e.printStackTrace())
        }
    }

}