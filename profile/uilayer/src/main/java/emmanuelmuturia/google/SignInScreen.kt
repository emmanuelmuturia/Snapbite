package emmanuelmuturia.google

import android.widget.Toast
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import emmanuelmuturia.components.SnapbiteBackgroundImage
import emmanuelmuturia.components.SnapbiteHeader
import emmanuelmuturia.theme.snapbiteMaroon

@Composable
fun SignInScreen(navigateBack: () -> Unit, onSignInClick: () -> Unit, signInState: SignInState) {

    val context = LocalContext.current

    LaunchedEffect(key1 = signInState.signInError) {
        signInState.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SnapbiteHeader(navigateBack = navigateBack, headerTitle = "Sign In")

            SignInWithGoogleButton(onSignInClick = onSignInClick)

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