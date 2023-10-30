package emmanuelmuturia.day

fun interface PermissionTextProvider {
    fun getDescription(isPermanentlyDeclined: Boolean): String
}