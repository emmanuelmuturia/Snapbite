package snapbite.commons.profile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import snapbite.commons.profile.google.UserData

data class ProfileScreen(val userData: UserData?) : Screen {

    @Composable
    override fun Content() {

        Box(modifier = Modifier.fillMaxSize()) {

            emmanuelmuturia.commons.components.SnapbiteBackgroundImage()

            Column(modifier = Modifier.fillMaxSize()) {

                emmanuelmuturia.commons.components.SnapbiteHeader(
                    navigateBack = navigateBack,
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
                        ProfileScreenCard()
                    }

                    item(key = 4) {
                        Spacer(modifier = Modifier.weight(weight = 1f))
                    }

                    item(key = 5) {
                        SignOutButton(onSignOut = onSignOut)
                    }

                }

            }

        }

    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreenContent(
    userData: emmanuelmuturia.profile.google.UserData?
) {

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {

        GlideImage(
            modifier = Modifier.size(size = 140.dp),
            model = userData?.profilePictureUrl,
            contentDescription = "Profile Image",
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize()) {

            userData?.userName?.let { Text(text = it, style = MaterialTheme.typography.bodyLarge) }

        }

    }

}


@Composable
fun ProfileScreenCard() {

    val profileScreenViewModel: ProfileScreenViewModel = hiltViewModel()

    val foodList by profileScreenViewModel.foodList.collectAsState()

    Card(
        modifier = Modifier
            .height(height = 121.dp)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        //.clickable(onClick = onClick),
        , elevation = CardDefaults.cardElevation(7.dp),
        colors = CardDefaults.cardColors(containerColor = emmanuelmuturia.commons.theme.snapbiteOrange)
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
            colors = ButtonDefaults.buttonColors(containerColor = emmanuelmuturia.commons.theme.snapbiteMaroon)
        ) {
            Text(text = "Sign Out", style = MaterialTheme.typography.bodyLarge)
        }

    }

}