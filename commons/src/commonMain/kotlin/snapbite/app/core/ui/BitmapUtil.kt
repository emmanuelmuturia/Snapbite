package snapbite.app.core.ui

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

@Composable
expect fun rememberBitmapFromBytes(bytes: ByteArray?): ImageBitmap?