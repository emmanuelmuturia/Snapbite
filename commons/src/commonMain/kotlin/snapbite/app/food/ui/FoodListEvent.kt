package snapbite.app.food.ui

import snapbite.app.food.domain.FoodEntity


sealed interface FoodListEvent {

    data object OnAddNewFoodClick: FoodListEvent

    data object DismissFood: FoodListEvent

    data class OnFoodNameChanged(val value: String): FoodListEvent

    data class OnFoodCaptionChanged(val value: String): FoodListEvent

    data class OnFoodEmojiChanged(val value: String): FoodListEvent

    class OnFoodImagePicked(val bytes: ByteArray): FoodListEvent

    data object OnAddFoodImage: FoodListEvent

    data object SaveFood: FoodListEvent

    data class SelectFood(val food: FoodEntity): FoodListEvent

    data class EditFood(val food: FoodEntity): FoodListEvent

    data object DeleteFood: FoodListEvent

}