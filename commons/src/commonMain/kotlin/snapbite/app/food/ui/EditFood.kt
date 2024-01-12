package snapbite.app.food.ui

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import snapbite.app.about.ui.AboutScreenViewModel
import snapbite.app.commons.SnapbiteHeader
import snapbite.app.core.ui.ImagePicker
import snapbite.app.di.AppModule
import snapbite.app.faq.ui.FAQScreenViewModel
import snapbite.app.food.components.SnapbiteBackgroundImage
import snapbite.app.food.domain.Food
import snapbite.app.settings.ui.SettingsScreenViewModel
import snapbite.app.theme.snapbiteMaroon

data class EditFood(
    val selectedFood: Food?,
    val onEvent: (FoodListEvent) -> Unit,
    val modifier: Modifier = Modifier,
    val appModule: AppModule,
    val state: FoodListState,
    val foodListViewModel: FoodListViewModel,
    val imagePicker: ImagePicker,
    val aboutScreenViewModel: AboutScreenViewModel,
    val faqScreenViewModel: FAQScreenViewModel,
    val settingsScreenViewModel: SettingsScreenViewModel
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
                            imagePicker = imagePicker,
                            foodListViewModel = foodListViewModel,
                            state = state,
                            selectedFood = selectedFood,
                            onEvent = onEvent,
                            aboutScreenViewModel = aboutScreenViewModel,
                            faqScreenViewModel = faqScreenViewModel,
                            settingsScreenViewModel = settingsScreenViewModel
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
                        Spacer(Modifier.height(16.dp))
                    }

                    item {
                        FoodSuggestions(
                            foodListViewModel = foodListViewModel,
                            selectedFood = selectedFood
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
    imagePicker: ImagePicker,
    foodListViewModel: FoodListViewModel,
    state: FoodListState,
    selectedFood: Food?,
    onEvent: (FoodListEvent) -> Unit,
    aboutScreenViewModel: AboutScreenViewModel,
    faqScreenViewModel: FAQScreenViewModel,
    settingsScreenViewModel: SettingsScreenViewModel
) {

    val navigator = LocalNavigator.currentOrThrow

    Row(modifier = Modifier) {
        FilledTonalIconButton(
            onClick = {
                foodListViewModel.onEvent(event = FoodListEvent.EditFood(food = selectedFood!!))
                navigator.push(item = AddNewFood(
                    state = state,
                    foodListViewModel = foodListViewModel,
                    imagePicker = imagePicker,
                    onEvent = { event ->
                        if (event is FoodListEvent.OnAddFoodImage) {
                            imagePicker.pickImage()
                        }
                        onEvent(event)
                    },
                    aboutScreenViewModel = aboutScreenViewModel,
                    faqScreenViewModel = faqScreenViewModel,
                    settingsScreenViewModel = settingsScreenViewModel
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
            verticalArrangement = Arrangement.spacedBy(space = 3.5.dp)
        ) {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Black,
                fontSize = 21.sp,
                fontWeight = FontWeight.Normal
            )
        }
    }
}


@Composable
fun FoodSuggestions(
    foodListViewModel: FoodListViewModel,
    selectedFood: Food?
) {

    var foodSuggestions by remember { mutableStateOf<String?>(value = null) }

    val isResponseLoading by foodListViewModel.isResponseLoading.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                foodListViewModel.getSuggestion(selectedFood = selectedFood)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = snapbiteMaroon,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Get Suggestions...", style = MaterialTheme.typography.bodyLarge)
        }

        foodListViewModel.foodSuggestions?.let {
            foodSuggestions = it
        }

        if (isResponseLoading) {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator(
                    color = Color.Black,
                    strokeWidth = 3.5.dp
                )

            }

        } else {
            foodSuggestions?.let {
                Text(
                    modifier = Modifier.padding(all = 7.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

    }
}