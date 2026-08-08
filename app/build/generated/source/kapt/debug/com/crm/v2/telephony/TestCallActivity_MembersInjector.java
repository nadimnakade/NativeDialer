package com.crm.v2.telephony;

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
public final class TestCallActivity_MembersInjector implements MembersInjector<TestCallActivity> {
  private final Provider<TelephonyManager> telephonyProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  public TestCallActivity_MembersInjector(Provider<TelephonyManager> telephonyProvider,
      Provider<AuthDataStore> authDataStoreProvider) {
    this.telephonyProvider = telephonyProvider;
    this.authDataStoreProvider = authDataStoreProvider;
  }

  public static MembersInjector<TestCallActivity> create(
      Provider<TelephonyManager> telephonyProvider, Provider<AuthDataStore> authDataStoreProvider) {
    return new TestCallActivity_MembersInjector(telephonyProvider, authDataStoreProvider);
  }

  public static MembersInjector<TestCallActivity> create(
      javax.inject.Provider<TelephonyManager> telephonyProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider) {
    return new TestCallActivity_MembersInjector(Providers.asDaggerProvider(telephonyProvider), Providers.asDaggerProvider(authDataStoreProvider));
  }

  @Override
  public void injectMembers(TestCallActivity instance) {
    injectTelephonyProvider(instance, telephonyProvider.get());
    injectAuthDataStore(instance, authDataStoreProvider.get());
  }

  @InjectedFieldSignature("com.crm.v2.telephony.TestCallActivity.telephonyProvider")
  public static void injectTelephonyProvider(TestCallActivity instance,
      TelephonyManager telephonyProvider) {
    instance.telephonyProvider = telephonyProvider;
  }

  @InjectedFieldSignature("com.crm.v2.telephony.TestCallActivity.authDataStore")
  public static void injectAuthDataStore(TestCallActivity instance, AuthDataStore authDataStore) {
    instance.authDataStore = authDataStore;
  }
}
