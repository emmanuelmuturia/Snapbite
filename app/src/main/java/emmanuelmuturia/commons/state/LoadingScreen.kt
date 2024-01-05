package emmanuelmuturia.commons.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import emmanuelmuturia.commons.uilayer.R
import emmanuelmuturia.commons.components.SnapbiteProgressIndicator

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.snapbite), contentDescription = "Background Image")

        SnapbiteProgressIndicator()
    }
}