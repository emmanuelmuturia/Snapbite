package emmanuelmuturia.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import emmanuelmuturia.commons.uilayer.R

val Caveat = FontFamily(
    Font(R.font.caveat_regular),
    Font(R.font.caveat_medium),
    Font(R.font.caveat_semi_bold),
    Font(R.font.caveat_bold)
)

val Typography = Typography(

    headlineLarge = TextStyle(
        fontFamily = Caveat,
        fontSize = 32.sp,
        color = Color.Black,
        fontWeight = FontWeight.ExtraBold
    ),

    titleLarge = TextStyle(
        color = Color.Black,
        fontFamily = Caveat,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold
    ),

    bodyLarge = TextStyle(
        fontFamily = Caveat,
        fontSize = 21.sp,
        color = Color.Black,
        fontWeight = FontWeight.Medium
    ),

    labelLarge = TextStyle(
        color = Color.Black,
        fontFamily = Caveat,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )

)