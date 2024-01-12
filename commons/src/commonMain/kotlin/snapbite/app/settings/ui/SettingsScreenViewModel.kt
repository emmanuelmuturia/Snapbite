package snapbite.app.settings.ui

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import snapbite.app.settings.domain.SettingsRepository

class SettingsScreenViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    fun navigateToNotificationsSettings() {
        viewModelScope.launch {
            settingsRepository.navigateToNotificationsSettings()
        }
    }

    fun rateUs() {
        viewModelScope.launch {
            settingsRepository.rateUs()
        }
    }

}