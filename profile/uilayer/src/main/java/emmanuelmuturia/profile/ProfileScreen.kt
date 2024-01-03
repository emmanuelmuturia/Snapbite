package emmanuelmuturia.profile

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
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import emmanuelmuturia.components.SnapbiteBackgroundImage
import emmanuelmuturia.components.SnapbiteHeader
import emmanuelmuturia.google.UserData
import emmanuelmuturia.theme.snapbiteMaroon
import emmanuelmuturia.theme.snapbiteOrange

@Composable
fun ProfileScreen(navigateBack: () -> Unit, userData: UserData?, onSignOut: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

        Column(modifier = Modifier.fillMaxSize()) {

            SnapbiteHeader(navigateBack = navigateBack, headerTitle = "My Profile")

            Spacer(modifier = Modifier.height(height = 14.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(weight = 1f)
            ) {

                item {
                    ProfileScreenContent(userData = userData)
                }

                item {
                    Spacer(modifier = Modifier.height(height = 7.dp))
                }

                item {
                    ProfileScreenCard()
                }

                item {
                    Spacer(modifier = Modifier.weight(weight = 1f))
                }

                item {
                    SignOutButton(onSignOut = onSignOut)
                }

            }

        }

    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProfileScreenContent(
    userData: UserData?
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