package emmanuelmuturia.faq

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.model.FAQ
import emmanuelmuturia.repository.FAQRepository
import emmanuelmuturia.state.SnapbiteState
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
    private val faqRepository: FAQRepository
) : AndroidViewModel(application = application) {

    private var _faqState =
        MutableStateFlow<SnapbiteState<Any>>(SnapbiteState.Loading)
    val faqState: StateFlow<SnapbiteState<Any>> = _faqState.asStateFlow()

    private var _faqList = MutableStateFlow<List<FAQ>>(value = emptyList())
    val faqList: StateFlow<List<FAQ>> = _faqList.asStateFlow()

    private var _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        getFAQs()
    }

    fun getFAQs() {
        viewModelScope.launch {

            _faqState.update { SnapbiteState.Loading }

            try {
                _faqList.value = faqRepository.getFAQs()
                _faqState.update { SnapbiteState.Success(data = _faqList.value) }
            } catch (e: Exception) {
                _faqState.update { SnapbiteState.Error(error = e) }
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