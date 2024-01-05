package snapbite.commons.settings.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.settings.repository.SettingsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    application: Application,
    private val settingsRepository: SettingsRepository
) : AndroidViewModel(application = application) {

    fun navigateToNotificationsSettings(context: Context) {
        viewModelScope.launch {
            settingsRepository.navigateToNotificationSettings(context = context)
        }
    }

    fun rateUs(context: Context) {
        viewModelScope.launch {
            settingsRepository.rateUs(context = context)
        }
    }

}