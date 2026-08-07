package com.crm.v2.data.network.interceptor;

import com.crm.v2.data.datastore.AuthDataStore;
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
public final class AuthInterceptor_Factory implements Factory<AuthInterceptor> {
  private final Provider<AuthDataStore> authDataStoreProvider;

  public AuthInterceptor_Factory(Provider<AuthDataStore> authDataStoreProvider) {
    this.authDataStoreProvider = authDataStoreProvider;
  }

  @Override
  public AuthInterceptor get() {
    return newInstance(authDataStoreProvider.get());
  }

  public static AuthInterceptor_Factory create(
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new AuthInterceptor_Factory(Providers.asDaggerProvider(authDataStoreProvider));
  }

  public static AuthInterceptor_Factory create(Provider<AuthDataStore> authDataStoreProvider) {
    return new AuthInterceptor_Factory(authDataStoreProvider);
  }

  public static AuthInterceptor newInstance(AuthDataStore authDataStore) {
    return new AuthInterceptor(authDataStore);
  }
}
