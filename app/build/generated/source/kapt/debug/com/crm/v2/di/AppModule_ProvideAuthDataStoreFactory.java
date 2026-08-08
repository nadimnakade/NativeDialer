package com.crm.v2.di;

import android.content.Context;
import com.crm.v2.data.datastore.AuthDataStore;
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
public final class AppModule_ProvideAuthDataStoreFactory implements Factory<AuthDataStore> {
  private final Provider<Context> contextProvider;

  public AppModule_ProvideAuthDataStoreFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AuthDataStore get() {
    return provideAuthDataStore(contextProvider.get());
  }

  public static AppModule_ProvideAuthDataStoreFactory create(
      javax.inject.Provider<Context> contextProvider) {
    return new AppModule_ProvideAuthDataStoreFactory(Providers.asDaggerProvider(contextProvider));
  }

  public static AppModule_ProvideAuthDataStoreFactory create(Provider<Context> contextProvider) {
    return new AppModule_ProvideAuthDataStoreFactory(contextProvider);
  }

  public static AuthDataStore provideAuthDataStore(Context context) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideAuthDataStore(context));
  }
}
