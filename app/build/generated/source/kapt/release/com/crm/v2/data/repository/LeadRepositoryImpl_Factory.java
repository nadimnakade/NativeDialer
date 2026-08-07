package com.crm.v2.data.repository;

import com.crm.v2.data.database.dao.LeadDao;
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
public final class LeadRepositoryImpl_Factory implements Factory<LeadRepositoryImpl> {
  private final Provider<PickupApi> pickupApiProvider;

  private final Provider<LeadDao> leadDaoProvider;

  public LeadRepositoryImpl_Factory(Provider<PickupApi> pickupApiProvider,
      Provider<LeadDao> leadDaoProvider) {
    this.pickupApiProvider = pickupApiProvider;
    this.leadDaoProvider = leadDaoProvider;
  }

  @Override
  public LeadRepositoryImpl get() {
    return newInstance(pickupApiProvider.get(), leadDaoProvider.get());
  }

  public static LeadRepositoryImpl_Factory create(
      javax.inject.Provider<PickupApi> pickupApiProvider,
      javax.inject.Provider<LeadDao> leadDaoProvider) {
    return new LeadRepositoryImpl_Factory(Providers.asDaggerProvider(pickupApiProvider), Providers.asDaggerProvider(leadDaoProvider));
  }

  public static LeadRepositoryImpl_Factory create(Provider<PickupApi> pickupApiProvider,
      Provider<LeadDao> leadDaoProvider) {
    return new LeadRepositoryImpl_Factory(pickupApiProvider, leadDaoProvider);
  }

  public static LeadRepositoryImpl newInstance(PickupApi pickupApi, LeadDao leadDao) {
    return new LeadRepositoryImpl(pickupApi, leadDao);
  }
}
