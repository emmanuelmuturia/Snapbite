package snapbite.app.food.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.ai.client.generativeai.GenerativeModel
import snapbite.app.BuildConfig
import snapbite.app.commons.SnapbiteHeader
import snapbite.app.core.ui.ImagePicker
import snapbite.app.di.AppModule
import snapbite.app.food.components.SnapbiteBackgroundImage
import snapbite.app.food.domain.Food
import snapbite.app.theme.snapbiteMaroon

data class EditFood(
    val selectedFood: Food?,
    val onEvent: (FoodListEvent) -> Unit,
    val modifier: Modifier = Modifier,
    val appModule: AppModule,
    val state: FoodListState,
    val foodListViewModel: FoodListViewModel,
    val imagePicker: ImagePicker
) : Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {

            SnapbiteBackgroundImage()

            Column(modifier = Modifier.fillMaxSize()) {

                SnapbiteHeader(headerTitle = "Edit Food")

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Spacer(Modifier.height(60.dp))
                    }

                    item {
                        FoodPhoto(
                            food = selectedFood,
                            iconSize = 50.dp,
                            modifier = Modifier
                                .size(150.dp)
                        )
                    }

                    item {
                        Spacer(Modifier.height(16.dp))
                    }

                    item {
                        Text(
                            text = "${selectedFood?.foodName}",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp
                        )
                    }

                    item {
                        Spacer(Modifier.height(16.dp))
                    }

                    item {
                        EditRow(
                            onDeleteClick = {
                                onEvent(FoodListEvent.DeleteFood)
                                navigator.popUntilRoot()
                            },
                            appModule = appModule,
                            imagePicker = imagePicker,
                            foodListViewModel = foodListViewModel,
                            state = state,
                            selectedFood = selectedFood,
                            onEvent = onEvent
                        )
                    }

                    item {
                        Spacer(Modifier.height(16.dp))
                    }

                    item {
                        FoodInfoSection(
                            title = "Food Caption",
                            value = selectedFood?.foodCaption ?: "-",
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    item {
                        FoodSuggestions(
                            foodListViewModel = foodListViewModel,
                            generativeModel = GenerativeModel(
                                modelName = "gemini-pro-vision",
                                apiKey = BuildConfig.geminiApiKey,
                            ),
                            image = BitmapFactory.decodeByteArray(
                                selectedFood?.foodImage,
                                0,
                                selectedFood?.foodImage?.size ?: 0
                            )
                        )
                    }

                }

            }

        }

    }

}


@Composable
private fun EditRow(
    onDeleteClick: () -> Unit,
    appModule: AppModule,
    imagePicker: ImagePicker,
    foodListViewModel: FoodListViewModel,
    state: FoodListState,
    selectedFood: Food?,
    onEvent: (FoodListEvent) -> Unit
) {

    val navigator = LocalNavigator.currentOrThrow

    Row(modifier = Modifier) {
        FilledTonalIconButton(
            onClick = {
                foodListViewModel.onEvent(event = FoodListEvent.EditFood(food = selectedFood!!))
                navigator.push(item = AddNewFood(
                    state = state,
                    appModule = appModule,
                    foodListViewModel = foodListViewModel,
                    imagePicker = imagePicker,
                    onEvent = { event ->
                        if (event is FoodListEvent.OnAddFoodImage) {
                            imagePicker.pickImage()
                        }
                        onEvent(event)
                    }
                ))
            },
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = snapbiteMaroon,
                contentColor = Color.Black
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Edit contact"
            )
        }
        FilledTonalIconButton(
            onClick = onDeleteClick,
            colors = IconButtonDefaults.filledTonalIconButtonColors(
                containerColor = snapbiteMaroon,
                contentColor = Color.Black
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete contact"
            )
        }
    }
}

@Composable
private fun FoodInfoSection(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(start = 21.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 12.sp
            )
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 18.sp
            )
        }
    }
}


@Composable
fun FoodSuggestions(foodListViewModel: FoodListViewModel, generativeModel: GenerativeModel, image: Bitmap) {

    var foodSuggestions by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                foodListViewModel.getSuggestion(
                    generativeModel = generativeModel,
                    image = image
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = snapbiteMaroon,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Get Suggestions...")
        }

        foodListViewModel.foodSuggestions?.let {
            foodSuggestions = it
        }

        foodSuggestions?.let { Text(text = it, style = MaterialTheme.typography.bodyLarge) }

    }
}