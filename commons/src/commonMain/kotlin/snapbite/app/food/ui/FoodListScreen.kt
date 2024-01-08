package snapbite.app.food.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import snapbite.app.core.ui.ImagePicker
import snapbite.app.food.components.AddFoodSheet
import snapbite.app.food.components.FoodDetailSheet
import snapbite.app.food.components.FoodListItem
import snapbite.app.food.components.SnapbiteBackgroundImage
import snapbite.app.food.domain.Food
import snapbite.app.theme.snapbiteMaroon


data class FoodListScreen(
    val state: FoodListState,
    val newFood: Food?,
    val onEvent: (FoodListEvent) -> Unit,
    val imagePicker: ImagePicker,
    val foodListViewModel: FoodListViewModel
) : Screen {

    @Composable
    override fun Content() {

        imagePicker.registerPicker { imageBytes ->
            onEvent(FoodListEvent.OnFoodImagePicked(bytes = imageBytes))
        }

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        onEvent(FoodListEvent.OnAddNewFoodClick)
                    },
                    shape = RoundedCornerShape(20.dp),
                    containerColor = snapbiteMaroon
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Fastfood,
                        contentDescription = "Add Food",
                    )
                }
            }
        ) {
            it

            Box(modifier = Modifier.fillMaxSize()) {

                SnapbiteBackgroundImage()

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    item {
                        Spacer(modifier = Modifier.height(height = 21.dp))
                    }

                    item {
                        Text(
                            text = "My Foods (${state.foodList.size})",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    items(state.foodList) { food ->
                        FoodListItem(
                            food = food,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onEvent(FoodListEvent.SelectFood(food = food))
                                }
                                .padding(horizontal = 16.dp)
                        )
                    }
                }

            }
        }

        FoodDetailSheet(
            isOpen = state.isSelectedFoodSheetOpen,
            selectedFood = state.selectedFood,
            onEvent = onEvent,
        )
        AddFoodSheet(
            state = state,
            newFood = newFood,
            isOpen = state.isAddFoodSheetOpen,
            onEvent = { event ->
                if (event is FoodListEvent.OnAddFoodImage) {
                    imagePicker.pickImage()
                }
                onEvent(event)
            },
            foodListViewModel = foodListViewModel
        )

    }

}
