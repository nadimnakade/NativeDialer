package com.crm.v2.presentation.ui.pickup;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.domain.repository.PickupRepository;
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
public final class PickupViewModel_Factory implements Factory<PickupViewModel> {
  private final Provider<PickupRepository> pickupRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public PickupViewModel_Factory(Provider<PickupRepository> pickupRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.pickupRepositoryProvider = pickupRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public PickupViewModel get() {
    return newInstance(pickupRepositoryProvider.get(), authDataStoreProvider.get());
  }

  public static PickupViewModel_Factory create(
      javax.inject.Provider<PickupRepository> pickupRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new PickupViewModel_Factory(Providers.asDaggerProvider(pickupRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static PickupViewModel_Factory create(Provider<PickupRepository> pickupRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new PickupViewModel_Factory(pickupRepositoryProvider, authDataStoreProvider);
  }

  public static PickupViewModel newInstance(PickupRepository pickupRepository,
      AuthDataStore authDataStore) {
    return new PickupViewModel(pickupRepository, authDataStore);
  }
}
