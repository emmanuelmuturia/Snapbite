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

    var myFoodName by mutableStateOf(value = "")
        private set

    var myFoodCaption by mutableStateOf(value = "")
        private set

    fun updateFoodName(foodName: String) {
        myFoodName = foodName
    }

    fun updateFoodCaption(foodCaption: String) {
        myFoodCaption = foodCaption
    }

    fun updateFood(foodEntity: FoodEntity?) {
        if (foodEntity != null) {
            viewModelScope.launch {
                val updatedFood = foodEntity!!.copy(
                    foodName = myFoodName,
                    foodCaption = myFoodCaption
                )
                foodRepository.updateFood(foodEntity = updatedFood)
            }
        }
    }

    fun getFoodById(foodId: Int?): FoodEntity? {
        return _foodList.value.find { it.foodId == foodId }
    }

}