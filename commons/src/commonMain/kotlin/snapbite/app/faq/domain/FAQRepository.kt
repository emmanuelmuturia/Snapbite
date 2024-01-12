package snapbite.app.faq.domain

fun interface FAQRepository {

    suspend fun getFAQs(): List<FAQ>

}