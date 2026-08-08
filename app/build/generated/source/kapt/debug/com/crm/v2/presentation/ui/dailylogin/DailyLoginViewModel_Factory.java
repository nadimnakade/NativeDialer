package com.crm.v2.presentation.ui.dailylogin;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.domain.repository.DailyLoginRepository;
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
public final class DailyLoginViewModel_Factory implements Factory<DailyLoginViewModel> {
  private final Provider<DailyLoginRepository> dailyLoginRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public DailyLoginViewModel_Factory(Provider<DailyLoginRepository> dailyLoginRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.dailyLoginRepositoryProvider = dailyLoginRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public DailyLoginViewModel get() {
    return newInstance(dailyLoginRepositoryProvider.get(), authDataStoreProvider.get());
  }

  public static DailyLoginViewModel_Factory create(
      javax.inject.Provider<DailyLoginRepository> dailyLoginRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new DailyLoginViewModel_Factory(Providers.asDaggerProvider(dailyLoginRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static DailyLoginViewModel_Factory create(
      Provider<DailyLoginRepository> dailyLoginRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new DailyLoginViewModel_Factory(dailyLoginRepositoryProvider, authDataStoreProvider);
  }

  public static DailyLoginViewModel newInstance(DailyLoginRepository dailyLoginRepository,
      AuthDataStore authDataStore) {
    return new DailyLoginViewModel(dailyLoginRepository, authDataStore);
  }
}
