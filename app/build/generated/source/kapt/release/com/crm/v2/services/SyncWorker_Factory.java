package com.crm.v2.services;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.crm.v2.data.database.dao.CallLogDao;
import com.crm.v2.data.database.dao.SyncQueueDao;
import com.crm.v2.data.network.api.PickupApi;
import dagger.internal.DaggerGenerated;
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
public final class SyncWorker_Factory {
  private final Provider<CallLogDao> callLogDaoProvider;

  private final Provider<SyncQueueDao> syncQueueDaoProvider;

  private final Provider<PickupApi> pickupApiProvider;

  public SyncWorker_Factory(Provider<CallLogDao> callLogDaoProvider,
      Provider<SyncQueueDao> syncQueueDaoProvider, Provider<PickupApi> pickupApiProvider) {
    this.callLogDaoProvider = callLogDaoProvider;
    this.syncQueueDaoProvider = syncQueueDaoProvider;
    this.pickupApiProvider = pickupApiProvider;
  }

  public SyncWorker get(Context appContext, WorkerParameters workerParams) {
    return newInstance(appContext, workerParams, callLogDaoProvider.get(), syncQueueDaoProvider.get(), pickupApiProvider.get());
  }

  public static SyncWorker_Factory create(javax.inject.Provider<CallLogDao> callLogDaoProvider,
      javax.inject.Provider<SyncQueueDao> syncQueueDaoProvider,
      javax.inject.Provider<PickupApi> pickupApiProvider) {
    return new SyncWorker_Factory(Providers.asDaggerProvider(callLogDaoProvider), Providers.asDaggerProvider(syncQueueDaoProvider), Providers.asDaggerProvider(pickupApiProvider));
  }

  public static SyncWorker_Factory create(Provider<CallLogDao> callLogDaoProvider,
      Provider<SyncQueueDao> syncQueueDaoProvider, Provider<PickupApi> pickupApiProvider) {
    return new SyncWorker_Factory(callLogDaoProvider, syncQueueDaoProvider, pickupApiProvider);
  }

  public static SyncWorker newInstance(Context appContext, WorkerParameters workerParams,
      CallLogDao callLogDao, SyncQueueDao syncQueueDao, PickupApi pickupApi) {
    return new SyncWorker(appContext, workerParams, callLogDao, syncQueueDao, pickupApi);
  }
}
