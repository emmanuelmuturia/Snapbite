package snapbite.app.food.domain

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel

expect interface FoodRepository {

    suspend fun getSuggestion()

}