package snapbite.app.faq.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await
import snapbite.app.faq.domain.FAQ
import snapbite.app.faq.domain.FAQRepository
import timber.log.Timber

class FAQRepositoryImplementation(
    private val firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
) : FAQRepository {

    override suspend fun getFAQs(): List<FAQ> {
        return try {
            firebaseFirestore.collection("FAQ").get().await().toObjects<FAQ>()
        } catch (e: Exception) {
            Timber.tag(tag = "Firebase Cloud Firestore Error")
                .e(message = "Could not fetch data due to: %s", e.message)
            emptyList()
        }
    }

}