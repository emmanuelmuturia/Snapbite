package snapbite.commons.food.day

fun interface PermissionTextProvider {

    fun getDescription(isPermanentlyDeclined: Boolean): String

}