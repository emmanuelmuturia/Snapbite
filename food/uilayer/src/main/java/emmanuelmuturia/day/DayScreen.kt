package emmanuelmuturia.day

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun DayScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.snapbite),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds
        )

        DayScreenHeader(navController = navController)

    }

    DayScreenFooter()

}


@Composable
fun DayScreenHeader(navController: NavHostController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

        }

        Spacer(modifier = Modifier.height(height = 21.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "21st October 2023",
                fontFamily = Caveat,
                fontSize = 28.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun DayScreenFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 21.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {

        Image(
            modifier = Modifier.size(size = 49.dp).clickable {  },
            painter = painterResource(id = R.drawable.photo_camera),
            contentDescription = "Camera Button"
        )

    }
}