package snapbite.app.food.ui

import snapbite.app.food.domain.FoodEntity

data class FoodListState(
    val foodList: List<FoodEntity> = emptyList(),
    val selectedFood: FoodEntity? = null,
    val isAddFoodSheetOpen: Boolean = false,
    val isSelectedFoodSheetOpen: Boolean = false,
    val foodNameError: String? = null,
    val foodCaptionError: String? = null
)