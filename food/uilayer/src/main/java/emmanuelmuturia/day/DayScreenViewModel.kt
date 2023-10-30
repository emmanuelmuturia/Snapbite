package emmanuelmuturia.day

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.entities.DayEntity
import emmanuelmuturia.repository.DayRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DayScreenViewModel @Inject constructor(
    application: Application,
    private val dayRepository: DayRepository
) : AndroidViewModel(application = application) {

    private var _daysList = MutableStateFlow<List<DayEntity>>(value = emptyList())
    val daysList: StateFlow<List<DayEntity>> = _daysList.asStateFlow()

    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    fun dismissDialog() {
        visiblePermissionDialogQueue.removeFirst()
    }

    fun onPermissionResult(
        permission: String,
        isGranted: Boolean
    ) {
        if (!isGranted) {
            visiblePermissionDialogQueue.add(element = permission)
        }
    }

    fun addDay(dayEntity: DayEntity) {
        viewModelScope.launch {
            dayRepository.insertDay(dayEntity = dayEntity)
        }
    }

}