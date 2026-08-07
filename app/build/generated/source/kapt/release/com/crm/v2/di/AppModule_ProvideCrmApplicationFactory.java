package com.crm.v2.di;

import android.content.Context;
import com.crm.v2.CrmApplication;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideCrmApplicationFactory implements Factory<CrmApplication> {
  private final Provider<Context> appProvider;

  public AppModule_ProvideCrmApplicationFactory(Provider<Context> appProvider) {
    this.appProvider = appProvider;
  }

  @Override
  public CrmApplication get() {
    return provideCrmApplication(appProvider.get());
  }

  public static AppModule_ProvideCrmApplicationFactory create(
      javax.inject.Provider<Context> appProvider) {
    return new AppModule_ProvideCrmApplicationFactory(Providers.asDaggerProvider(appProvider));
  }

  public static AppModule_ProvideCrmApplicationFactory create(Provider<Context> appProvider) {
    return new AppModule_ProvideCrmApplicationFactory(appProvider);
  }

  public static CrmApplication provideCrmApplication(Context app) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCrmApplication(app));
  }
}
