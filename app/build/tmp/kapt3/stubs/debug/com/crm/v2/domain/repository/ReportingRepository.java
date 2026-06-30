package com.crm.v2.domain.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J.\u0010\u0002\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00050\u00040\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\bJ$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0013J\"\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\bJ\"\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\bJ\"\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00040\u00032\u0006\u0010\u0007\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\b\u00a8\u0006\u001b"}, d2 = {"Lcom/crm/v2/domain/repository/ReportingRepository;", "", "getApplications", "Lcom/crm/v2/common/result/Resource;", "", "", "", "userKey", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAttendance", "Lcom/crm/v2/domain/model/AttendanceData;", "getDashboardData", "Lcom/crm/v2/domain/model/DashboardData;", "getDialerLive", "Lcom/crm/v2/domain/model/LiveCount;", "getDialerLiveIndv", "getDisbursalDetail", "Lcom/crm/v2/domain/model/DisbursalDetail;", "loanId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIncentive", "Lcom/crm/v2/domain/model/PerformanceData;", "getPerformance", "getRankings", "Lcom/crm/v2/domain/model/RankingData;", "getTeamCallLog", "Lcom/crm/v2/domain/model/CallLog;", "app_debug"})
public abstract interface ReportingRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDashboardData(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.DashboardData>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDialerLive(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.LiveCount>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDialerLiveIndv(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.LiveCount>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTeamCallLog(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.CallLog>>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAttendance(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.AttendanceData>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getPerformance(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.PerformanceData>>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRankings(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.RankingData>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getIncentive(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<com.crm.v2.domain.model.PerformanceData>>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getApplications(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<? extends java.util.List<? extends java.util.Map<java.lang.String, ? extends java.lang.Object>>>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getDisbursalDetail(@org.jetbrains.annotations.NotNull()
    java.lang.String userKey, @org.jetbrains.annotations.NotNull()
    java.lang.String loanId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.crm.v2.common.result.Resource<com.crm.v2.domain.model.DisbursalDetail>> $completion);
}