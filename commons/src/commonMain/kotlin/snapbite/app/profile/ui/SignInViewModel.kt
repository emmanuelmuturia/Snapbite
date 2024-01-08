package snapbite.app.profile.ui

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import snapbite.app.profile.google.SignInResult
import snapbite.app.profile.google.SignInState

class SignInViewModel(
): ViewModel() {

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