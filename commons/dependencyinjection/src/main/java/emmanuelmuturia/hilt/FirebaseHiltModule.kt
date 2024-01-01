package emmanuelmuturia.hilt

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseHiltModule {

    @Provides
    fun providesFirebaseCloudFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

}
