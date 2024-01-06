package snapbite.app.food.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Fastfood
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import snapbite.app.R
import snapbite.app.food.components.FoodListItem
import snapbite.app.food.components.SnapbiteBackgroundImage
import snapbite.app.food.domain.Food

@Composable
fun FoodListScreen(
    state: FoodListState,
    newFood: Food?,
    onEvent: (FoodListEvent) -> Unit,
    imagePicker: ImagePicker
) {

    imagePicker.registerPicker { imageBytes ->
        onEvent(FoodListEvent.OnFoodImagePicked(bytes = imageBytes))
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(FoodListEvent.OnAddNewFoodClick)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Fastfood,
                    contentDescription = "Add Food"
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

    ContactDetailSheet(
        isOpen = state.isSelectedContactSheetOpen,
        selectedContact = state.selectedContact,
        onEvent = onEvent,
    )
    AddContactSheet(
        state = state,
        newContact = newContact,
        isOpen = state.isAddContactSheetOpen,
        onEvent = { event ->
            if (event is ContactListEvent.OnAddPhotoClicked) {
                imagePicker.pickImage()
            }
            onEvent(event)
        },
    )

}