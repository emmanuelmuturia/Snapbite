package emmanuelmuturia.notifications

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import emmanuelmuturia.theme.Caveat
import emmanuelmuturia.uilayer.R

@Composable
fun NotificationsScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.snapbite),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds
        )

        NotificationsScreenHeader(navController = navController)

    }

}


@Composable
fun NotificationsScreenHeader(navController: NavHostController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 42.dp, start = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Icon(
            modifier = Modifier.clickable { navController.popBackStack() },
            imageVector = Icons.Rounded.ArrowBack,
            contentDescription = "Back Arrow",
            tint = Color.Black
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Notifications",
                fontFamily = Caveat,
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

    }

}