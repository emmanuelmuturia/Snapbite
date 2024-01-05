package emmanuelmuturia.commons.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun SnapbiteProgressIndicator() {

    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.background,
        strokeWidth = 7.dp
    )

}