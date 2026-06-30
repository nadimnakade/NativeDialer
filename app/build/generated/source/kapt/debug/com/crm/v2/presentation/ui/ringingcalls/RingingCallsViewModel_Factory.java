package com.crm.v2.presentation.ui.ringingcalls;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.domain.repository.DialerRepository;
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
public final class RingingCallsViewModel_Factory implements Factory<RingingCallsViewModel> {
  private final Provider<DialerRepository> dialerRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public RingingCallsViewModel_Factory(Provider<DialerRepository> dialerRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.dialerRepositoryProvider = dialerRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public RingingCallsViewModel get() {
    return newInstance(dialerRepositoryProvider.get(), authDataStoreProvider.get());
  }

  public static RingingCallsViewModel_Factory create(
      javax.inject.Provider<DialerRepository> dialerRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new RingingCallsViewModel_Factory(Providers.asDaggerProvider(dialerRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static RingingCallsViewModel_Factory create(
      Provider<DialerRepository> dialerRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new RingingCallsViewModel_Factory(dialerRepositoryProvider, authDataStoreProvider);
  }

  public static RingingCallsViewModel newInstance(DialerRepository dialerRepository,
      AuthDataStore authDataStore) {
    return new RingingCallsViewModel(dialerRepository, authDataStore);
  }
}
