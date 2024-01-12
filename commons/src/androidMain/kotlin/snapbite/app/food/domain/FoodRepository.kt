package snapbite.app.food.domain

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse

actual interface FoodRepository {

    actual suspend fun response(selectedFood: Food?): GenerateContentResponse

    actual suspend fun result(selectedFood: Food?): String?

}