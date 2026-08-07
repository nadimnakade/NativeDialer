package com.crm.v2.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\nJ\"\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00062\u0006\u0010\t\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u000eJ,\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u00062\u0006\u0010\t\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007H\u0096@\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/crm/v2/data/repository/PickupRepositoryImpl;", "Lcom/crm/v2/domain/repository/PickupRepository;", "pickupApi", "Lcom/crm/v2/data/network/api/PickupApi;", "(Lcom/crm/v2/data/network/api/PickupApi;)V", "createPickup", "Lcom/crm/v2/common/result/Resource;", "", "leadId", "userKey", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPickupData", "", "Lcom/crm/v2/domain/model/PickupEntry;", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPickupEntryData", "type", "app_release"})
public final class PickupRepositoryImpl implements com.crm.v2.domain.repository.PickupRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.network.api.PickupApi pickupApi = null;
    
    @javax.inject.Inject()
    public PickupRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.api.PickupApi pickupApi) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPickupData(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.PickupEntry>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPickupEntryData(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.PickupEntry>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createPickup(@org.jetbrains.annotations.NotNull()
    java.lang.String leadId, @org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion) {
        return null;
    }
}