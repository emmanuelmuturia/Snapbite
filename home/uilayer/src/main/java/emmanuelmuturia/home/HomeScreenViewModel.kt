package emmanuelmuturia.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.database.SnapbiteDatabase
import emmanuelmuturia.entities.DayEntity
import emmanuelmuturia.hilt.FoodHiltModule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel  @Inject constructor(
    application: Application
) : AndroidViewModel(application = application) {

    private var _daysList = MutableStateFlow<List<DayEntity>>(value = emptyList())
    val daysList: StateFlow<List<DayEntity>> = _daysList.asStateFlow()


    private val snapbiteDatabase = SnapbiteDatabase.getSnapbiteDatabase(context = application)
    private val dayDao = FoodHiltModule.providesDayDao(snapbiteDatabase = snapbiteDatabase)
    private val dayRepository = FoodHiltModule.providesDayRepository(dayDAO = dayDao)

    init {
        getAllDays()
    }

    private fun getAllDays() {
        viewModelScope.launch {
            dayRepository.getAllDays().collectLatest { days ->
                _daysList.value = days
            }
        }
    }

    fun deleteAllDays() {
        viewModelScope.launch {
            dayRepository.deleteDays()
        }
    }

}