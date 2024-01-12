package snapbite.app.food.domain

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel

actual interface FoodRepository {

    actual suspend fun getSuggestion()

}