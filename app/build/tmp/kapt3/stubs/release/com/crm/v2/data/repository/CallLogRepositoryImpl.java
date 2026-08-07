package com.crm.v2.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\u0016J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0096@\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\fH\u0096@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/crm/v2/data/repository/CallLogRepositoryImpl;", "Lcom/crm/v2/domain/repository/CallLogRepository;", "pickupApi", "Lcom/crm/v2/data/network/api/PickupApi;", "callLogDao", "Lcom/crm/v2/data/database/dao/CallLogDao;", "(Lcom/crm/v2/data/network/api/PickupApi;Lcom/crm/v2/data/database/dao/CallLogDao;)V", "getOfflineCallLogs", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/crm/v2/domain/model/CallLog;", "saveCallLog", "Lcom/crm/v2/common/result/Resource;", "", "log", "(Lcom/crm/v2/domain/model/CallLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveCallLogBatch", "logs", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncPendingCallLogs", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class CallLogRepositoryImpl implements com.crm.v2.domain.repository.CallLogRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.network.api.PickupApi pickupApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.database.dao.CallLogDao callLogDao = null;
    
    @javax.inject.Inject()
    public CallLogRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.api.PickupApi pickupApi, @org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.dao.CallLogDao callLogDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveCallLogBatch(@org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.CallLog> logs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveCallLog(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.CallLog log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.crm.v2.domain.model.CallLog>> getOfflineCallLogs() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object syncPendingCallLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.Integer>> $completion) {
        return null;
    }
}