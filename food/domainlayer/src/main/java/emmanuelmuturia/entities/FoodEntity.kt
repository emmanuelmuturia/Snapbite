package emmanuelmuturia.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "snapbiteFoods")
data class FoodEntity(
    @PrimaryKey(autoGenerate = true)
    val foodId: Int = 0,
    @ColumnInfo(name = "foodName")
    var foodName: String,
    @ColumnInfo(name = "foodImage")
    val foodImagesList: List<Bitmap?>,
    @ColumnInfo(name = "foodEmoji")
    val foodEmoji: String,
    @ColumnInfo(name = "foodCaption")
    var foodCaption: String
)
