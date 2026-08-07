package com.crm.v2.di;

import com.crm.v2.data.database.AppDatabase;
import com.crm.v2.data.database.dao.SyncQueueDao;
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
public final class DatabaseModule_ProvideSyncQueueDaoFactory implements Factory<SyncQueueDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideSyncQueueDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public SyncQueueDao get() {
    return provideSyncQueueDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideSyncQueueDaoFactory create(
      javax.inject.Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideSyncQueueDaoFactory(Providers.asDaggerProvider(dbProvider));
  }

  public static DatabaseModule_ProvideSyncQueueDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideSyncQueueDaoFactory(dbProvider);
  }

  public static SyncQueueDao provideSyncQueueDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideSyncQueueDao(db));
  }
}
