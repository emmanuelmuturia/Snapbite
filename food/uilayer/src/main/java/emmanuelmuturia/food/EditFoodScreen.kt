package emmanuelmuturia.food

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import emmanuelmuturia.theme.Caveat
import emmanuelmuturia.commons.uilayer.R
import java.time.LocalDate
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditFoodScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.snapbite),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds
        )

        EditFoodScreenHeader(navController = navController)

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(height = 210.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                item {
                    FoodList()
                }

                item {
                    FoodCaption()
                }

            }

        }

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditFoodScreenHeader(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 42.dp, start = 14.dp, end = 14.dp, bottom = 21.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                imageVector = Icons.Rounded.Close,
                contentDescription = "Cancel",
                tint = Color.Black,
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .size(size = 30.dp))

            Button(onClick = { }, shape = RoundedCornerShape(size = 10.dp)) {
                Text(
                    text = "Save",
                    fontFamily = Caveat,
                    color = Color.White,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }

        Spacer(modifier = Modifier.height(height = 10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = formatCurrentDate(),
                fontFamily = Caveat,
                color = Color.Black,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                //modifier = Modifier.size(size = 10.dp),
                text = "\uD83D\uDE0B",
                fontSize = 28.sp
            )

        }

        Spacer(modifier = Modifier.height(height = 21.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your Favourite Food",
                fontFamily = Caveat,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodList(
    modifier: Modifier = Modifier,
    pagerPaddingValues: PaddingValues = PaddingValues(horizontal = 65.dp),
    imageCornerRadius: Dp = 16.dp,
    imageHeight: Dp = 300.dp,
) {

    val pizzaImagesList = List(10) { R.drawable.pizza }

    val pagerState = rememberPagerState(pageCount = {
        pizzaImagesList.size
    })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            HorizontalPager(
                state = pagerState,
                contentPadding = pagerPaddingValues,
                modifier = modifier.weight(1f)
            ) { page ->
                // Calculate the absolute offset for the current page from the
                // scroll position. We use the absolute value which allows us to mirror
                // any effects for both directions
                val pageOffset =
                    (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

                val scaleFactor = 0.75f + (1f - 0.75f) * (1f - pageOffset.absoluteValue)

                Box(modifier = modifier
                    .graphicsLayer {
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    .alpha(
                        scaleFactor.coerceIn(0f, 1f)
                    )
                    .padding(10.dp)
                    .clip(RoundedCornerShape(imageCornerRadius))) {
                    Image(painter = painterResource(id = pizzaImagesList[page]), contentDescription = "Food Photos", contentScale = ContentScale.Crop, modifier = modifier
                        .height(imageHeight))
                }
            }

        }
    }

}


@Composable
fun FoodCaption() {

    Spacer(modifier = Modifier.height(height = 14.dp))

    Text(
        text = "Lorem Ipsum...",
        fontFamily = Caveat,
        color = Color.Black,
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold
    )

}

@RequiresApi(Build.VERSION_CODES.O)
private fun formatCurrentDate(): String {
    val currentDate = LocalDate.now()
    val dayOfMonth = currentDate.dayOfMonth
    val month =
        currentDate.month.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.ENGLISH)
    val year = currentDate.year

    val daySuffix = when (dayOfMonth) {
        1, 21, 31 -> "st"
        2, 22 -> "nd"
        3, 23 -> "rd"
        else -> "th"
    }

    return "$dayOfMonth$daySuffix $month $year"
}