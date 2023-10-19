package emmanuelmuturia.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import emmanuelmuturia.uilayer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WelcomeScreen() {

    val sheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.snapbite),
            contentDescription = "Background Image...",
            contentScale = ContentScale.FillBounds
        )

    }
    ModalBottomSheet(
        modifier = Modifier.fillMaxSize(),
        onDismissRequest = { },
        containerColor = MaterialTheme.colorScheme.surface,
        sheetState = sheetState
    ) {
        Text(
            text = "Welcome!",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(height = 3.dp))
        Text(
            text = "Lorem Ipsum...",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(height = 7.dp))
        Button(onClick = { }) {
            Text(
                text = "Let's Go!"
            )
        }
    }
}