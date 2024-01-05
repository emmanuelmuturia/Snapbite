package emmanuelmuturia.food.permission

fun interface PermissionTextProvider {

    fun getDescription(isPermanentlyDeclined: Boolean): String

}