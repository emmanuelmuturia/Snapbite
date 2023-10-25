package emmanuelmuturia.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "snapbiteFoods", foreignKeys = [ForeignKey(
    entity = DayEntity::class,
    parentColumns = ["dayId"],
    childColumns = ["dayId"],
    onDelete = ForeignKey.CASCADE
)])
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val foodId: Int = 0,
    @ColumnInfo(name = "dayId")
    val dayId: Int,
    @ColumnInfo(name = "foodName")
    val foodName: String?,
    @ColumnInfo(name = "foodImage")
    val foodImage: String?,
    @ColumnInfo(name = "foodEmoji")
    val foodEmoji: String?,
    @ColumnInfo(name = "foodCaption")
    val foodCaption: String?
)
