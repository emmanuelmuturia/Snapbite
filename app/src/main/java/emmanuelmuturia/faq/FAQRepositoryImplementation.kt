package emmanuelmuturia.faq

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObjects
import emmanuelmuturia.model.FAQ
import emmanuelmuturia.repository.FAQRepository
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class FAQRepositoryImplementation @Inject constructor(
    private val firestore: FirebaseFirestore
) : FAQRepository {

    override suspend fun getFAQs(): List<FAQ> {
        return try {
            firestore.collection("FAQ").get().await().toObjects<FAQ>()
        } catch (e: Exception) {
            Timber.tag(tag = "Firebase Cloud Firestore Error")
                .e(message = "Could not fetch data due to: %s", e.printStackTrace())
            return emptyList()
        }
    }

}