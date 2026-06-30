package com.crm.v2.data.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J&\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u0003H\'J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0012J\"\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\u0015J\u001c\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u00a7@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0018"}, d2 = {"Lcom/crm/v2/data/database/dao/CallLogDao;", "", "deleteOldSyncedLogs", "", "before", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCallLogs", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/crm/v2/data/database/entity/CallLogEntity;", "userKey", "", "limit", "getUnsyncedCallLogs", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCallLog", "log", "(Lcom/crm/v2/data/database/entity/CallLogEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertCallLogs", "logs", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsSynced", "ids", "app_debug"})
@androidx.room.Dao()
public abstract interface CallLogDao {
    
    @androidx.room.Query(value = "SELECT * FROM call_logs WHERE userKey = :userKey ORDER BY callDate DESC LIMIT :limit")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.crm.v2.data.database.entity.CallLogEntity>> getCallLogs(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, int limit);
    
    @androidx.room.Query(value = "SELECT * FROM call_logs WHERE synced = 0")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUnsyncedCallLogs(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.crm.v2.data.database.entity.CallLogEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertCallLogs(@org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.data.database.entity.CallLogEntity> logs, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertCallLog(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.entity.CallLogEntity log, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "UPDATE call_logs SET synced = 1 WHERE id IN (:ids)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markAsSynced(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> ids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM call_logs WHERE synced = 1 AND cachedAt < :before")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOldSyncedLogs(long before, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}