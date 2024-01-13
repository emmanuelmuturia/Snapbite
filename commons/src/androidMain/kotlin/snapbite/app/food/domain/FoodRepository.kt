package snapbite.app.food.domain

import com.google.ai.client.generativeai.type.GenerateContentResponse

actual interface FoodRepository {

    actual suspend fun response(selectedFood: FoodEntity?): GenerateContentResponse

    actual suspend fun result(selectedFood: FoodEntity?): String?

}