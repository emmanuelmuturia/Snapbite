package snapbite.app.food.domain

object FoodValidator {

    fun validateFood(food: FoodEntity): ValidationResult {

        var result = ValidationResult()

        if (food.foodName.isBlank()) {
            result = result.copy(foodNameError = "The food name cannot be empty...")
        }

        if (food.foodCaption.isBlank()) {
            result = result.copy(foodCaptionError = "The food caption error should not be empty...")
        }

        return result

    }

    data class ValidationResult(
        val foodNameError: String? = null,
        val foodCaptionError: String? = null
    )

}