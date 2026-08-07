package com.crm.v2.di;

import com.crm.v2.data.network.api.PickupApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvidePickupApiFactory implements Factory<PickupApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvidePickupApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PickupApi get() {
    return providePickupApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvidePickupApiFactory create(
      javax.inject.Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvidePickupApiFactory(Providers.asDaggerProvider(retrofitProvider));
  }

  public static NetworkModule_ProvidePickupApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvidePickupApiFactory(retrofitProvider);
  }

  public static PickupApi providePickupApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.providePickupApi(retrofit));
  }
}
