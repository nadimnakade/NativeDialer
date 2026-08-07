package com.crm.v2.presentation.ui.settings;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.data.datastore.SettingsDataStore;
import com.crm.v2.domain.repository.AuthRepository;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<SettingsDataStore> settingsDataStoreProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  private final Provider<AuthRepository> authRepositoryProvider;

  public SettingsViewModel_Factory(Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<AuthDataStore> authDataStoreProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    this.settingsDataStoreProvider = settingsDataStoreProvider;
    this.authDataStoreProvider = authDataStoreProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(settingsDataStoreProvider.get(), authDataStoreProvider.get(), authRepositoryProvider.get());
  }

  public static SettingsViewModel_Factory create(
      javax.inject.Provider<SettingsDataStore> settingsDataStoreProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider,
      javax.inject.Provider<AuthRepository> authRepositoryProvider) {
    return new SettingsViewModel_Factory(Providers.asDaggerProvider(settingsDataStoreProvider), Providers.asDaggerProvider(authDataStoreProvider), Providers.asDaggerProvider(authRepositoryProvider));
  }

  public static SettingsViewModel_Factory create(
      Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<AuthDataStore> authDataStoreProvider,
      Provider<AuthRepository> authRepositoryProvider) {
    return new SettingsViewModel_Factory(settingsDataStoreProvider, authDataStoreProvider, authRepositoryProvider);
  }

  public static SettingsViewModel newInstance(SettingsDataStore settingsDataStore,
      AuthDataStore authDataStore, AuthRepository authRepository) {
    return new SettingsViewModel(settingsDataStore, authDataStore, authRepository);
  }
}
