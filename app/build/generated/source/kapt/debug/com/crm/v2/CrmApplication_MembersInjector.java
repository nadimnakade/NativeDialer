package com.crm.v2;

import androidx.hilt.work.HiltWorkerFactory;
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
public final class CrmApplication_MembersInjector implements MembersInjector<CrmApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public CrmApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<CrmApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new CrmApplication_MembersInjector(workerFactoryProvider);
  }

  public static MembersInjector<CrmApplication> create(
      javax.inject.Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new CrmApplication_MembersInjector(Providers.asDaggerProvider(workerFactoryProvider));
  }

  @Override
  public void injectMembers(CrmApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.crm.v2.CrmApplication.workerFactory")
  public static void injectWorkerFactory(CrmApplication instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
