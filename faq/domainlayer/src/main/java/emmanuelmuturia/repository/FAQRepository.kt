package emmanuelmuturia.repository

import emmanuelmuturia.model.FAQ

fun interface FAQRepository {

    suspend fun getFAQs(): List<FAQ>

}