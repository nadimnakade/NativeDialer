package com.crm.v2.di;

import com.crm.v2.data.database.AppDatabase;
import com.crm.v2.data.database.dao.LeadDao;
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
public final class DatabaseModule_ProvideLeadDaoFactory implements Factory<LeadDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideLeadDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public LeadDao get() {
    return provideLeadDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideLeadDaoFactory create(
      javax.inject.Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideLeadDaoFactory(Providers.asDaggerProvider(dbProvider));
  }

  public static DatabaseModule_ProvideLeadDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideLeadDaoFactory(dbProvider);
  }

  public static LeadDao provideLeadDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideLeadDao(db));
  }
}
