package com.crm.v2.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\t0\u0011H\u0016J\u000e\u0010\u0012\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u000fJ,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\u0017J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\"\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u000bH\u0096@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/crm/v2/data/repository/AuthRepositoryImpl;", "Lcom/crm/v2/domain/repository/AuthRepository;", "authApi", "Lcom/crm/v2/data/network/api/AuthApi;", "authDataStore", "Lcom/crm/v2/data/datastore/AuthDataStore;", "(Lcom/crm/v2/data/network/api/AuthApi;Lcom/crm/v2/data/datastore/AuthDataStore;)V", "checkUserStatus", "Lcom/crm/v2/common/result/Resource;", "", "userKey", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSavedUser", "Lcom/crm/v2/domain/model/User;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isLoggedIn", "Lkotlinx/coroutines/flow/Flow;", "isPinSet", "login", "userName", "password", "uuid", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loginMobile", "mobileNo", "udid", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "", "saveFcmToken", "token", "savePin", "pin", "verifyPin", "Companion", "app_debug"})
public final class AuthRepositoryImpl implements com.crm.v2.domain.repository.AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.network.api.AuthApi authApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.datastore.AuthDataStore authDataStore = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.data.repository.AuthRepositoryImpl.Companion Companion = null;
    
    @javax.inject.Inject()
    public AuthRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.api.AuthApi authApi, @org.jetbrains.annotations.NotNull()
    com.crm.v2.data.datastore.AuthDataStore authDataStore) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object login(@org.jetbrains.annotations.NotNull()
    java.lang.String userName, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String uuid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.User>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object loginMobile(@org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, @org.jetbrains.annotations.NotNull()
    java.lang.String udid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.User>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object checkUserStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.Boolean>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getSavedUser(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.domain.model.User> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> isLoggedIn() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object savePin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object verifyPin(@org.jetbrains.annotations.NotNull()
    java.lang.String pin, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object isPinSet(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveFcmToken(@org.jetbrains.annotations.NotNull()
    java.lang.String token, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0002\u00a8\u0006\t"}, d2 = {"Lcom/crm/v2/data/repository/AuthRepositoryImpl$Companion;", "", "()V", "parseExpiry", "", "expiration", "", "parseServerError", "errorBody", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        private final java.lang.String parseServerError(java.lang.String errorBody) {
            return null;
        }
        
        private final long parseExpiry(java.lang.String expiration) {
            return 0L;
        }
    }
}