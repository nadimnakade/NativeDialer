package com.crm.v2.presentation;

import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.telephony.pjsip.PjsipManager;
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

  private final Provider<PjsipManager> pjsipManagerProvider;

  public MainActivity_MembersInjector(Provider<AuthDataStore> authDataStoreProvider,
      Provider<PjsipManager> pjsipManagerProvider) {
    this.authDataStoreProvider = authDataStoreProvider;
    this.pjsipManagerProvider = pjsipManagerProvider;
  }

  public static MembersInjector<MainActivity> create(Provider<AuthDataStore> authDataStoreProvider,
      Provider<PjsipManager> pjsipManagerProvider) {
    return new MainActivity_MembersInjector(authDataStoreProvider, pjsipManagerProvider);
  }

  public static MembersInjector<MainActivity> create(
      javax.inject.Provider<AuthDataStore> authDataStoreProvider,
      javax.inject.Provider<PjsipManager> pjsipManagerProvider) {
    return new MainActivity_MembersInjector(Providers.asDaggerProvider(authDataStoreProvider), Providers.asDaggerProvider(pjsipManagerProvider));
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectAuthDataStore(instance, authDataStoreProvider.get());
    injectPjsipManager(instance, pjsipManagerProvider.get());
  }

  @InjectedFieldSignature("com.crm.v2.presentation.MainActivity.authDataStore")
  public static void injectAuthDataStore(MainActivity instance, AuthDataStore authDataStore) {
    instance.authDataStore = authDataStore;
  }

  @InjectedFieldSignature("com.crm.v2.presentation.MainActivity.pjsipManager")
  public static void injectPjsipManager(MainActivity instance, PjsipManager pjsipManager) {
    instance.pjsipManager = pjsipManager;
  }
}
