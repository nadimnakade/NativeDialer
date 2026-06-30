package com.crm.v2.di;

import com.crm.v2.data.database.AppDatabase;
import com.crm.v2.data.database.dao.ContactDao;
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
public final class DatabaseModule_ProvideContactDaoFactory implements Factory<ContactDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideContactDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ContactDao get() {
    return provideContactDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideContactDaoFactory create(
      javax.inject.Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideContactDaoFactory(Providers.asDaggerProvider(dbProvider));
  }

  public static DatabaseModule_ProvideContactDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideContactDaoFactory(dbProvider);
  }

  public static ContactDao provideContactDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideContactDao(db));
  }
}
