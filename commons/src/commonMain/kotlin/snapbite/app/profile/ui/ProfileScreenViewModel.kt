package snapbite.app.profile.ui

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import snapbite.app.food.domain.Food

class ProfileScreenViewModel(
) : ViewModel() {

    private var _foodList = MutableStateFlow<List<Food>>(value = emptyList())
    val foodList: StateFlow<List<Food>> = _foodList.asStateFlow()

}