package snapbite.app.food.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import snapbite.app.food.domain.Food
import snapbite.app.theme.snapbiteOrange

@Composable
fun FoodListItem(food: Food, modifier: Modifier) {

    Card(
        modifier = modifier
            .height(height = 121.dp)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            //.clickable(onClick = onClick)
        ,
        elevation = CardDefaults.cardElevation(7.dp),
        colors = CardDefaults.cardColors(containerColor = snapbiteOrange)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Use SpaceBetween here
        ) {
            Column(
                modifier = modifier.padding(7.dp),
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = "Jan 6 2023", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(height = 7.dp))
                Text(text = food.foodName, style = MaterialTheme.typography.bodyLarge)
            }

            // Move the Box outside the Column and use horizontal alignment
            Box(
                modifier = modifier
                    .background(color = Color.Transparent)
                    .padding(end = 16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                food.foodEmoji?.let { Text(text = it) }
            }
        }
    }

}