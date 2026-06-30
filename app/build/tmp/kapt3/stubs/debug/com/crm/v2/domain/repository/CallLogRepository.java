package com.crm.v2.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003H&J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\nJ\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u00a6@\u00a2\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007H\u00a6@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/crm/v2/domain/repository/CallLogRepository;", "", "getOfflineCallLogs", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/crm/v2/domain/model/CallLog;", "saveCallLog", "Lcom/crm/v2/common/result/Resource;", "", "log", "(Lcom/crm/v2/domain/model/CallLog;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveCallLogBatch", "logs", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncPendingCallLogs", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface CallLogRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveCallLogBatch(@org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.CallLog> logs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveCallLog(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.CallLog log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.crm.v2.domain.model.CallLog>> getOfflineCallLogs();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object syncPendingCallLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.Integer>> $completion);
}