package emmanuelmuturia.home

import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import emmanuelmuturia.theme.Caveat

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = emmanuelmuturia.uilayer.R.drawable.snapbite),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds
        )

        HomeScreenHeader()

    }

    HomeScreenFooter()

}


@Composable
fun HomeScreenHeader() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp, start = 14.dp, end = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = displayGreeting(),
            textAlign = TextAlign.Start,
            fontFamily = Caveat,
            fontSize = 28.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier
                    .padding(end = 21.dp)
                    .size(size = 30.dp),
                imageVector = Icons.Rounded.Search,
                contentDescription = "Search Icon",
                tint = Color.Black
            )

            Icon(
                modifier = Modifier.size(size = 30.dp),
                imageVector = Icons.Rounded.Notifications,
                contentDescription = "Search Icon",
                tint = Color.Black
            )
        }
    }
}


@Composable
fun HomeScreenFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 21.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        Icon(
            modifier = Modifier.size(size = 40.dp),
            imageVector = Icons.Rounded.Settings,
            tint = Color.Black,
            contentDescription = "Settings"
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        Icon(
            modifier = Modifier.size(size = 42.dp),
            imageVector = Icons.Rounded.AddCircle,
            tint = Color.Black,
            contentDescription = "Add Food Entry"
        )

        Spacer(modifier = Modifier.weight(weight = 1f))

        Icon(
            modifier = Modifier.size(size = 40.dp),
            imageVector = Icons.Rounded.AccountCircle,
            tint = Color.Black,
            contentDescription = "User Profile"
        )
    }
}

private fun displayGreeting(): String {
    return when (Calendar.getInstance()[Calendar.HOUR_OF_DAY]) {
        in 0..11 -> "Good Morning!" // 0 to 11 is considered morning
        in 12..16 -> "Good Afternoon!" // 12 to 16 is considered afternoon
        else -> "Good Evening!" // After 16 is considered evening
    }
}