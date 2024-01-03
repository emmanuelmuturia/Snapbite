package emmanuelmuturia.hilt;

import com.google.firebase.firestore.FirebaseFirestore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class FirebaseHiltModule_ProvidesFirebaseCloudFirestoreFactory implements Factory<FirebaseFirestore> {
  @Override
  public FirebaseFirestore get() {
    return providesFirebaseCloudFirestore();
  }

  public static FirebaseHiltModule_ProvidesFirebaseCloudFirestoreFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FirebaseFirestore providesFirebaseCloudFirestore() {
    return Preconditions.checkNotNullFromProvides(FirebaseHiltModule.INSTANCE.providesFirebaseCloudFirestore());
  }

  private static final class InstanceHolder {
    private static final FirebaseHiltModule_ProvidesFirebaseCloudFirestoreFactory INSTANCE = new FirebaseHiltModule_ProvidesFirebaseCloudFirestoreFactory();
  }
}
