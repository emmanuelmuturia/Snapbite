package emmanuelmuturia.faq

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.faq.model.FAQ
import emmanuelmuturia.faq.repository.FAQRepository
import emmanuelmuturia.commons.SnapbiteState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class FAQScreenViewModel @Inject constructor(
    application: Application,
    private val faqRepository: emmanuelmuturia.faq.repository.FAQRepository
) : AndroidViewModel(application = application) {

    private var _faqState =
        MutableStateFlow<emmanuelmuturia.commons.SnapbiteState<Any>>(emmanuelmuturia.commons.SnapbiteState.Loading)
    val faqState: StateFlow<emmanuelmuturia.commons.SnapbiteState<Any>> = _faqState.asStateFlow()

    private var _faqList = MutableStateFlow<List<emmanuelmuturia.faq.model.FAQ>>(value = emptyList())
    val faqList: StateFlow<List<emmanuelmuturia.faq.model.FAQ>> = _faqList.asStateFlow()

    private var _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        getFAQs()
    }

    fun getFAQs() {
        viewModelScope.launch {

            _faqState.update { emmanuelmuturia.commons.SnapbiteState.Loading }

            try {
                _faqList.value = faqRepository.getFAQs()
                _faqState.update { emmanuelmuturia.commons.SnapbiteState.Success(data = _faqList.value) }
            } catch (e: Exception) {
                _faqState.update { emmanuelmuturia.commons.SnapbiteState.Error(error = e) }
            }

        }
    }


    fun refreshFAQs() {

        viewModelScope.launch {

            try {
                _isLoading.value = true
                faqRepository.getFAQs()
                delay(timeMillis = 1400L)
                _isLoading.value = false
            } catch (e: Exception) {
                Timber.tag(tag = "Refresh FAQs Exception")
                    .e(message = "Could not refresh the FAQs due to: %s", e.printStackTrace())
            }

        }

    }

}