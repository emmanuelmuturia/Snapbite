package snapbite.app.about.ui

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import snapbite.app.about.domain.AboutRepository
import java.io.Serializable

class AboutScreenViewModel(
    private val aboutRepository: AboutRepository
) : ViewModel(), Serializable {

    fun getPrivacyPolicy() {
        viewModelScope.launch {
            aboutRepository.getPrivacyPolicy()
        }
    }

    fun getTermsAndConditions() {
        viewModelScope.launch {
            aboutRepository.getTermsAndConditions()
        }
    }

    fun getAppVersion(): String {
        return aboutRepository.getAppVersion()
    }

}