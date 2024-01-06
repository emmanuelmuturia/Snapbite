package snapbite.app.food.domain

data class Food(
    val foodId: Long?,
    val foodName: String,
    val foodCaption: String,
    val foodImage: ByteArray?,
    val foodEmoji: String?,
    val foodDate: Long
)
