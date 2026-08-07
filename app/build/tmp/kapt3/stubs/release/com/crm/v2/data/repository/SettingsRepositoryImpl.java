package com.crm.v2.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\nJ$\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u00062\u0006\u0010\u0010\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/crm/v2/data/repository/SettingsRepositoryImpl;", "Lcom/crm/v2/domain/repository/SettingsRepository;", "pickupApi", "Lcom/crm/v2/data/network/api/PickupApi;", "(Lcom/crm/v2/data/network/api/PickupApi;)V", "getProfile", "Lcom/crm/v2/common/result/Resource;", "Lcom/crm/v2/domain/model/Employee;", "userKey", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendFeedback", "body", "user", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProfile", "employee", "(Lcom/crm/v2/domain/model/Employee;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class SettingsRepositoryImpl implements com.crm.v2.domain.repository.SettingsRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.network.api.PickupApi pickupApi = null;
    
    @javax.inject.Inject()
    public SettingsRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.api.PickupApi pickupApi) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getProfile(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.Employee>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateProfile(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.Employee employee, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object sendFeedback(@org.jetbrains.annotations.NotNull()
    java.lang.String body, @org.jetbrains.annotations.NotNull()
    java.lang.String user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion) {
        return null;
    }
}