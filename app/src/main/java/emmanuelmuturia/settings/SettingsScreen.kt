package emmanuelmuturia.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    navigateBack: () -> Unit,
    navigateToAbout: () -> Unit,
    navigateToFAQ: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        emmanuelmuturia.commons.components.SnapbiteBackgroundImage()

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            emmanuelmuturia.commons.components.SnapbiteHeader(
                navigateBack = navigateBack,
                headerTitle = "Settings"
            )

            SettingsContent(
                navigateToAbout = navigateToAbout,
                navigateToFAQ = navigateToFAQ
            )

            Spacer(modifier = Modifier.weight(weight = 1f))

        }

    }

}


@Composable
private fun SettingsContent(
    navigateToAbout: () -> Unit,
    navigateToFAQ: () -> Unit
) {

    val settingsScreenViewModel: SettingsScreenViewModel = hiltViewModel()

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 7.dp), verticalArrangement = Arrangement.spacedBy(space = 28.dp)
    ) {

        item(key = 1) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .clickable { settingsScreenViewModel.navigateToNotificationsSettings(context = context) },
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(size = 28.dp),
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notification Settings",
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.width(width = 21.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = "Notifications",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Toggle Notification Tones and Vibrations",
                        style = MaterialTheme.typography.bodyLarge
                    )

                }

                Spacer(modifier = Modifier.weight(weight = 1f))

                Icon(
                    modifier = Modifier
                        .size(size = 28.dp)
                        .padding(all = 10.dp),
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Notification Settings",
                    tint = Color.White
                )

            }
        }

        item(key = 2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .clickable(onClick = navigateToAbout),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(size = 28.dp),
                    imageVector = Icons.Rounded.Info,
                    contentDescription = "About",
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.width(width = 21.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = "About",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Privacy Policy, Terms & Conditions, and About",
                        style = MaterialTheme.typography.bodyLarge
                    )

                }

            }
        }

        item(key = 3) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .clickable(onClick = navigateToFAQ),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(size = 28.dp),
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "FAQ",
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.width(width = 21.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = "FAQ",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Frequently Asked Questions (FAQ)",
                        style = MaterialTheme.typography.bodyLarge
                    )

                }

            }
        }

        item(key = 4) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .clickable(onClick = { settingsScreenViewModel.rateUs(context = context) }),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(size = 28.dp),
                    imageVector = Icons.Rounded.Star,
                    contentDescription = "Ratings",
                    tint = Color.Black
                )

                Spacer(modifier = Modifier.width(width = 21.dp))

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {

                    Text(
                        text = "Rate Us",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Rate us on GitHub!",
                        style = MaterialTheme.typography.bodyLarge
                    )

                }

            }
        }

    }

}