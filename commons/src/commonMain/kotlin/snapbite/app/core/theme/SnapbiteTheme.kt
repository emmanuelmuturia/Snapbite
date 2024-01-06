package snapbite.app.core.theme

import androidx.compose.runtime.Composable

@Composable
expect fun SnapbiteTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
)