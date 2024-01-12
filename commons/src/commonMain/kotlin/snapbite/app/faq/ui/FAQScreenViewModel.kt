package snapbite.app.faq.ui

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import snapbite.app.commons.SnapbiteState
import snapbite.app.faq.domain.FAQ
import snapbite.app.faq.domain.FAQRepository

class FAQScreenViewModel(
    private val faqRepository: FAQRepository
) : ViewModel() {

    private var _faqState =
        MutableStateFlow<SnapbiteState<Any>>(SnapbiteState.Loading)
    val faqState: StateFlow<SnapbiteState<Any>> = _faqState.asStateFlow()

    private var _faqList = MutableStateFlow<List<FAQ>>(value = emptyList())
    val faqList: StateFlow<List<FAQ>> = _faqList.asStateFlow()

    init {
        getFAQs()
    }

    private fun getFAQs() {

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

}