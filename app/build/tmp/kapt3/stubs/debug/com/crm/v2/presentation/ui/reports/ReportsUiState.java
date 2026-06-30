package com.crm.v2.presentation.ui.reports;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\b\u0087\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\fH\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000eH\u00c6\u0003JM\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00c6\u0001J\u0013\u0010\"\u001a\u00020\f2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020\u0003H\u00d6\u0001J\t\u0010%\u001a\u00020\u000eH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0018R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006&"}, d2 = {"Lcom/crm/v2/presentation/ui/reports/ReportsUiState;", "", "activeTab", "", "performance", "", "Lcom/crm/v2/domain/model/PerformanceData;", "attendance", "Lcom/crm/v2/domain/model/AttendanceData;", "dialerLive", "Lcom/crm/v2/domain/model/LiveCount;", "isLoading", "", "error", "", "(ILjava/util/List;Lcom/crm/v2/domain/model/AttendanceData;Lcom/crm/v2/domain/model/LiveCount;ZLjava/lang/String;)V", "getActiveTab", "()I", "getAttendance", "()Lcom/crm/v2/domain/model/AttendanceData;", "getDialerLive", "()Lcom/crm/v2/domain/model/LiveCount;", "getError", "()Ljava/lang/String;", "()Z", "getPerformance", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class ReportsUiState {
    private final int activeTab = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.crm.v2.domain.model.PerformanceData> performance = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.model.AttendanceData attendance = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.model.LiveCount dialerLive = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public ReportsUiState(int activeTab, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.PerformanceData> performance, @org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.AttendanceData attendance, @org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.LiveCount dialerLive, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    public final int getActiveTab() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.PerformanceData> getPerformance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.domain.model.AttendanceData getAttendance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.domain.model.LiveCount getDialerLive() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public ReportsUiState() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.PerformanceData> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.domain.model.AttendanceData component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.domain.model.LiveCount component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.presentation.ui.reports.ReportsUiState copy(int activeTab, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.PerformanceData> performance, @org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.AttendanceData attendance, @org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.LiveCount dialerLive, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
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