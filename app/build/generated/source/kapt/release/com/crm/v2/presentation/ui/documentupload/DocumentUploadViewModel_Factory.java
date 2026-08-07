package com.crm.v2.presentation.ui.documentupload;

import android.app.Application;
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
public final class DocumentUploadViewModel_Factory implements Factory<DocumentUploadViewModel> {
  private final Provider<DocumentRepository> documentRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  private final Provider<Application> appProvider;

  public DocumentUploadViewModel_Factory(Provider<DocumentRepository> documentRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider, Provider<Application> appProvider) {
    this.documentRepositoryProvider = documentRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
    this.appProvider = appProvider;
  }

  @Override
  public DocumentUploadViewModel get() {
    return newInstance(documentRepositoryProvider.get(), authDataStoreProvider.get(), appProvider.get());
  }

  public static DocumentUploadViewModel_Factory create(
      javax.inject.Provider<DocumentRepository> documentRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider,
      javax.inject.Provider<Application> appProvider) {
    return new DocumentUploadViewModel_Factory(Providers.asDaggerProvider(documentRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider), Providers.asDaggerProvider(appProvider));
  }

  public static DocumentUploadViewModel_Factory create(
      Provider<DocumentRepository> documentRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider, Provider<Application> appProvider) {
    return new DocumentUploadViewModel_Factory(documentRepositoryProvider, authDataStoreProvider, appProvider);
  }

  public static DocumentUploadViewModel newInstance(DocumentRepository documentRepository,
      AuthDataStore authDataStore, Application app) {
    return new DocumentUploadViewModel(documentRepository, authDataStore, app);
  }
}
