package snapbite.app.food.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import snapbite.app.food.domain.Food

class FoodListViewModel : ViewModel() {

    private var _state = MutableStateFlow(value = FoodListState())
    val state: StateFlow<FoodListState> = _state.asStateFlow()

    var newFood: Food? by mutableStateOf(value = null)
        private set

    fun onEvent(event: FoodListEvent)  {

    }

}