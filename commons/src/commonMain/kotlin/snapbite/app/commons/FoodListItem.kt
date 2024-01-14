package snapbite.app.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import snapbite.app.food.domain.FoodEntity
import snapbite.app.theme.snapbiteOrange

@Composable
fun FoodListItem(food: FoodEntity, modifier: Modifier) {

    Card(
        modifier = modifier
            .height(height = 149.dp)
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
        ,
        elevation = CardDefaults.cardElevation(7.dp),
        colors = CardDefaults.cardColors(containerColor = snapbiteOrange)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = modifier.padding(7.dp).fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(3.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(text = getFormattedCurrentDate(), style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(height = 7.dp))
                Text(text = food.foodName, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(height = 7.dp))
                Text(text = food.foodEmoji, style = MaterialTheme.typography.titleLarge)
            }

        }
    }

}


fun getFormattedCurrentDate(): String {
    val now = Clock.System.now().toLocalDateTime(timeZone = TimeZone.currentSystemDefault())
    val day = now.date.dayOfMonth
    val suffix = when (day) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else -> "th"
    }
    return "$day$suffix ${now.month.name} ${now.year}"
}