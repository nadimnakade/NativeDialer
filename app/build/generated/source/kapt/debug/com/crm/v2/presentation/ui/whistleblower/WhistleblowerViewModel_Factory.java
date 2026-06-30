package com.crm.v2.presentation.ui.whistleblower;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.domain.repository.SettingsRepository;
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
public final class WhistleblowerViewModel_Factory implements Factory<WhistleblowerViewModel> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public WhistleblowerViewModel_Factory(Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public WhistleblowerViewModel get() {
    return newInstance(settingsRepositoryProvider.get(), authDataStoreProvider.get());
  }

  public static WhistleblowerViewModel_Factory create(
      javax.inject.Provider<SettingsRepository> settingsRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new WhistleblowerViewModel_Factory(Providers.asDaggerProvider(settingsRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static WhistleblowerViewModel_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new WhistleblowerViewModel_Factory(settingsRepositoryProvider, authDataStoreProvider);
  }

  public static WhistleblowerViewModel newInstance(SettingsRepository settingsRepository,
      AuthDataStore authDataStore) {
    return new WhistleblowerViewModel(settingsRepository, authDataStore);
  }
}
