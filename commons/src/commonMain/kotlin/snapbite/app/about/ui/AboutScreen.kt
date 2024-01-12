package snapbite.app.about.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import org.koin.androidx.compose.koinViewModel
import snapbite.app.commons.SnapbiteHeader
import snapbite.app.food.components.SnapbiteBackgroundImage


class AboutScreen: Screen {

    @Composable
    override fun Content() {

        val aboutScreenViewModel: AboutScreenViewModel = koinViewModel()

        Box(modifier = Modifier.fillMaxSize()) {

            SnapbiteBackgroundImage()

            Column(modifier = Modifier.fillMaxSize()) {

                SnapbiteHeader(headerTitle = "About")

                AboutScreenContent(aboutScreenViewModel = aboutScreenViewModel)

            }

        }

    }

}


@Composable
private fun AboutScreenContent(aboutScreenViewModel: AboutScreenViewModel) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 7.dp, end = 7.dp),
        verticalArrangement = Arrangement.spacedBy(space = 28.dp)
    ) {

        item(key = 1) {
            AboutListItem(
                //imageId = R.drawable.privacy,
                text = "Privacy Policy",
                onClick = { aboutScreenViewModel.getPrivacyPolicy() }
            )
        }

        item(key = 2) {
            AboutListItem(
                //imageId = R.drawable.terms,
                text = "Terms & Conditions",
                onClick = { aboutScreenViewModel.getTermsAndConditions() }
            )
        }

        item(key = 3) {

            AboutListItem(
                //imageId = R.drawable.version,
                text = "App Version") { }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 57.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                Text(
                    text = aboutScreenViewModel.getAppVersion(),
                    style = MaterialTheme.typography.bodyLarge
                )

            }

        }

    }

}

@Composable
private fun AboutListItem(
    //imageId: Int,
    text: String,
    onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp)
            .clickable(onClick = onClick),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {

        /*Image(
            imageVector = ImageVector.vectorResource(id = imageId),
            contentDescription = text
        )

        Spacer(modifier = Modifier.width(width = 21.dp))*/

        Text(text = text, style = MaterialTheme.typography.titleLarge)

    }

}
