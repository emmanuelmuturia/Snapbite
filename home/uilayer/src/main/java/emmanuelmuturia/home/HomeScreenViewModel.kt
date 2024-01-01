package emmanuelmuturia.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.entities.DayEntity
import emmanuelmuturia.repository.HomeRepository
import emmanuelmuturia.state.SnapbiteState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel  @Inject constructor(
    private val homeRepository: HomeRepository,
    application: Application
) : AndroidViewModel(application = application) {

    private var _daysState = MutableStateFlow<SnapbiteState<Any>>(SnapbiteState.Loading)
    val daysState: StateFlow<SnapbiteState<Any>> = _daysState.asStateFlow()

    private var _daysList = MutableStateFlow<List<DayEntity>>(value = emptyList())
    val daysList: StateFlow<List<DayEntity>> = _daysList.asStateFlow()

    private var _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        getAllDays()
    }

    private fun getAllDays() {
        viewModelScope.launch {

            _daysState.value = SnapbiteState.Loading

            try {
                homeRepository.getAllDays().collectLatest { daysList ->
                    _daysList.value = daysList
                    _daysState.value = SnapbiteState.Success(data = daysList)
                }
            } catch (e: Exception) {
                _daysState.value = SnapbiteState.Error(error = e)
            }
        }
    }

    fun refreshDaysList() {

        viewModelScope.launch {

            _isLoading.value = true
            homeRepository.getAllDays()
            delay(timeMillis = 1400L)
            _isLoading.value = false

        }

    }

}