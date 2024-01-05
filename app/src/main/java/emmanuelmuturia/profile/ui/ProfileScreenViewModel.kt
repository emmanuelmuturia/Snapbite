package emmanuelmuturia.profile.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import emmanuelmuturia.food.entities.FoodEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProfileScreenViewModel(
    application: Application
) : AndroidViewModel(application = application) {

    private var _foodList = MutableStateFlow<List<FoodEntity>>(value = emptyList())
    val foodList: StateFlow<List<FoodEntity>> = _foodList.asStateFlow()

}