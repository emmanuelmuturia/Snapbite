package emmanuelmuturia.profile.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import emmanuelmuturia.profile.google.SignInResult
import emmanuelmuturia.profile.google.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel(
    application: Application
): AndroidViewModel(application = application) {

    private val _state = MutableStateFlow(value = SignInState())
    val state: StateFlow<SignInState> = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }

}