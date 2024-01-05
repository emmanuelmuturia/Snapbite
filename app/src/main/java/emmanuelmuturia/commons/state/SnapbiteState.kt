package emmanuelmuturia.commons.state

sealed class SnapbiteState<out T> {

    data object Loading : SnapbiteState<Nothing>()

    data class Success<T>(val data: T) : SnapbiteState<T>()

    data class Error(val error: Throwable) : SnapbiteState<Nothing>()

}