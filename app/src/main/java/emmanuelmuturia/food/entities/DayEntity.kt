package emmanuelmuturia.food.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "snapbiteDays")
data class DayEntity(
    @PrimaryKey(autoGenerate = true)
    val dayId: Int = 0,
    @ColumnInfo(name = "dayDate")
    val dayDate: String,
    @ColumnInfo(name = "dayEmoji")
    val dayEmoji: String,
    @ColumnInfo(name = "foodList")
    val foodList: List<FoodEntity>
)
