package snapbite.app.food.domain

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse

expect interface FoodRepository {

    suspend fun response(selectedFood: Food?): GenerateContentResponse

    suspend fun result(selectedFood: Food?): String?

}