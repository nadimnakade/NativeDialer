package com.crm.v2.services;

import com.crm.v2.telephony.TelephonyManager;
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
public final class ForegroundCallService_MembersInjector implements MembersInjector<ForegroundCallService> {
  private final Provider<TelephonyManager> telephonyManagerProvider;

  public ForegroundCallService_MembersInjector(
      Provider<TelephonyManager> telephonyManagerProvider) {
    this.telephonyManagerProvider = telephonyManagerProvider;
  }

  public static MembersInjector<ForegroundCallService> create(
      Provider<TelephonyManager> telephonyManagerProvider) {
    return new ForegroundCallService_MembersInjector(telephonyManagerProvider);
  }

  public static MembersInjector<ForegroundCallService> create(
      javax.inject.Provider<TelephonyManager> telephonyManagerProvider) {
    return new ForegroundCallService_MembersInjector(Providers.asDaggerProvider(telephonyManagerProvider));
  }

  @Override
  public void injectMembers(ForegroundCallService instance) {
    injectTelephonyManager(instance, telephonyManagerProvider.get());
  }

  @InjectedFieldSignature("com.crm.v2.services.ForegroundCallService.telephonyManager")
  public static void injectTelephonyManager(ForegroundCallService instance,
      TelephonyManager telephonyManager) {
    instance.telephonyManager = telephonyManager;
  }
}
