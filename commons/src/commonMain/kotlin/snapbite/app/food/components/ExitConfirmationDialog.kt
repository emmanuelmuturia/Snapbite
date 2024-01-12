package snapbite.app.food.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import snapbite.app.theme.snapbiteMaroon
import snapbite.app.theme.snapbiteOrange

@Composable
fun ExitConfirmationDialog(
    onConfirmExit: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 21.dp)),
        onDismissRequest = { onDismiss() },
        tonalElevation = 21.dp,
        text = {
            Text(
                text = "Are you sure you want to exit the app?",
                style = MaterialTheme.typography.titleLarge,
                lineHeight = 30.sp
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmExit()
                    onDismiss()
                },
                shape = RoundedCornerShape(size = 21.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = snapbiteMaroon)
            ) {
                Text(
                    text = "Yes",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                shape = RoundedCornerShape(size = 21.dp),
                border = BorderStroke(width = 1.dp, color = Color.Black),
                colors = ButtonDefaults.buttonColors(containerColor = snapbiteMaroon)
            ) {
                Text(
                    text = "No",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        },
        containerColor = snapbiteOrange,
        textContentColor = Color.Black,
        titleContentColor = Color.Black,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    )
}

