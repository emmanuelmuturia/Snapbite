package emmanuelmuturia.food

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.entities.FoodEntity
import emmanuelmuturia.repository.FoodRepository
import emmanuelmuturia.state.SnapbiteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditFoodScreenViewModel @Inject constructor(
    application: Application,
    private val foodRepository: FoodRepository
) : AndroidViewModel(application = application) {

    private var _foodList = MutableStateFlow<List<FoodEntity>>(value = emptyList())
    val foodList: StateFlow<List<FoodEntity>> = _foodList.asStateFlow()

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
            foodRepository.getAllFoods().collectLatest { foodList ->
                _foodList.value = foodList
            }
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

}