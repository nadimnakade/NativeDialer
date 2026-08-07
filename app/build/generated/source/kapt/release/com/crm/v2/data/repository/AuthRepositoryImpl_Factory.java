package com.crm.v2.data.repository;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.data.network.api.AuthApi;
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
public final class AuthRepositoryImpl_Factory implements Factory<AuthRepositoryImpl> {
  private final Provider<AuthApi> authApiProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public AuthRepositoryImpl_Factory(Provider<AuthApi> authApiProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.authApiProvider = authApiProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public AuthRepositoryImpl get() {
    return newInstance(authApiProvider.get(), authDataStoreProvider.get());
  }

  public static AuthRepositoryImpl_Factory create(javax.inject.Provider<AuthApi> authApiProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new AuthRepositoryImpl_Factory(Providers.asDaggerProvider(authApiProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static AuthRepositoryImpl_Factory create(Provider<AuthApi> authApiProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    return new AuthRepositoryImpl_Factory(authApiProvider, authDataStoreProvider);
  }

  public static AuthRepositoryImpl newInstance(AuthApi authApi, AuthDataStore authDataStore) {
    return new AuthRepositoryImpl(authApi, authDataStore);
  }
}
