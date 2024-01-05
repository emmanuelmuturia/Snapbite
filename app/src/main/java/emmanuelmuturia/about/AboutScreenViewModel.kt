package emmanuelmuturia.about

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.about.repository.AboutRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutScreenViewModel @Inject constructor(
    application: Application,
    private val aboutRepository: AboutRepository
) : AndroidViewModel(application = application) {

    fun getPrivacyPolicy(context: Context) {
        viewModelScope.launch {
            aboutRepository.getPrivacyPolicy(context = context)
        }
    }

    fun getTermsAndConditions(context: Context) {
        viewModelScope.launch {
            aboutRepository.getTermsAndConditions(context = context)
        }
    }

    fun getAppVersion(context: Context): String {
        return aboutRepository.getAppVersion(context = context)
    }

}