package emmanuelmuturia.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import emmanuelmuturia.food.entities.FoodEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application = application) {

    private var _foodList = MutableStateFlow<List<emmanuelmuturia.food.entities.FoodEntity>>(value = emptyList())
    val foodList: StateFlow<List<emmanuelmuturia.food.entities.FoodEntity>> = _foodList.asStateFlow()

}