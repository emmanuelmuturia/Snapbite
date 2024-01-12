package snapbite.app.food.components

import androidx.compose.runtime.Composable

@Composable
expect fun backHandler(onBack: () -> Unit, onDismiss: () -> Unit, exitDialogState: Boolean = true)