package com.crm.v2.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u0004\u0018\u00010\tH\u00a6@\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fH&J\u000e\u0010\r\u001a\u00020\u0004H\u00a6@\u00a2\u0006\u0002\u0010\nJ,\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0012J$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0018H\u00a6@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\u001e"}, d2 = {"Lcom/crm/v2/domain/repository/AuthRepository;", "", "checkUserStatus", "Lcom/crm/v2/common/result/Resource;", "", "userKey", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSavedUser", "Lcom/crm/v2/domain/model/User;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isLoggedIn", "Lkotlinx/coroutines/flow/Flow;", "isPinSet", "login", "userName", "password", "uuid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginMobile", "mobileNo", "udid", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "", "saveFcmToken", "token", "savePin", "pin", "verifyPin", "app_debug"})
public abstract interface AuthRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object login(@org.jetbrains.annotations.NotNull()
    java.lang.String userName, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String uuid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.User>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object loginMobile(@org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, @org.jetbrains.annotations.NotNull()
    java.lang.String udid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.User>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkUserStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.Boolean>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getSavedUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.domain.model.User> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLoggedIn();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object savePin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object verifyPin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isPinSet(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object saveFcmToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}