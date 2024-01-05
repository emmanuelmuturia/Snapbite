package emmanuelmuturia.settings.repository

import android.content.Context

interface SettingsRepository {

    fun navigateToNotificationSettings(context: Context)

    suspend fun rateUs(context: Context)

}