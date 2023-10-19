package emmanuelmuturia.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import emmanuelmuturia.uilayer.R

val Caveat = FontFamily(
    Font(R.font.caveat_regular),
    Font(R.font.caveat_medium),
    Font(R.font.caveat_semi_bold),
    Font(R.font.caveat_bold)
)

// Set of Material typography styles to start with
val Typography = Typography(

    titleLarge = TextStyle(
        fontFamily = Caveat,
        fontWeight = FontWeight.Bold,
        fontSize = 21.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    headlineLarge = TextStyle(
        fontFamily = Caveat,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ),

    bodyLarge = TextStyle(
        fontFamily = Caveat,
        fontWeight = FontWeight.Medium,
        fontSize = 7.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ),

    labelLarge = TextStyle(
        fontFamily = Caveat,
        fontWeight = FontWeight.Normal,
        fontSize = 3.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    )
)