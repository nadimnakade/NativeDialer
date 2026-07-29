package com.crm.v2.presentation;

import com.crm.v2.data.datastore.AuthDataStore;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<AuthDataStore> authDataStoreProvider;

  public MainActivity_MembersInjector(Provider<AuthDataStore> authDataStoreProvider) {
    this.authDataStoreProvider = authDataStoreProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<AuthDataStore> authDataStoreProvider) {
    return new MainActivity_MembersInjector(authDataStoreProvider);
  }

  public static MembersInjector<MainActivity> create(
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new MainActivity_MembersInjector(Providers.asDaggerProvider(authDataStoreProvider));
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectAuthDataStore(instance, authDataStoreProvider.get());
  }

  @InjectedFieldSignature("com.crm.v2.presentation.MainActivity.authDataStore")
  public static void injectAuthDataStore(MainActivity instance, AuthDataStore authDataStore) {
    instance.authDataStore = authDataStore;
  }
}
