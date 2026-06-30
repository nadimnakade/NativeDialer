package com.crm.v2.data.repository;

import android.content.Context;
import com.crm.v2.data.database.dao.ContactDao;
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
public final class ContactRepositoryImpl_Factory implements Factory<ContactRepositoryImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<ContactDao> contactDaoProvider;

  public ContactRepositoryImpl_Factory(Provider<Context> contextProvider,
      Provider<ContactDao> contactDaoProvider) {
    this.contextProvider = contextProvider;
    this.contactDaoProvider = contactDaoProvider;
  }

  @Override
  public ContactRepositoryImpl get() {
    return newInstance(contextProvider.get(), contactDaoProvider.get());
  }

  public static ContactRepositoryImpl_Factory create(javax.inject.Provider<Context> contextProvider,
      javax.inject.Provider<ContactDao> contactDaoProvider) {
    return new ContactRepositoryImpl_Factory(Providers.asDaggerProvider(contextProvider), Providers.asDaggerProvider(contactDaoProvider));
  }

  public static ContactRepositoryImpl_Factory create(Provider<Context> contextProvider,
      Provider<ContactDao> contactDaoProvider) {
    return new ContactRepositoryImpl_Factory(contextProvider, contactDaoProvider);
  }

  public static ContactRepositoryImpl newInstance(Context context, ContactDao contactDao) {
    return new ContactRepositoryImpl(context, contactDao);
  }
}
