package emmanuelmuturia.google

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
