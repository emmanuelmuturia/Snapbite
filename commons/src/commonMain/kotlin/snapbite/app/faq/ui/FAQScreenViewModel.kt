package snapbite.app.faq.ui

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import emmanuelmuturia.commons.state.SnapbiteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import snapbite.app.faq.domain.FAQ

class FAQScreenViewModel(

) : ViewModel() {

    private var _faqState =
        MutableStateFlow<SnapbiteState<Any>>(SnapbiteState.Loading)
    val faqState: StateFlow<SnapbiteState<Any>> = _faqState.asStateFlow()

    private var _faqList = MutableStateFlow<List<FAQ>>(value = emptyList())
    val faqList: StateFlow<List<FAQ>> = _faqList.asStateFlow()

    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

    init {
        viewModelScope.launch {
            getFAQs()
        }
    }

    private suspend fun getFAQs() {

        _faqState.update { SnapbiteState.Loading }

        return try {
            _faqList.value = firestore.collection("FAQ").get().await().toObjects<FAQ>()
            _faqState.update { SnapbiteState.Success(data = _faqList.value) }
        } catch (e: Exception) {
            _faqState.update { SnapbiteState.Error(error = e) }
        }

    }

}