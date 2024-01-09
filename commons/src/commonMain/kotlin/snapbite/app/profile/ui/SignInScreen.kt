package snapbite.app.profile.ui

import android.app.Activity.RESULT_OK
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.android.gms.auth.api.identity.Identity
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import kotlinx.coroutines.launch
import snapbite.app.commons.SnapbiteHeader
import snapbite.app.food.components.SnapbiteBackgroundImage
import snapbite.app.profile.google.GoogleAuthUiClient
import snapbite.app.theme.snapbiteMaroon

class SignInScreen : Screen {

    @Composable
    override fun Content() {

        val signInViewModel: SignInViewModel = getViewModel(
            key = "signInScreenViewModel",
            factory = viewModelFactory<SignInViewModel> {
                SignInViewModel()
            }
        )

        val signInState by signInViewModel.state.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        val context = LocalContext.current

        val scope = rememberCoroutineScope()

        val googleAuthUiClient by lazy {
            GoogleAuthUiClient(
                oneTapClient = Identity.getSignInClient(context)
            )
        }

        LaunchedEffect(key1 = signInState.signInError) {
            signInState.signInError?.let { error ->
                Toast.makeText(
                    context,
                    error,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartIntentSenderForResult(),
            onResult = { result ->
                if (result.resultCode == RESULT_OK) {
                    scope.launch {
                        val signInResult = googleAuthUiClient.signInWithIntent(
                            intent = result.data ?: return@launch
                        )
                        signInViewModel.onSignInResult(result = signInResult)
                    }
                }
            })

        LaunchedEffect(key1 = signInState.isSignInSuccessful) {
            if (signInState.isSignInSuccessful) {
                Toast.makeText(context, "You have signed in successfully!", Toast.LENGTH_LONG)
                    .show()
                navigator.push(item = ProfileScreen())
                signInViewModel.resetState()
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {

            SnapbiteBackgroundImage()

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SnapbiteHeader(
                    headerTitle = "Sign In"
                )

                SignInWithGoogleButton(onSignInClick = {
                    scope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                })

            }

        }

    }

}

@Composable
fun SignInWithGoogleButton(onSignInClick: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = onSignInClick,
            shape = RoundedCornerShape(size = 21.dp),
            colors = ButtonDefaults.buttonColors(containerColor = snapbiteMaroon)
        ) {
            Text(text = "Sign In With Google", style = MaterialTheme.typography.bodyLarge)
        }

    }

}