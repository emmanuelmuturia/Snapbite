package snapbite.app.profile.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.identity.Identity
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import kotlinx.coroutines.launch
import snapbite.app.commons.SnapbiteHeader
import snapbite.app.food.components.SnapbiteBackgroundImage
import snapbite.app.profile.google.GoogleAuthUiClient
import snapbite.app.profile.google.UserData
import snapbite.app.theme.snapbiteMaroon
import snapbite.app.theme.snapbiteOrange

@Composable
fun ProfileScreen(userData: UserData?, profileScreenViewModel: ProfileScreenViewModel = getViewModel(
    key = "profileScreenViewModel",
    factory = viewModelFactory<ProfileScreenViewModel> {
        ProfileScreenViewModel()
    }
)) {

        val context = LocalContext.current

        val scope = rememberCoroutineScope()

        val googleAuthUiClient by lazy {
            GoogleAuthUiClient(
                oneTapClient = Identity.getSignInClient(context)
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {

            SnapbiteBackgroundImage()

            Column(modifier = Modifier.fillMaxSize()) {

                SnapbiteHeader(
                    headerTitle = "My Profile"
                )

                Spacer(modifier = Modifier.height(height = 14.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(weight = 1f)
                ) {

                    item(key = 1) {
                        ProfileScreenContent(userData = userData)
                    }

                    item(key = 2) {
                        Spacer(modifier = Modifier.height(height = 7.dp))
                    }

                    item(key = 3) {
                        ProfileScreenCard(profileScreenViewModel = profileScreenViewModel)
                    }

                    item(key = 4) {
                        Spacer(modifier = Modifier.weight(weight = 1f))
                    }

                    item(key = 5) {
                        SignOutButton(onSignOut = {
                            scope.launch { googleAuthUiClient.signOut() }
                            Toast.makeText(context, "You have signed out successfully!", Toast.LENGTH_LONG).show()
                        })
                    }

                }

            }

        }

}

@Composable
fun ProfileScreenContent(
    userData: UserData?
) {

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {

        /*GlideImage(
            modifier = Modifier.size(size = 140.dp),
            model = userData?.profilePictureUrl,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop
        )*/

        Column(modifier = Modifier.fillMaxSize()) {

            userData?.userName?.let { Text(text = it, style = MaterialTheme.typography.bodyLarge) }

        }

    }

}


@Composable
fun ProfileScreenCard(profileScreenViewModel: ProfileScreenViewModel) {

    val foodList by profileScreenViewModel.foodList.collectAsState()

    Card(
        modifier = Modifier
            .height(height = 121.dp)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        //.clickable(onClick = onClick),
        , elevation = CardDefaults.cardElevation(7.dp),
        colors = CardDefaults.cardColors(containerColor = snapbiteOrange)
    ) {

        Column(
            modifier = Modifier.padding(7.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "${foodList.size} Diaries", style = MaterialTheme.typography.titleLarge)
        }

    }

}

@Composable
fun SignOutButton(onSignOut: () -> Unit) {

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Button(
            onClick = onSignOut,
            shape = RoundedCornerShape(size = 21.dp),
            colors = ButtonDefaults.buttonColors(containerColor = snapbiteMaroon)
        ) {
            Text(text = "Sign Out", style = MaterialTheme.typography.bodyLarge)
        }

    }

}