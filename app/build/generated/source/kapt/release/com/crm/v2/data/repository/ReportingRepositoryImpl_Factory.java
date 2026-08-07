package com.crm.v2.data.repository;

import com.crm.v2.data.network.api.PickupApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ReportingRepositoryImpl_Factory implements Factory<ReportingRepositoryImpl> {
  private final Provider<PickupApi> pickupApiProvider;

  public ReportingRepositoryImpl_Factory(Provider<PickupApi> pickupApiProvider) {
    this.pickupApiProvider = pickupApiProvider;
  }

  @Override
  public ReportingRepositoryImpl get() {
    return newInstance(pickupApiProvider.get());
  }

  public static ReportingRepositoryImpl_Factory create(
      javax.inject.Provider<PickupApi> pickupApiProvider) {
    return new ReportingRepositoryImpl_Factory(Providers.asDaggerProvider(pickupApiProvider));
  }

  public static ReportingRepositoryImpl_Factory create(Provider<PickupApi> pickupApiProvider) {
    return new ReportingRepositoryImpl_Factory(pickupApiProvider);
  }

  public static ReportingRepositoryImpl newInstance(PickupApi pickupApi) {
    return new ReportingRepositoryImpl(pickupApi);
  }
}
