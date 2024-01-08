package snapbite.app.settings.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import snapbite.app.BuildConfig

class SettingsScreenViewModel(

) : ViewModel() {

    fun navigateToNotificationsSettings(context: Context) {
        viewModelScope.launch {
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

            }
        }
    }

    fun rateUs(context: Context) {
        viewModelScope.launch {
            val rateUsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.github))

            try {
                rateUsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(rateUsIntent)
            } catch (e: Exception) {

            }
        }
    }

}