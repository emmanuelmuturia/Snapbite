package snapbite.app.food.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NoFood
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import snapbite.app.core.ui.rememberBitmapFromBytes
import snapbite.app.food.domain.Food
import snapbite.app.theme.snapbiteOrange

@Composable
fun FoodPhoto(
    food: Food?,
    modifier: Modifier = Modifier,
    iconSize: Dp = 25.dp
) {

    val bitmap = rememberBitmapFromBytes(bytes = food?.foodImage)

    val photoModifier = modifier.clip(shape = RoundedCornerShape(percent = 35))

    if (bitmap != null) {
        Image(
            bitmap = bitmap,
            contentDescription = food?.foodName,
            modifier = photoModifier,
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = photoModifier
                .background(color = snapbiteOrange),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.NoFood,
                contentDescription = food?.foodName,
                modifier = Modifier.size(size = iconSize),
                tint = Color.Black
            )
        }
    }

}