package com.crm.v2.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J.\u0010\u0005\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b0\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\fJ$\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00062\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\u0017J\"\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\fJ\"\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\fJ\"\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0096@\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/crm/v2/data/repository/ReportingRepositoryImpl;", "Lcom/crm/v2/domain/repository/ReportingRepository;", "pickupApi", "Lcom/crm/v2/data/network/api/PickupApi;", "(Lcom/crm/v2/data/network/api/PickupApi;)V", "getApplications", "Lcom/crm/v2/common/result/Resource;", "", "", "", "", "userKey", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAttendance", "Lcom/crm/v2/domain/model/AttendanceData;", "getDashboardData", "Lcom/crm/v2/domain/model/DashboardData;", "getDialerLive", "Lcom/crm/v2/domain/model/LiveCount;", "getDialerLiveIndv", "getDisbursalDetail", "Lcom/crm/v2/domain/model/DisbursalDetail;", "loanId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIncentive", "Lcom/crm/v2/domain/model/PerformanceData;", "getPerformance", "getRankings", "Lcom/crm/v2/domain/model/RankingData;", "getTeamCallLog", "Lcom/crm/v2/domain/model/CallLog;", "app_release"})
public final class ReportingRepositoryImpl implements com.crm.v2.domain.repository.ReportingRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.network.api.PickupApi pickupApi = null;
    
    @javax.inject.Inject()
    public ReportingRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.network.api.PickupApi pickupApi) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDashboardData(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.DashboardData>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDialerLive(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.LiveCount>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDialerLiveIndv(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.LiveCount>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getTeamCallLog(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.CallLog>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getAttendance(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.AttendanceData>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getPerformance(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.PerformanceData>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getRankings(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.RankingData>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getIncentive(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.PerformanceData>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getApplications(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<? extends java.util.Map<java.lang.String, ? extends java.lang.Object>>>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getDisbursalDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    java.lang.String loanId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.DisbursalDetail>> $completion) {
        return null;
    }
}