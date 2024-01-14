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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import snapbite.app.commons.SnapbiteBackgroundImage
import snapbite.app.commons.SnapbiteHeader
import snapbite.app.food.ui.FoodListViewModel
import snapbite.app.profile.google.GoogleAuthUiClient
import snapbite.app.profile.google.UserData
import snapbite.app.theme.snapbiteMaroon
import snapbite.app.theme.snapbiteOrange

class ProfileScreen : Screen {

    @Composable
    override fun Content() {

        val foodListViewModel: FoodListViewModel = koinViewModel()

        val scope = rememberCoroutineScope()

        val context = LocalContext.current

        val googleAuthUiClient by lazy {
            GoogleAuthUiClient(
                oneTapClient = Identity.getSignInClient(context)
            )
        }

        val userData = googleAuthUiClient.getSignedInUser()

        val navigator = LocalNavigator.currentOrThrow

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
                        ProfileScreenCard(foodListViewModel = foodListViewModel)
                    }

                    item(key = 4) {
                        Spacer(modifier = Modifier.weight(weight = 1f))
                    }

                    item(key = 5) {
                        SignOutButton(onSignOut = {
                            scope.launch { googleAuthUiClient.signOut() }
                            navigator.pop()
                            Toast.makeText(context, "You have signed out successfully!", Toast.LENGTH_LONG).show()
                        })
                    }

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

        Column(modifier = Modifier.fillMaxSize()) {

            userData?.userName?.let { Text(text = it, style = MaterialTheme.typography.bodyLarge) }

        }

    }

}


@Composable
fun ProfileScreenCard(foodListViewModel: FoodListViewModel) {

    val foodList by foodListViewModel.foods.collectAsState()

    Card(
        modifier = Modifier
            .height(height = 121.dp)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        ,elevation = CardDefaults.cardElevation(7.dp),
        colors = CardDefaults.cardColors(containerColor = snapbiteOrange)
    ) {

        Column(
            modifier = Modifier.padding(7.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "${foodList.size} Food Entries", style = MaterialTheme.typography.titleLarge)
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