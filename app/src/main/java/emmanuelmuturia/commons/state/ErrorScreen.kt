package emmanuelmuturia.commons.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import emmanuelmuturia.commons.uilayer.R
import emmanuelmuturia.commons.components.SnapbiteProgressIndicator

@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(painter = painterResource(id = R.drawable.snapbite), contentDescription = "Background Image")

        SnapbiteProgressIndicator()

    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen()
}