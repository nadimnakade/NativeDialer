package com.crm.v2.presentation.ui.dialer;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.domain.repository.DialerRepository;
import com.crm.v2.telephony.TelephonyManager;
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
public final class DialerViewModel_Factory implements Factory<DialerViewModel> {
  private final Provider<DialerRepository> dialerRepositoryProvider;

  private final Provider<TelephonyManager> telephonyManagerProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public DialerViewModel_Factory(Provider<DialerRepository> dialerRepositoryProvider,
      Provider<TelephonyManager> telephonyManagerProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.dialerRepositoryProvider = dialerRepositoryProvider;
    this.telephonyManagerProvider = telephonyManagerProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public DialerViewModel get() {
    return newInstance(dialerRepositoryProvider.get(), telephonyManagerProvider.get(), authDataStoreProvider.get());
  }

  public static DialerViewModel_Factory create(
      javax.inject.Provider<DialerRepository> dialerRepositoryProvider,
      javax.inject.Provider<TelephonyManager> telephonyManagerProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new DialerViewModel_Factory(Providers.asDaggerProvider(dialerRepositoryProvider), Providers.asDaggerProvider(telephonyManagerProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static DialerViewModel_Factory create(Provider<DialerRepository> dialerRepositoryProvider,
      Provider<TelephonyManager> telephonyManagerProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new DialerViewModel_Factory(dialerRepositoryProvider, telephonyManagerProvider, authDataStoreProvider);
  }

  public static DialerViewModel newInstance(DialerRepository dialerRepository,
      TelephonyManager telephonyManager, AuthDataStore authDataStore) {
    return new DialerViewModel(dialerRepository, telephonyManager, authDataStore);
  }
}
