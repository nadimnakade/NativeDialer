package com.crm.v2.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006JH\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\b\u0010\r\u001a\u0004\u0018\u00010\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u0010J,\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\b2\u0006\u0010\u000f\u001a\u00020\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\tH\u0096@\u00a2\u0006\u0002\u0010\u0015J&\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\b2\u0006\u0010\u000f\u001a\u00020\t2\b\u0010\u0014\u001a\u0004\u0018\u00010\tH\u0096@\u00a2\u0006\u0002\u0010\u0015J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\b2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u0015J*\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00120\b2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001dH\u0096@\u00a2\u0006\u0002\u0010\u001eJ(\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0!H\u0096@\u00a2\u0006\u0002\u0010\"R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/crm/v2/data/repository/LeadRepositoryImpl;", "Lcom/crm/v2/domain/repository/LeadRepository;", "pickupApi", "Lcom/crm/v2/data/network/api/PickupApi;", "leadDao", "Lcom/crm/v2/data/database/dao/LeadDao;", "(Lcom/crm/v2/data/network/api/PickupApi;Lcom/crm/v2/data/database/dao/LeadDao;)V", "createLead", "Lcom/crm/v2/common/result/Resource;", "", "name", "mobileNo", "product", "callerId", "remarks", "userKey", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFollowUpLeads", "", "Lcom/crm/v2/domain/model/DialerRecord;", "type", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLeadCount", "Lcom/crm/v2/domain/model/LeadCount;", "getLeadDetail", "Lcom/crm/v2/domain/model/Lead;", "key", "getLeads", "pageIndex", "", "(Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLeadStatus", "request", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"})
public final class LeadRepositoryImpl implements com.crm.v2.domain.repository.LeadRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.network.api.PickupApi pickupApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.database.dao.LeadDao leadDao = null;
    
    @javax.inject.Inject()
    public LeadRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.api.PickupApi pickupApi, @org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.dao.LeadDao leadDao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getLeads(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, int pageIndex, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.Lead>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getLeadDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.Lead>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object createLead(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String mobileNo, @org.jetbrains.annotations.NotNull()
    java.lang.String product, @org.jetbrains.annotations.Nullable()
    java.lang.String callerId, @org.jetbrains.annotations.Nullable()
    java.lang.String remarks, @org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getLeadCount(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.LeadCount>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getFollowUpLeads(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.DialerRecord>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateLeadStatus(@org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<java.lang.String>> $completion) {
        return null;
    }
}