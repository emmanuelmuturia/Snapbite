package emmanuelmuturia.food.ui

import android.app.Application
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

class FoodScreenViewModel(
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

    var food by mutableStateOf<FoodEntity?>(value = null)
        private set

    var foodName by mutableStateOf(value = "")
        private set

    var foodCaption by mutableStateOf(value = "")
        private set

    var foodEmoji by mutableStateOf(value = "")
        private set

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

    fun onTakePhoto(bitmap: Bitmap) {
        _bitmaps.value += bitmap
    }

    fun addFood(foodEntity: FoodEntity) {
        viewModelScope.launch {
            foodRepository.insertFood(foodEntity = foodEntity)
        }
    }

    fun updateFood(foodEntity: FoodEntity?) {
        if (foodEntity != null) {
            viewModelScope.launch {
                foodRepository.updateFood(foodEntity = foodEntity)
            }
        }
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