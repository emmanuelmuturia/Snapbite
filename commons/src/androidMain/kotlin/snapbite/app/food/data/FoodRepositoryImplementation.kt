package snapbite.app.food.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import snapbite.app.food.domain.FoodEntity
import snapbite.app.food.domain.FoodRepository

class FoodRepositoryImplementation(
    private val generativeModel: GenerativeModel
) : FoodRepository {

    override suspend fun response(selectedFood: FoodEntity?): GenerateContentResponse {

        val image: Bitmap = BitmapFactory.decodeByteArray(
            selectedFood?.foodImage,
            0,
            selectedFood?.foodImage?.size ?: 0
        )

        return generativeModel.generateContent(
            content {
                image(image = image)
                text(text = "What are some of the ways in which I can make this food healthier to eat?")
            }
        )
    }

    override suspend fun result(selectedFood: FoodEntity?): String? {
        return response(selectedFood = selectedFood).text
    }

}