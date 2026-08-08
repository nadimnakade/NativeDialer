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
public final class DailyLoginRepositoryImpl_Factory implements Factory<DailyLoginRepositoryImpl> {
  private final Provider<PickupApi> pickupApiProvider;

  public DailyLoginRepositoryImpl_Factory(Provider<PickupApi> pickupApiProvider) {
    this.pickupApiProvider = pickupApiProvider;
  }

  @Override
  public DailyLoginRepositoryImpl get() {
    return newInstance(pickupApiProvider.get());
  }

  public static DailyLoginRepositoryImpl_Factory create(
      javax.inject.Provider<PickupApi> pickupApiProvider) {
    return new DailyLoginRepositoryImpl_Factory(Providers.asDaggerProvider(pickupApiProvider));
  }

  public static DailyLoginRepositoryImpl_Factory create(Provider<PickupApi> pickupApiProvider) {
    return new DailyLoginRepositoryImpl_Factory(pickupApiProvider);
  }

  public static DailyLoginRepositoryImpl newInstance(PickupApi pickupApi) {
    return new DailyLoginRepositoryImpl(pickupApi);
  }
}
