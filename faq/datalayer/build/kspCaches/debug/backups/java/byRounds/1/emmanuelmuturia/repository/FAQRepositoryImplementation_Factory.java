package emmanuelmuturia.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class FAQRepositoryImplementation_Factory implements Factory<FAQRepositoryImplementation> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public FAQRepositoryImplementation_Factory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public FAQRepositoryImplementation get() {
    return newInstance(firestoreProvider.get());
  }

  public static FAQRepositoryImplementation_Factory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new FAQRepositoryImplementation_Factory(firestoreProvider);
  }

  public static FAQRepositoryImplementation newInstance(FirebaseFirestore firestore) {
    return new FAQRepositoryImplementation(firestore);
  }
}
