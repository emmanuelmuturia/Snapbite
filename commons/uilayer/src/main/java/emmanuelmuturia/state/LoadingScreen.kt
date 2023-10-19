package emmanuelmuturia.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import emmanuelmuturia.uilayer.R

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.snapbite), contentDescription = "Background Image")

        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.background,
            strokeWidth = 7.dp
        )
    }
}