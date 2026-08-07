package com.crm.v2.presentation.ui.callhistory;

import com.crm.v2.data.database.dao.CallLogDao;
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
public final class CallHistoryViewModel_Factory implements Factory<CallHistoryViewModel> {
  private final Provider<CallLogDao> callLogDaoProvider;

  public CallHistoryViewModel_Factory(Provider<CallLogDao> callLogDaoProvider) {
    this.callLogDaoProvider = callLogDaoProvider;
  }

  @Override
  public CallHistoryViewModel get() {
    return newInstance(callLogDaoProvider.get());
  }

  public static CallHistoryViewModel_Factory create(
      javax.inject.Provider<CallLogDao> callLogDaoProvider) {
    return new CallHistoryViewModel_Factory(Providers.asDaggerProvider(callLogDaoProvider));
  }

  public static CallHistoryViewModel_Factory create(Provider<CallLogDao> callLogDaoProvider) {
    return new CallHistoryViewModel_Factory(callLogDaoProvider);
  }

  public static CallHistoryViewModel newInstance(CallLogDao callLogDao) {
    return new CallHistoryViewModel(callLogDao);
  }
}
