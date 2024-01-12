package snapbite.app.settings.domain

expect interface SettingsRepository {

    suspend fun navigateToNotificationsSettings()

    suspend fun rateUs()

}