package snapbite.commons.faq.dependencyinjection

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import emmanuelmuturia.faq.repository.FAQRepositoryImplementation
import emmanuelmuturia.faq.repository.FAQRepository

@Module
@InstallIn(SingletonComponent::class)
object FAQHiltModule {

    @Provides
    fun providesFAQRepository(firestore: FirebaseFirestore): FAQRepository {
        return FAQRepositoryImplementation(firestore = firestore)
    }

}