package emmanuelmuturia.faq

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.repository.FAQRepository
import emmanuelmuturia.faq.FAQRepositoryImplementation

@Module
@InstallIn(SingletonComponent::class)
object FAQHiltModule {

    @Provides
    fun providesFAQRepository(firestore: FirebaseFirestore): FAQRepository {
        return emmanuelmuturia.faq.FAQRepositoryImplementation(firestore = firestore)
    }

}