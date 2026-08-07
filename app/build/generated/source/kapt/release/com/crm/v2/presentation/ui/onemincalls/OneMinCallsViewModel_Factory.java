package com.crm.v2.presentation.ui.onemincalls;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.domain.repository.ReportingRepository;
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
public final class OneMinCallsViewModel_Factory implements Factory<OneMinCallsViewModel> {
  private final Provider<ReportingRepository> reportingRepositoryProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public OneMinCallsViewModel_Factory(Provider<ReportingRepository> reportingRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.reportingRepositoryProvider = reportingRepositoryProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public OneMinCallsViewModel get() {
    return newInstance(reportingRepositoryProvider.get(), authDataStoreProvider.get());
  }

  public static OneMinCallsViewModel_Factory create(
      javax.inject.Provider<ReportingRepository> reportingRepositoryProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new OneMinCallsViewModel_Factory(Providers.asDaggerProvider(reportingRepositoryProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static OneMinCallsViewModel_Factory create(
      Provider<ReportingRepository> reportingRepositoryProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new OneMinCallsViewModel_Factory(reportingRepositoryProvider, authDataStoreProvider);
  }

  public static OneMinCallsViewModel newInstance(ReportingRepository reportingRepository,
      AuthDataStore authDataStore) {
    return new OneMinCallsViewModel(reportingRepository, authDataStore);
  }
}
