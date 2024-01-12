package snapbite.app.food.components

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

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