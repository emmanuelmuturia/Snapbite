package emmanuelmuturia.day

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.entities.DayEntity
import emmanuelmuturia.entities.FoodEntity
import emmanuelmuturia.repository.FoodRepository
import emmanuelmuturia.state.SnapbiteState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DayScreenViewModel @Inject constructor(
    application: Application,
    private val foodRepository: FoodRepository
) : AndroidViewModel(application = application) {

    private var _daysState = MutableStateFlow<SnapbiteState<Any>>(SnapbiteState.Loading)
    val daysState: StateFlow<SnapbiteState<Any>> = _daysState.asStateFlow()

    private var _foodList = MutableStateFlow<List<FoodEntity>>(value = emptyList())
    val foodList: StateFlow<List<FoodEntity>> = _foodList.asStateFlow()

    private var _dayList = MutableStateFlow<List<DayEntity>>(value = emptyList())

    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    @RequiresApi(Build.VERSION_CODES.O)
    var dayDate: MutableState<String> = mutableStateOf(value = formatCurrentDate())

    var dayEmoji: MutableState<String> = mutableStateOf(value = "")

    var foodCaption: MutableState<String> = mutableStateOf(value = "")

    var foodEmoji: MutableState<String> = mutableStateOf(value = "\uD83D\uDE0B")

    var foodName: MutableState<String> = mutableStateOf(value = "")

    var listOfFoods: MutableState<List<FoodEntity>> = mutableStateOf(value = _foodList.value)

    private var _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        getAllFoods()
    }

    private fun getAllFoods() {

        viewModelScope.launch {

            _daysState.value = SnapbiteState.Loading

            try {
               foodRepository.getAllFoods().collectLatest { foodList ->
                   _foodList.value = foodList
                   _daysState.value = SnapbiteState.Success(data = foodList)
               }
            } catch (e: Exception) {
                _daysState.value = SnapbiteState.Error(error = e)
            }

        }

    }

    fun addFood(foodEntity: FoodEntity) {
        viewModelScope.launch {
            foodRepository.insertFood(foodEntity = foodEntity)
        }
    }

    fun getDayById(dayId: Int?): DayEntity? {
        return _dayList.value.find { it.dayId == dayId }
    }

    fun getFoodById(foodId: Int?): FoodEntity? {
        return _foodList.value.find { it.foodId == foodId }
    }

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

    fun refreshFoodList() {

        viewModelScope.launch {

            _isLoading.value = true
            foodRepository.getAllFoods()
            delay(timeMillis = 1400L)
            _isLoading.value = false

        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun formatCurrentDate(): String {
        val currentDate = LocalDate.now()
        val dayOfMonth = currentDate.dayOfMonth
        val month =
            currentDate.month.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH)
        val year = currentDate.year

        val daySuffix = when (dayOfMonth) {
            1, 21, 31 -> "st"
            2, 22 -> "nd"
            3, 23 -> "rd"
            else -> "th"
        }

        return "$dayOfMonth$daySuffix $month $year"
    }

}