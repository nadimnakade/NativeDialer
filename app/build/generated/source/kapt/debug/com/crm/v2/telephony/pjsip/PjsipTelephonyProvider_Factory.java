package com.crm.v2.telephony.pjsip;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class PjsipTelephonyProvider_Factory implements Factory<PjsipTelephonyProvider> {
  private final Provider<Context> contextProvider;

  private final Provider<PjsipManager> pjsipManagerProvider;

  public PjsipTelephonyProvider_Factory(Provider<Context> contextProvider,
      Provider<PjsipManager> pjsipManagerProvider) {
    this.contextProvider = contextProvider;
    this.pjsipManagerProvider = pjsipManagerProvider;
  }

  @Override
  public PjsipTelephonyProvider get() {
    return newInstance(contextProvider.get(), pjsipManagerProvider.get());
  }

  public static PjsipTelephonyProvider_Factory create(
      javax.inject.Provider<Context> contextProvider,
      javax.inject.Provider<PjsipManager> pjsipManagerProvider) {
    return new PjsipTelephonyProvider_Factory(Providers.asDaggerProvider(contextProvider), Providers.asDaggerProvider(pjsipManagerProvider));
  }

  public static PjsipTelephonyProvider_Factory create(Provider<Context> contextProvider,
      Provider<PjsipManager> pjsipManagerProvider) {
    return new PjsipTelephonyProvider_Factory(contextProvider, pjsipManagerProvider);
  }

  public static PjsipTelephonyProvider newInstance(Context context, PjsipManager pjsipManager) {
    return new PjsipTelephonyProvider(context, pjsipManager);
  }
}
