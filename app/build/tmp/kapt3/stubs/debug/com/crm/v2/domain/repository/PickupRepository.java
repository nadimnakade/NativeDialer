package com.crm.v2.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\"\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00032\u0006\u0010\u0006\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u000bJ,\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00032\u0006\u0010\u0006\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u0004H\u00a6@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\u000e"}, d2 = {"Lcom/crm/v2/domain/repository/PickupRepository;", "", "createPickup", "Lcom/crm/v2/common/result/Resource;", "", "leadId", "userKey", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPickupData", "", "Lcom/crm/v2/domain/model/PickupEntry;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPickupEntryData", "type", "app_debug"})
public abstract interface PickupRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPickupData(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.PickupEntry>>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPickupEntryData(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.PickupEntry>>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createPickup(@org.jetbrains.annotations.NotNull()
    java.lang.String leadId, @org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion);
}