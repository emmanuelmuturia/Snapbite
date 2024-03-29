package snapbite.app.commons

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import snapbite.app.commons.ExitConfirmationDialog

@Composable
actual fun backHandler(onBack: () -> Unit, onDismiss: () -> Unit, exitDialogState: Boolean) {

    val context = LocalContext.current

    BackHandler(enabled = true, onBack = onBack)

    if (exitDialogState) {
        ExitConfirmationDialog(onConfirmExit = {
            (context as? ComponentActivity)?.finish()
        }, onDismiss = onDismiss)
    }

}