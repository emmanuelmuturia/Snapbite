package emmanuelmuturia.search.ui

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import javax.inject.Inject

class SearchScreenViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application = application) {

    var searchItem = mutableStateOf("")

}