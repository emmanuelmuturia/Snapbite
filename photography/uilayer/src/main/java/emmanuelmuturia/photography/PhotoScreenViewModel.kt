package emmanuelmuturia.photography

import android.app.Application
import android.graphics.Bitmap
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class PhotoScreenViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application = application) {



}