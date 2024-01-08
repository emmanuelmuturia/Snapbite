package snapbite.app.search

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import javax.inject.Inject

class SearchScreenViewModel (

) : ViewModel() {

    var searchItem = mutableStateOf("")
        private set

}