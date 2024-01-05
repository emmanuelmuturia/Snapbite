package emmanuelmuturia.food.converter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import emmanuelmuturia.food.entities.FoodEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.ByteArrayOutputStream

class SnapbiteTypeConverter {

    @TypeConverter
    fun fromFoodImagesList(bitmapList: List<Bitmap?>): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmapList.forEach { bitmap ->
            bitmap?.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        }
        val byteArray = byteArrayOutputStream.toByteArray()
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
    }

    @TypeConverter
    fun toFoodImagesList(encodedString: String): List<Bitmap?> {
        val byteArray = android.util.Base64.decode(encodedString, android.util.Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)?.let {
            listOf(it)
        } ?: emptyList()
    }

    @TypeConverter
    fun fromFoodEntityList(foodEntityList: List<FoodEntity>): String {
        return Json.encodeToString(value = foodEntityList)
    }

    @TypeConverter
    fun toFoodEntityList(foodImage: String): List<FoodEntity> {
        return Json.decodeFromString(string = foodImage)
    }

}