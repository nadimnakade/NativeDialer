package com.crm.v2.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00a2\u0006\u0002\u0010\bJf\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\u00032\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00a2\u0006\u0002\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"Lcom/crm/v2/domain/repository/DailyLoginRepository;", "", "getDailyLoginList", "Lcom/crm/v2/common/result/Resource;", "", "Lcom/crm/v2/domain/model/DailyLogin;", "userKey", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertDailyLogin", "clientName", "mobileNo", "banks", "product", "region", "losNo", "appNo", "loginAmt", "", "remarks", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public abstract interface DailyLoginRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertDailyLogin(@org.jetbrains.annotations.NotNull()
    java.lang.String clientName, @org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, @org.jetbrains.annotations.NotNull()
    java.lang.String banks, @org.jetbrains.annotations.NotNull()
    java.lang.String product, @org.jetbrains.annotations.NotNull()
    java.lang.String region, @org.jetbrains.annotations.NotNull()
    java.lang.String losNo, @org.jetbrains.annotations.NotNull()
    java.lang.String appNo, double loginAmt, @org.jetbrains.annotations.Nullable()
    java.lang.String remarks, @org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDailyLoginList(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.DailyLogin>>> $completion);
}