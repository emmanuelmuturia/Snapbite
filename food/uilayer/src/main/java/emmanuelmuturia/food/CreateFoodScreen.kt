package emmanuelmuturia.food

import android.os.Build
import android.widget.Toast
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import emmanuelmuturia.components.SnapbiteBackgroundImage
import emmanuelmuturia.day.DayScreenViewModel
import emmanuelmuturia.entities.FoodEntity
import emmanuelmuturia.photography.PhotoScreenViewModel
import emmanuelmuturia.theme.Caveat
import kotlin.math.absoluteValue

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateFoodScreen(navController: NavHostController) {

    val dayScreenViewModel: DayScreenViewModel = hiltViewModel()

    val foodList by dayScreenViewModel.foodList.collectAsState()

    val foodCaption by rememberSaveable { mutableStateOf(dayScreenViewModel.foodCaption.value) }
    val foodEmoji by rememberSaveable { mutableStateOf(dayScreenViewModel.foodEmoji.value) }
    val foodName by rememberSaveable { mutableStateOf(dayScreenViewModel.foodName.value) }

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

            CreateFoodScreenHeader(
                navController = navController
            )

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(height = 210.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {

                /*item {
                    FoodList()
                }*/

                item {
                    CreateFoodCaption(
                        foodCaption = foodCaption
                    )
                }

            }

        }

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateFoodScreenHeader(
    navController: NavHostController
) {

    val dayScreenViewModel: DayScreenViewModel = hiltViewModel()

    var foodCaption by rememberSaveable { mutableStateOf(value = "") }
    var foodEmoji by rememberSaveable { mutableStateOf(value = "") }
    var foodName by rememberSaveable { mutableStateOf(value = "") }

    val context = LocalContext.current

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

            Button(onClick = {
                dayScreenViewModel.addFood(foodEntity = FoodEntity(
                    foodName = foodName,
                    foodImage = "",
                    foodCaption = foodCaption,
                    foodEmoji = foodEmoji
                ))
                navController.navigate(route = "homeScreen")
                Toast.makeText(context, "Food item has been created!", Toast.LENGTH_LONG).show()
            }, shape = RoundedCornerShape(size = 10.dp)) {
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
                text = dayScreenViewModel.formatCurrentDate(),
                fontFamily = Caveat,
                color = Color.Black,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                //modifier = Modifier.size(size = 10.dp),
                text = foodEmoji,
                fontSize = 28.sp
            )

        }

        Spacer(modifier = Modifier.height(height = 21.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                value = foodName, onValueChange = { foodName = it },
                label = {
                    Text(
                        text = "Write your food name...",
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                shape = RoundedCornerShape(size = 21.dp),
            )
        }

    }

}

/*@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodList(
    modifier: Modifier = Modifier,
    pagerPaddingValues: PaddingValues = PaddingValues(horizontal = 65.dp),
    imageCornerRadius: Dp = 16.dp,
    imageHeight: Dp = 300.dp,
) {

    val photoScreenViewModel: PhotoScreenViewModel = hiltViewModel()

    val foodImages by photoScreenViewModel.bitmaps.collectAsState()

    val pagerState = rememberPagerState(pageCount = {
        foodImages.size
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
                    Image(
                        painter = painterResource(id = foodImages[page]),
                        contentDescription = "Food Photos",
                        contentScale = ContentScale.Crop,
                        modifier = modifier
                            .height(imageHeight)
                    )
                }
            }

        }
    }

}*/


@Composable
fun CreateFoodCaption(foodCaption: String) {

    val myFoodCaption = rememberSaveable { mutableStateOf(value = foodCaption) }

    Spacer(modifier = Modifier.height(height = 14.dp))

    OutlinedTextField(
        value = myFoodCaption.value, onValueChange = { myFoodCaption.value = it },
        label = {
            Text(
                text = "Write your food caption...",
                style = MaterialTheme.typography.labelLarge
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        shape = RoundedCornerShape(size = 21.dp),
    )

}