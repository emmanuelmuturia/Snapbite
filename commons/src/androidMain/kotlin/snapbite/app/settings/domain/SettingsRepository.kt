package snapbite.app.settings.domain

actual interface SettingsRepository {

    actual suspend fun navigateToNotificationsSettings()

    actual suspend fun rateUs()

}