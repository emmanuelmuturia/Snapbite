package emmanuelmuturia.food

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import emmanuelmuturia.components.SnapbiteBackgroundImage
import emmanuelmuturia.day.DayScreenViewModel
import emmanuelmuturia.entities.FoodEntity
import emmanuelmuturia.theme.Caveat

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateFoodScreen(navController: NavHostController) {

    Box(modifier = Modifier.fillMaxSize()) {

        SnapbiteBackgroundImage()

        CreateFoodScreenHeader(
            navController = navController
        )

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateFoodScreenHeader(
    navController: NavHostController
) {

    val dayScreenViewModel: DayScreenViewModel = hiltViewModel()

    var foodCaption by rememberSaveable { mutableStateOf(value = "") }
    var foodEmoji by rememberSaveable { mutableStateOf(value = "\uD83D\uDE0B") }
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
                dayScreenViewModel.addFood(
                    foodEntity = FoodEntity(
                        foodName = foodName,
                        foodImage = "",
                        foodCaption = foodCaption,
                        foodEmoji = foodEmoji
                    )
                )
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

        Spacer(modifier = Modifier.height(height = 14.dp))

        OutlinedTextField(
            value = foodCaption, onValueChange = { foodCaption = it },
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

}