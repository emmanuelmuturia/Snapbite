package snapbite.app.food.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import snapbite.app.food.domain.Food
import snapbite.app.food.domain.FoodDataSource
import snapbite.app.food.domain.FoodValidator

class FoodListViewModel(
    private val foodDataSource: FoodDataSource
) : ViewModel() {

    private var _state = MutableStateFlow(value = FoodListState())
    val state = combine(
        _state,
        foodDataSource.getFoods()
    ) { state, foods ->

        state.copy(
            foodList = foods
        )

    }.stateIn(
        scope = viewModelScope,
        SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000L),
        FoodListState()
    )

    var newFood: Food? by mutableStateOf(value = null)
        private set

    fun onEvent(event: FoodListEvent) {
        when(event) {
            FoodListEvent.DeleteFood -> {
                viewModelScope.launch {
                    _state.value.selectedFood?.foodId?.let { foodId ->
                        _state.update { it.copy(
                            isSelectedFoodSheetOpen = false
                        ) }
                        foodDataSource.deleteFood(foodId = foodId)
                        delay(timeMillis = 300L) // Animation delay
                        _state.update { it.copy(
                            selectedFood = null
                        ) }
                    }
                }
            }
            FoodListEvent.DismissFood -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        isSelectedFoodSheetOpen = false,
                        isAddFoodSheetOpen = false,
                        foodNameError = null,
                        foodCaptionError = null
                    ) }
                    delay(timeMillis = 300L) // Animation delay
                    newFood = null
                    _state.update { it.copy(
                        selectedFood = null
                    ) }
                }
            }
            is FoodListEvent.EditFood -> {
                _state.update { it.copy(
                    selectedFood = null,
                    isAddFoodSheetOpen = true,
                    isSelectedFoodSheetOpen = false
                ) }
                newFood = event.food
            }
            FoodListEvent.OnAddNewFoodClick -> {
                _state.update { it.copy(
                    isAddFoodSheetOpen = true
                ) }
                newFood = Food(
                    foodId = null,
                    foodName = "",
                    foodEmoji = "",
                    foodCaption = "",
                    foodDate = Clock.System.now().toEpochMilliseconds(),
                    foodImage = null
                )
            }
            is FoodListEvent.OnFoodCaptionChanged -> {
                newFood = newFood?.copy(
                    foodCaption = event.value
                )
            }
            is FoodListEvent.OnFoodEmojiChanged -> {
                newFood = newFood?.copy(
                    foodEmoji = event.value
                )
            }
            is FoodListEvent.OnFoodImagePicked -> {
                newFood = newFood?.copy(
                    foodImage = event.bytes
                )
            }
            is FoodListEvent.OnFoodNameChanged -> {
                newFood = newFood?.copy(
                    foodName = event.value
                )
            }
            FoodListEvent.SaveFood -> {
                newFood?.let { food ->
                    val result = FoodValidator.validateFood(food = food)
                    val errors = listOfNotNull(
                        result.foodNameError,
                        result.foodCaptionError
                    )

                    if(errors.isEmpty()) {
                        _state.update { it.copy(
                            isAddFoodSheetOpen = false,
                            foodNameError = null,
                            foodCaptionError = null
                        ) }
                        viewModelScope.launch {
                            foodDataSource.insertFood(food = food)
                            delay(timeMillis = 300L) // Animation delay
                            newFood = null
                        }
                    } else {
                        _state.update { it.copy(
                            foodNameError = result.foodNameError,
                            foodCaptionError = result.foodCaptionError
                        ) }
                    }
                }
            }
            is FoodListEvent.SelectFood -> {
                _state.update { it.copy(
                    selectedFood = event.food,
                    isSelectedFoodSheetOpen = true
                ) }
            }

            else -> Unit

        }
    }

}