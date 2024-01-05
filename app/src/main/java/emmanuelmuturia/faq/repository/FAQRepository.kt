package emmanuelmuturia.faq.repository

import emmanuelmuturia.faq.model.FAQ

fun interface FAQRepository {

    suspend fun getFAQs(): List<FAQ>

}