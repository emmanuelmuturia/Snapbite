package emmanuelmuturia.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import emmanuelmuturia.components.SnapbiteBackgroundImage
import emmanuelmuturia.components.SnapbiteHeader

@Composable
fun ProfileScreen(navigateBack: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

        SnapbiteHeader(navigateBack = navigateBack, headerTitle = "My Profile")

    }

}