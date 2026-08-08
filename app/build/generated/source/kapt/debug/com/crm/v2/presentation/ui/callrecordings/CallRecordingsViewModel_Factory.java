package com.crm.v2.presentation.ui.callrecordings;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.domain.repository.DocumentRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
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
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class CallRecordingsViewModel_Factory implements Factory<CallRecordingsViewModel> {
  private final Provider<DocumentRepository> documentRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public CallRecordingsViewModel_Factory(Provider<DocumentRepository> documentRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.documentRepositoryProvider = documentRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public CallRecordingsViewModel get() {
    return newInstance(documentRepositoryProvider.get(), authDataStoreProvider.get());
  }

  public static CallRecordingsViewModel_Factory create(
      javax.inject.Provider<DocumentRepository> documentRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new CallRecordingsViewModel_Factory(Providers.asDaggerProvider(documentRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static CallRecordingsViewModel_Factory create(
      Provider<DocumentRepository> documentRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new CallRecordingsViewModel_Factory(documentRepositoryProvider, authDataStoreProvider);
  }

  public static CallRecordingsViewModel newInstance(DocumentRepository documentRepository,
      AuthDataStore authDataStore) {
    return new CallRecordingsViewModel(documentRepository, authDataStore);
  }
}
