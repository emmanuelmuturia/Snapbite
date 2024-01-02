package emmanuelmuturia.converter

import androidx.room.TypeConverter
import emmanuelmuturia.entities.FoodEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SnapbiteTypeConverter {

    @TypeConverter
    fun fromFoodList(foodList: List<FoodEntity>): String {
        return Json.encodeToString(value = foodList)
    }

    @TypeConverter
    fun toFoodList(foodListString: String): List<FoodEntity> {
        return Json.decodeFromString(string = foodListString)
    }

}