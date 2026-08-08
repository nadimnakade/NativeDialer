package com.crm.v2.data.repository;

import com.crm.v2.data.database.dao.CallLogDao;
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
public final class CallLogRepositoryImpl_Factory implements Factory<CallLogRepositoryImpl> {
  private final Provider<PickupApi> pickupApiProvider;

  private final Provider<CallLogDao> callLogDaoProvider;

  public CallLogRepositoryImpl_Factory(Provider<PickupApi> pickupApiProvider,
      Provider<CallLogDao> callLogDaoProvider) {
    this.pickupApiProvider = pickupApiProvider;
    this.callLogDaoProvider = callLogDaoProvider;
  }

  @Override
  public CallLogRepositoryImpl get() {
    return newInstance(pickupApiProvider.get(), callLogDaoProvider.get());
  }

  public static CallLogRepositoryImpl_Factory create(
      javax.inject.Provider<PickupApi> pickupApiProvider,
      javax.inject.Provider<CallLogDao> callLogDaoProvider) {
    return new CallLogRepositoryImpl_Factory(Providers.asDaggerProvider(pickupApiProvider), Providers.asDaggerProvider(callLogDaoProvider));
  }

  public static CallLogRepositoryImpl_Factory create(Provider<PickupApi> pickupApiProvider,
      Provider<CallLogDao> callLogDaoProvider) {
    return new CallLogRepositoryImpl_Factory(pickupApiProvider, callLogDaoProvider);
  }

  public static CallLogRepositoryImpl newInstance(PickupApi pickupApi, CallLogDao callLogDao) {
    return new CallLogRepositoryImpl(pickupApi, callLogDao);
  }
}
