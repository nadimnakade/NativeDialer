package com.crm.v2.services;

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
public final class CrmFirebaseMessagingService_MembersInjector implements MembersInjector<CrmFirebaseMessagingService> {
  private final Provider<AuthDataStore> authDataStoreProvider;

  public CrmFirebaseMessagingService_MembersInjector(
      Provider<AuthDataStore> authDataStoreProvider) {
    this.authDataStoreProvider = authDataStoreProvider;
  }

  public static MembersInjector<CrmFirebaseMessagingService> create(
      Provider<AuthDataStore> authDataStoreProvider) {
    return new CrmFirebaseMessagingService_MembersInjector(authDataStoreProvider);
  }

  public static MembersInjector<CrmFirebaseMessagingService> create(
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new CrmFirebaseMessagingService_MembersInjector(Providers.asDaggerProvider(authDataStoreProvider));
  }

  @Override
  public void injectMembers(CrmFirebaseMessagingService instance) {
    injectAuthDataStore(instance, authDataStoreProvider.get());
  }

  @InjectedFieldSignature("com.crm.v2.services.CrmFirebaseMessagingService.authDataStore")
  public static void injectAuthDataStore(CrmFirebaseMessagingService instance,
      AuthDataStore authDataStore) {
    instance.authDataStore = authDataStore;
  }
}
