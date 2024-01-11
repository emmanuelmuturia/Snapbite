package snapbite.app.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import snapbite.app.R

@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(painter = painterResource(id = R.drawable.snapbite), contentDescription = "Background Image")

        SnapbiteProgressIndicator()

    }
}