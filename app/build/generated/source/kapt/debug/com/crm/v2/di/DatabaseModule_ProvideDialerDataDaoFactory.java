package com.crm.v2.di;

import com.crm.v2.data.database.AppDatabase;
import com.crm.v2.data.database.dao.DialerDataDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class DatabaseModule_ProvideDialerDataDaoFactory implements Factory<DialerDataDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideDialerDataDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DialerDataDao get() {
    return provideDialerDataDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideDialerDataDaoFactory create(
      javax.inject.Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideDialerDataDaoFactory(Providers.asDaggerProvider(dbProvider));
  }

  public static DatabaseModule_ProvideDialerDataDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideDialerDataDaoFactory(dbProvider);
  }

  public static DialerDataDao provideDialerDataDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDialerDataDao(db));
  }
}
