package snapbite.app.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import snapbite.app.theme.snapbiteMaroon

@Composable
fun EmojiPicker(
    selectedEmoji: String,
    onEmojiSelected: (String) -> Unit
) {

    var expanded by rememberSaveable { mutableStateOf(value = false) }

    val emojis = listOf("\uD83D\uDE0B", "\uD83D\uDE03", "\uD83D\uDE04", "\uD83D\uDE09", "\uD83D\uDE0D")

    Box {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "How did the food make you feel?")

            Spacer(Modifier.height(height = 7.dp))

            Text(
                text = selectedEmoji,
                fontSize = 28.sp,
                modifier = Modifier
                    .clickable { expanded = true }
                    .padding(all = 8.dp)
            )

            DropdownMenu(
                modifier = Modifier.background(color = snapbiteMaroon),
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(x = 56.dp, y = (-140).dp)
            ) {
                emojis.forEach { emoji ->
                    DropdownMenuItem(
                        onClick = {
                            onEmojiSelected(emoji)
                            expanded = false
                        },
                        text = { Text(text = emoji) }
                    )
                }
            }

        }

    }
}