package com.crm.v2.data.network.api;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0007JB\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u00062\b\b\u0001\u0010\f\u001a\u00020\u00062\b\b\u0001\u0010\r\u001a\u00020\u00062\b\b\u0003\u0010\u000e\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ8\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00032\b\b\u0001\u0010\u0011\u001a\u00020\u00062\b\b\u0001\u0010\u0012\u001a\u00020\u00062\b\b\u0003\u0010\u000e\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/crm/v2/data/network/api/AuthApi;", "", "getUserStatus", "Lretrofit2/Response;", "Lcom/crm/v2/data/network/dto/auth/UserStatusDto;", "userKey", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "", "Lcom/crm/v2/data/network/dto/auth/LoginResponseDto;", "userName", "password", "uuid", "type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginMobile", "mobileNo", "udid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface AuthApi {
    
    @retrofit2.http.GET(value = "UpdateStatus/ValidateLogin")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@retrofit2.http.Query(value = "UserName")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userName, @retrofit2.http.Query(value = "Password")
    @org.jetbrains.annotations.NotNull()
    java.lang.String password, @retrofit2.http.Query(value = "UUID")
    @org.jetbrains.annotations.NotNull()
    java.lang.String uuid, @retrofit2.http.Query(value = "Type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.crm.v2.data.network.dto.auth.LoginResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "UpdateStatus/ValidateLogin")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object loginMobile(@retrofit2.http.Query(value = "MobileNo")
    @org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, @retrofit2.http.Query(value = "UDID")
    @org.jetbrains.annotations.NotNull()
    java.lang.String udid, @retrofit2.http.Query(value = "Type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<java.util.List<com.crm.v2.data.network.dto.auth.LoginResponseDto>>> $completion);
    
    @retrofit2.http.GET(value = "Pickup/user-status")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserStatus(@retrofit2.http.Query(value = "userKey")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super retrofit2.Response<com.crm.v2.data.network.dto.auth.UserStatusDto>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}