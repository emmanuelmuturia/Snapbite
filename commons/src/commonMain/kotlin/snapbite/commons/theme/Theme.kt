package snapbite.commons.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import emmanuelmuturia.commons.theme.Typography

private val darkColourScheme = darkColorScheme(
    primary = snapbiteMaroon,
    surface = snapbiteOrange
)

private val lightColourScheme = lightColorScheme(
    primary = snapbiteMaroon,
    surface = snapbiteOrange
)

@Composable
fun SnapbiteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> darkColourScheme
        else -> lightColourScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = Typography
    )
}