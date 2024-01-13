package snapbite.app.food.domain

data class FoodEntity(
    val foodId: Long?,
    var foodName: String,
    var foodCaption: String,
    val foodImage: ByteArray?,
    var foodEmoji: String,
    val foodDate: Long
)
