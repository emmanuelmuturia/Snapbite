package emmanuelmuturia.food.day

import android.app.Application
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import emmanuelmuturia.food.entities.FoodEntity
import emmanuelmuturia.food.repository.FoodRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import emmanuelmuturia.commons.state.SnapbiteState
import java.time.LocalDate
import javax.inject.Inject

class DayScreenViewModel @Inject constructor(
    application: Application,
    private val foodRepository: FoodRepository
) : AndroidViewModel(application = application) {

    private var _bitmaps = MutableStateFlow<List<Bitmap>>(value = emptyList())
    val bitmaps: StateFlow<List<Bitmap>> = _bitmaps.asStateFlow()

    private var _daysState = MutableStateFlow<SnapbiteState<Any>>(
        SnapbiteState.Loading)
    val daysState: StateFlow<SnapbiteState<Any>> = _daysState.asStateFlow()

    private var _foodList = MutableStateFlow<List<FoodEntity>>(value = emptyList())
    val foodList: StateFlow<List<FoodEntity>> = _foodList.asStateFlow()

    val visiblePermissionDialogQueue = mutableStateListOf<String>()

    private var _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        getAllFoods()
    }

    private fun getAllFoods() {

        viewModelScope.launch {

            //_daysState.value = SnapbiteState.Loading

            try {
               foodRepository.getAllFoods().collectLatest { foodList ->
                   _foodList.value = foodList
                   //_daysState.value = SnapbiteState.Success(data = foodList)
               }
            } catch (e: Exception) {
                //_daysState.value = SnapbiteState.Error(error = e)
            }

        }

    }

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }

    fun addFood(foodEntity: FoodEntity) {
        viewModelScope.launch {
            foodRepository.insertFood(foodEntity = foodEntity)
        }
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