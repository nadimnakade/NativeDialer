package com.crm.v2.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J*\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\fJ*\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\fJ\"\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00070\u00062\u0006\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0010JD\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/crm/v2/data/repository/DocumentRepositoryImpl;", "Lcom/crm/v2/domain/repository/DocumentRepository;", "pickupApi", "Lcom/crm/v2/data/network/api/PickupApi;", "(Lcom/crm/v2/data/network/api/PickupApi;)V", "getCallerClients", "Lcom/crm/v2/common/result/Resource;", "", "Lcom/crm/v2/domain/model/ClientInfo;", "userKey", "", "mobileNo", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getClientDocuments", "Lcom/crm/v2/domain/model/Document;", "getPayslips", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDocument", "fileBytes", "", "fileName", "clientName", "caller", "fileType", "([BLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class DocumentRepositoryImpl implements com.crm.v2.domain.repository.DocumentRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.network.api.PickupApi pickupApi = null;
    
    @javax.inject.Inject()
    public DocumentRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.api.PickupApi pickupApi) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object saveDocument(@org.jetbrains.annotations.NotNull()
    byte[] fileBytes, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName, @org.jetbrains.annotations.NotNull()
    java.lang.String clientName, @org.jetbrains.annotations.NotNull()
    java.lang.String caller, @org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, @org.jetbrains.annotations.NotNull()
    java.lang.String fileType, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getCallerClients(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.ClientInfo>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getClientDocuments(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.Document>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPayslips(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.Document>>> $completion) {
        return null;
    }
}