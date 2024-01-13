package snapbite.app.food.domain

import com.google.ai.client.generativeai.type.GenerateContentResponse

expect interface FoodRepository {

    suspend fun response(selectedFood: FoodEntity?): GenerateContentResponse

    suspend fun result(selectedFood: FoodEntity?): String?

}