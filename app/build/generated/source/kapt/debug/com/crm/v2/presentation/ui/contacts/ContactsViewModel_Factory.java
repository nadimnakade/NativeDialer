package com.crm.v2.presentation.ui.contacts;

import com.crm.v2.domain.repository.ContactRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class ContactsViewModel_Factory implements Factory<ContactsViewModel> {
  private final Provider<ContactRepository> contactRepositoryProvider;

  public ContactsViewModel_Factory(Provider<ContactRepository> contactRepositoryProvider) {
    this.contactRepositoryProvider = contactRepositoryProvider;
  }

  @Override
  public ContactsViewModel get() {
    return newInstance(contactRepositoryProvider.get());
  }

  public static ContactsViewModel_Factory create(
      javax.inject.Provider<ContactRepository> contactRepositoryProvider) {
    return new ContactsViewModel_Factory(Providers.asDaggerProvider(contactRepositoryProvider));
  }

  public static ContactsViewModel_Factory create(
      Provider<ContactRepository> contactRepositoryProvider) {
    return new ContactsViewModel_Factory(contactRepositoryProvider);
  }

  public static ContactsViewModel newInstance(ContactRepository contactRepository) {
    return new ContactsViewModel(contactRepository);
  }
}
