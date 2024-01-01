package emmanuelmuturia.hilt;

import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import emmanuelmuturia.repository.FAQRepository;
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
public final class FAQHiltModule_ProvidesFAQRepositoryFactory implements Factory<FAQRepository> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public FAQHiltModule_ProvidesFAQRepositoryFactory(Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public FAQRepository get() {
    return providesFAQRepository(firestoreProvider.get());
  }

  public static FAQHiltModule_ProvidesFAQRepositoryFactory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new FAQHiltModule_ProvidesFAQRepositoryFactory(firestoreProvider);
  }

  public static FAQRepository providesFAQRepository(FirebaseFirestore firestore) {
    return Preconditions.checkNotNullFromProvides(FAQHiltModule.INSTANCE.providesFAQRepository(firestore));
  }
}
