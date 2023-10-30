package emmanuelmuturia.food

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditFoodScreenViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application = application) {



}