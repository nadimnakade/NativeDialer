package com.crm.v2.domain.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0083\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u00a2\u0006\u0002\u0010\u0013J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\tH\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u00c6\u0003J\t\u0010(\u001a\u00020\u0005H\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\tH\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0005H\u00c6\u0003J\u0087\u0001\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u00c6\u0001J\u0013\u00101\u001a\u0002022\b\u00103\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00104\u001a\u00020\u0003H\u00d6\u0001J\t\u00105\u001a\u00020\tH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017\u00a8\u00066"}, d2 = {"Lcom/crm/v2/domain/model/DashboardData;", "", "rank", "", "points", "", "totalDisbursedAmt", "clientConnectedFtd", "duration", "", "dailyLeads", "monthlyLeads", "loginCount", "loginAmount", "downTime", "connectivityPercent", "disbursalBreakdown", "", "Lcom/crm/v2/domain/model/DisbursalBreakdown;", "(IDDILjava/lang/String;IIIDLjava/lang/String;DLjava/util/List;)V", "getClientConnectedFtd", "()I", "getConnectivityPercent", "()D", "getDailyLeads", "getDisbursalBreakdown", "()Ljava/util/List;", "getDownTime", "()Ljava/lang/String;", "getDuration", "getLoginAmount", "getLoginCount", "getMonthlyLeads", "getPoints", "getRank", "getTotalDisbursedAmt", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class DashboardData {
    private final int rank = 0;
    private final double points = 0.0;
    private final double totalDisbursedAmt = 0.0;
    private final int clientConnectedFtd = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String duration = null;
    private final int dailyLeads = 0;
    private final int monthlyLeads = 0;
    private final int loginCount = 0;
    private final double loginAmount = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String downTime = null;
    private final double connectivityPercent = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.crm.v2.domain.model.DisbursalBreakdown> disbursalBreakdown = null;
    
    public DashboardData(int rank, double points, double totalDisbursedAmt, int clientConnectedFtd, @org.jetbrains.annotations.NotNull()
    java.lang.String duration, int dailyLeads, int monthlyLeads, int loginCount, double loginAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String downTime, double connectivityPercent, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DisbursalBreakdown> disbursalBreakdown) {
        super();
    }
    
    public final int getRank() {
        return 0;
    }
    
    public final double getPoints() {
        return 0.0;
    }
    
    public final double getTotalDisbursedAmt() {
        return 0.0;
    }
    
    public final int getClientConnectedFtd() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDuration() {
        return null;
    }
    
    public final int getDailyLeads() {
        return 0;
    }
    
    public final int getMonthlyLeads() {
        return 0;
    }
    
    public final int getLoginCount() {
        return 0;
    }
    
    public final double getLoginAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDownTime() {
        return null;
    }
    
    public final double getConnectivityPercent() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DisbursalBreakdown> getDisbursalBreakdown() {
        return null;
    }
    
    public DashboardData() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    public final double component11() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DisbursalBreakdown> component12() {
        return null;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.domain.model.DashboardData copy(int rank, double points, double totalDisbursedAmt, int clientConnectedFtd, @org.jetbrains.annotations.NotNull()
    java.lang.String duration, int dailyLeads, int monthlyLeads, int loginCount, double loginAmount, @org.jetbrains.annotations.NotNull()
    java.lang.String downTime, double connectivityPercent, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DisbursalBreakdown> disbursalBreakdown) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}