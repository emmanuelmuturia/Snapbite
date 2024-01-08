package snapbite.app.food.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import snapbite.app.core.ui.BottomSheetFromWish
import snapbite.app.food.domain.Food
import snapbite.app.food.ui.FoodListEvent
import snapbite.app.food.ui.FoodListState
import snapbite.app.food.ui.FoodListViewModel
import snapbite.app.food.ui.FoodPhoto
import snapbite.app.theme.snapbiteMaroon

@Composable
fun AddFoodSheet(
    state: FoodListState,
    newFood: Food?,
    isOpen: Boolean,
    onEvent: (FoodListEvent) -> Unit,
    modifier: Modifier = Modifier,
    foodListViewModel: FoodListViewModel
) {

    var foodEmoji by rememberSaveable { mutableStateOf(value = foodListViewModel.foodEmoji) }

    BottomSheetFromWish(
        visible = isOpen,
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {

            SnapbiteBackgroundImage()

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(60.dp))
                if (newFood?.foodImage == null) {
                    Box(
                        modifier = Modifier
                            .size(150.dp)
                            .clip(RoundedCornerShape(40))
                            .background(color = snapbiteMaroon)
                            .clickable {
                                onEvent(FoodListEvent.OnAddFoodImage)
                            }
                            .border(
                                width = 1.dp,
                                color = snapbiteMaroon,
                                shape = RoundedCornerShape(percent = 40)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add photo",
                            tint = Color.Black,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                } else {
                    FoodPhoto(
                        food = newFood,
                        modifier = Modifier
                            .size(150.dp)
                            .clickable {
                                onEvent(FoodListEvent.OnAddFoodImage)
                            }
                    )
                }
                Spacer(Modifier.height(16.dp))
                FoodTextField(
                    value = newFood?.foodName ?: "",
                    placeholder = "Food Name",
                    error = state.foodNameError,
                    onValueChanged = {
                        onEvent(FoodListEvent.OnFoodNameChanged(value = it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                FoodTextField(
                    value = newFood?.foodCaption ?: "",
                    placeholder = "Food Caption",
                    error = state.foodCaptionError,
                    onValueChanged = {
                        onEvent(FoodListEvent.OnFoodCaptionChanged(value = it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))

                if (newFood != null) {
                    EmojiPicker(
                        selectedEmoji = foodEmoji,
                        onEmojiSelected = { selectedEmoji ->
                            foodEmoji = selectedEmoji
                            newFood.foodEmoji = selectedEmoji
                        }
                    )
                }

                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = {
                        onEvent(FoodListEvent.SaveFood)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = snapbiteMaroon,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = "Save")
                }
            }
            IconButton(
                onClick = {
                    onEvent(FoodListEvent.DismissFood)
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = snapbiteMaroon,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = "Close"
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FoodTextField(
    value: String,
    placeholder: String,
    error: String?,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        OutlinedTextField(
            value = value,
            placeholder = {
                Text(text = placeholder)
            },
            onValueChange = onValueChanged,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        )
        if (error != null) {
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
