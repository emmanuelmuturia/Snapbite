package snapbite.app.food.data

import android.graphics.Bitmap
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import snapbite.app.food.domain.FoodRepository

class FoodRepositoryImplementation(
    private val generativeModel: GenerativeModel,
    private val image: Bitmap
) : FoodRepository {

    override suspend fun getSuggestion() {

        generativeModel.generateContent(
            content {
                image(image = image)
                text(text = "What are some of the ways in which I can make this food healthier to eat?")
            }
        )

    }

}