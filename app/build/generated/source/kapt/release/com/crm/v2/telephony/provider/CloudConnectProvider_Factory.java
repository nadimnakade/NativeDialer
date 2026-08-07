package com.crm.v2.telephony.provider;

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
public final class CloudConnectProvider_Factory implements Factory<CloudConnectProvider> {
  private final Provider<Context> contextProvider;

  public CloudConnectProvider_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public CloudConnectProvider get() {
    return newInstance(contextProvider.get());
  }

  public static CloudConnectProvider_Factory create(
      javax.inject.Provider<Context> contextProvider) {
    return new CloudConnectProvider_Factory(Providers.asDaggerProvider(contextProvider));
  }

  public static CloudConnectProvider_Factory create(Provider<Context> contextProvider) {
    return new CloudConnectProvider_Factory(contextProvider);
  }

  public static CloudConnectProvider newInstance(Context context) {
    return new CloudConnectProvider(context);
  }
}
