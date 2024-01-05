package emmanuelmuturia.commons.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import snapbite.app.R

@Composable
fun SnapbiteBackgroundImage() {

    Image(
        painter = painterResource(id = R.drawable.snapbite),
        contentDescription = "Background Image",
        contentScale = ContentScale.FillBounds
    )

}