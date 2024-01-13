package snapbite.app.food.ui

import snapbite.app.food.domain.Food

data class FoodListState(
    val foodList: List<Food> = emptyList(),
    val selectedFood: Food? = null,
    val isAddFoodSheetOpen: Boolean = false,
    val isSelectedFoodSheetOpen: Boolean = false,
    val foodNameError: String? = null,
    val foodCaptionError: String? = null
)