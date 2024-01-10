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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
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

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(60.dp))
                FoodPhoto(
                    food = selectedFood,
                    iconSize = 50.dp,
                    modifier = Modifier
                        .size(150.dp)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "${selectedFood?.foodName}",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
                Spacer(Modifier.height(16.dp))
                EditRow(
                    onEditClick = {
                        selectedFood?.let {
                            onEvent(FoodListEvent.EditFood(food = it))
                        }
                    },
                    onDeleteClick = {
                        onEvent(FoodListEvent.DeleteFood)
                        navigator.popUntilRoot()
                    },
                    appModule = appModule,
                    modifier = Modifier,
                    imagePicker = imagePicker,
                    foodListViewModel = foodListViewModel,
                    state = state,
                    selectedFood = selectedFood,
                    onEvent = onEvent
                )
                Spacer(Modifier.height(16.dp))
                FoodInfoSection(
                    title = "Food Caption",
                    value = selectedFood?.foodCaption ?: "-",
                    icon = Icons.Rounded.Phone,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }

}


@Composable
private fun EditRow(
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier,
    appModule: AppModule,
    imagePicker: ImagePicker,
    foodListViewModel: FoodListViewModel,
    state: FoodListState,
    selectedFood: Food?,
    onEvent: (FoodListEvent) -> Unit
) {

    val navigator = LocalNavigator.currentOrThrow

    Row(modifier) {
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
    icon: ImageVector,
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