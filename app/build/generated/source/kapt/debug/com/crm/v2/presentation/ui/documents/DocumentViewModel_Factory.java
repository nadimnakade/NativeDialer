package com.crm.v2.presentation.ui.documents;

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
public final class DocumentViewModel_Factory implements Factory<DocumentViewModel> {
  private final Provider<DocumentRepository> documentRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public DocumentViewModel_Factory(Provider<DocumentRepository> documentRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.documentRepositoryProvider = documentRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public DocumentViewModel get() {
    return newInstance(documentRepositoryProvider.get(), authDataStoreProvider.get());
  }

  public static DocumentViewModel_Factory create(
      javax.inject.Provider<DocumentRepository> documentRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new DocumentViewModel_Factory(Providers.asDaggerProvider(documentRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static DocumentViewModel_Factory create(
      Provider<DocumentRepository> documentRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new DocumentViewModel_Factory(documentRepositoryProvider, authDataStoreProvider);
  }

  public static DocumentViewModel newInstance(DocumentRepository documentRepository,
      AuthDataStore authDataStore) {
    return new DocumentViewModel(documentRepository, authDataStore);
  }
}
