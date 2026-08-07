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
public final class DocumentRepositoryImpl_Factory implements Factory<DocumentRepositoryImpl> {
  private final Provider<PickupApi> pickupApiProvider;

  public DocumentRepositoryImpl_Factory(Provider<PickupApi> pickupApiProvider) {
    this.pickupApiProvider = pickupApiProvider;
  }

  @Override
  public DocumentRepositoryImpl get() {
    return newInstance(pickupApiProvider.get());
  }

  public static DocumentRepositoryImpl_Factory create(
      javax.inject.Provider<PickupApi> pickupApiProvider) {
    return new DocumentRepositoryImpl_Factory(Providers.asDaggerProvider(pickupApiProvider));
  }

  public static DocumentRepositoryImpl_Factory create(Provider<PickupApi> pickupApiProvider) {
    return new DocumentRepositoryImpl_Factory(pickupApiProvider);
  }

  public static DocumentRepositoryImpl newInstance(PickupApi pickupApi) {
    return new DocumentRepositoryImpl(pickupApi);
  }
}
