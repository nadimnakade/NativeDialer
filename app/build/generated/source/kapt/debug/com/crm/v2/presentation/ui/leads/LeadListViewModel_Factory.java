package com.crm.v2.presentation.ui.leads;

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
public final class LeadListViewModel_Factory implements Factory<LeadListViewModel> {
  private final Provider<LeadRepository> leadRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public LeadListViewModel_Factory(Provider<LeadRepository> leadRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.leadRepositoryProvider = leadRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public LeadListViewModel get() {
    return newInstance(leadRepositoryProvider.get(), authDataStoreProvider.get());
  }

  public static LeadListViewModel_Factory create(
      javax.inject.Provider<LeadRepository> leadRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new LeadListViewModel_Factory(Providers.asDaggerProvider(leadRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static LeadListViewModel_Factory create(Provider<LeadRepository> leadRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new LeadListViewModel_Factory(leadRepositoryProvider, authDataStoreProvider);
  }

  public static LeadListViewModel newInstance(LeadRepository leadRepository,
      AuthDataStore authDataStore) {
    return new LeadListViewModel(leadRepository, authDataStore);
  }
}
