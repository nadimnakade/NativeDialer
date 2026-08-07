package com.crm.v2.presentation.ui.leads;

import androidx.lifecycle.SavedStateHandle;
import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.domain.repository.LeadRepository;
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
public final class LeadDetailViewModel_Factory implements Factory<LeadDetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<LeadRepository> leadRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public LeadDetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<LeadRepository> leadRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.leadRepositoryProvider = leadRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public LeadDetailViewModel get() {
    return newInstance(savedStateHandleProvider.get(), leadRepositoryProvider.get(), authDataStoreProvider.get());
  }

  public static LeadDetailViewModel_Factory create(
      javax.inject.Provider<SavedStateHandle> savedStateHandleProvider,
      javax.inject.Provider<LeadRepository> leadRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new LeadDetailViewModel_Factory(Providers.asDaggerProvider(savedStateHandleProvider), Providers.asDaggerProvider(leadRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static LeadDetailViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<LeadRepository> leadRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new LeadDetailViewModel_Factory(savedStateHandleProvider, leadRepositoryProvider, authDataStoreProvider);
  }

  public static LeadDetailViewModel newInstance(SavedStateHandle savedStateHandle,
      LeadRepository leadRepository, AuthDataStore authDataStore) {
    return new LeadDetailViewModel(savedStateHandle, leadRepository, authDataStore);
  }
}
