package snapbite.app.search

import androidx.compose.runtime.mutableStateOf
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class SearchScreenViewModel : ViewModel() {

    var searchItem = mutableStateOf(value = "")
        private set

}