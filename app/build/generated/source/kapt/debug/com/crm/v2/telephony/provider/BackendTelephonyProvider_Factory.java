package com.crm.v2.telephony.provider;

import android.content.Context;
import com.crm.v2.data.datastore.AuthDataStore;
import com.crm.v2.telephony.RtpAudioManager;
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
public final class BackendTelephonyProvider_Factory implements Factory<BackendTelephonyProvider> {
  private final Provider<Context> contextProvider;

  private final Provider<AuthDataStore> authDataStoreProvider;

  private final Provider<RtpAudioManager> rtpAudioManagerProvider;

  public BackendTelephonyProvider_Factory(Provider<Context> contextProvider,
      Provider<AuthDataStore> authDataStoreProvider,
      Provider<RtpAudioManager> rtpAudioManagerProvider) {
    this.contextProvider = contextProvider;
    this.authDataStoreProvider = authDataStoreProvider;
    this.rtpAudioManagerProvider = rtpAudioManagerProvider;
  }

  @Override
  public BackendTelephonyProvider get() {
    return newInstance(contextProvider.get(), authDataStoreProvider.get(), rtpAudioManagerProvider.get());
  }

  public static BackendTelephonyProvider_Factory create(
      javax.inject.Provider<Context> contextProvider,
      javax.inject.Provider<AuthDataStore> authDataStoreProvider,
      javax.inject.Provider<RtpAudioManager> rtpAudioManagerProvider) {
    return new BackendTelephonyProvider_Factory(Providers.asDaggerProvider(contextProvider), Providers.asDaggerProvider(authDataStoreProvider), Providers.asDaggerProvider(rtpAudioManagerProvider));
  }

  public static BackendTelephonyProvider_Factory create(Provider<Context> contextProvider,
      Provider<AuthDataStore> authDataStoreProvider,
      Provider<RtpAudioManager> rtpAudioManagerProvider) {
    return new BackendTelephonyProvider_Factory(contextProvider, authDataStoreProvider, rtpAudioManagerProvider);
  }

  public static BackendTelephonyProvider newInstance(Context context, AuthDataStore authDataStore,
      RtpAudioManager rtpAudioManager) {
    return new BackendTelephonyProvider(context, authDataStore, rtpAudioManager);
  }
}
