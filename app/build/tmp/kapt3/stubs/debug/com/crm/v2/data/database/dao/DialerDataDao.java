package com.crm.v2.data.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\b\u001a\u00020\u0005H\'J\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011"}, d2 = {"Lcom/crm/v2/data/database/dao/DialerDataDao;", "", "deleteByKey", "", "key", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteForUser", "userKey", "getDialerData", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/crm/v2/data/database/entity/DialerDataEntity;", "insertDialerData", "", "data", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface DialerDataDao {
    
    @androidx.room.Query(value = "SELECT * FROM dialer_data WHERE userKey = :userKey ORDER BY cachedAt DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.crm.v2.data.database.entity.DialerDataEntity>> getDialerData(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertDialerData(@org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.data.database.entity.DialerDataEntity> data, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Query(value = "DELETE FROM dialer_data WHERE userKey = :userKey")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteForUser(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM dialer_data WHERE key = :key")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteByKey(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}