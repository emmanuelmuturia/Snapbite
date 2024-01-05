package emmanuelmuturia.settings

import android.content.Context

interface SettingsRepository {

    fun navigateToNotificationSettings(context: Context)

    suspend fun rateUs(context: Context)

}