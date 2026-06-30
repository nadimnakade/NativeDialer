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
public final class PjsipManager_Factory implements Factory<PjsipManager> {
  private final Provider<Context> contextProvider;

  public PjsipManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public PjsipManager get() {
    return newInstance(contextProvider.get());
  }

  public static PjsipManager_Factory create(javax.inject.Provider<Context> contextProvider) {
    return new PjsipManager_Factory(Providers.asDaggerProvider(contextProvider));
  }

  public static PjsipManager_Factory create(Provider<Context> contextProvider) {
    return new PjsipManager_Factory(contextProvider);
  }

  public static PjsipManager newInstance(Context context) {
    return new PjsipManager(context);
  }
}
