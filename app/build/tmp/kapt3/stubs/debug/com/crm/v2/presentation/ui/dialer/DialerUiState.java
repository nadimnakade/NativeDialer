package com.crm.v2.presentation.ui.dialer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b&\b\u0087\b\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\r\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0013J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\rH\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\'\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010)\u001a\u00020\u000bH\u00c6\u0003J\t\u0010*\u001a\u00020\rH\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u0085\u0001\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0011\u001a\u00020\r2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005H\u00c6\u0001J\u0013\u0010/\u001a\u00020\r2\b\u00100\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00101\u001a\u00020\u0003H\u00d6\u0001J\t\u00102\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010 R\u0011\u0010\u0011\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010 R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001e\u00a8\u00063"}, d2 = {"Lcom/crm/v2/presentation/ui/dialer/DialerUiState;", "", "activeTab", "", "phoneNumber", "", "dialerQueue", "", "Lcom/crm/v2/domain/model/DialerRecord;", "currentLead", "callState", "Lcom/crm/v2/telephony/CallState;", "isAutoDialing", "", "autoDialInterval", "dndStatus", "error", "isLoading", "lastCallMessage", "(ILjava/lang/String;Ljava/util/List;Lcom/crm/v2/domain/model/DialerRecord;Lcom/crm/v2/telephony/CallState;ZILjava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V", "getActiveTab", "()I", "getAutoDialInterval", "getCallState", "()Lcom/crm/v2/telephony/CallState;", "getCurrentLead", "()Lcom/crm/v2/domain/model/DialerRecord;", "getDialerQueue", "()Ljava/util/List;", "getDndStatus", "()Ljava/lang/String;", "getError", "()Z", "getLastCallMessage", "getPhoneNumber", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class DialerUiState {
    private final int activeTab = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String phoneNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.crm.v2.domain.model.DialerRecord> dialerQueue = null;
    @org.jetbrains.annotations.Nullable()
    private final com.crm.v2.domain.model.DialerRecord currentLead = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.telephony.CallState callState = null;
    private final boolean isAutoDialing = false;
    private final int autoDialInterval = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String dndStatus = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    private final boolean isLoading = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastCallMessage = null;
    
    public DialerUiState(int activeTab, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> dialerQueue, @org.jetbrains.annotations.Nullable()
    com.crm.v2.domain.model.DialerRecord currentLead, @org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.CallState callState, boolean isAutoDialing, int autoDialInterval, @org.jetbrains.annotations.Nullable()
    java.lang.String dndStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String lastCallMessage) {
        super();
    }
    
    public final int getActiveTab() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPhoneNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> getDialerQueue() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.crm.v2.domain.model.DialerRecord getCurrentLead() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.telephony.CallState getCallState() {
        return null;
    }
    
    public final boolean isAutoDialing() {
        return false;
    }
    
    public final int getAutoDialInterval() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDndStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    public final boolean isLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastCallMessage() {
        return null;
    }
    
    public DialerUiState() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final boolean component10() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.crm.v2.domain.model.DialerRecord component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.telephony.CallState component5() {
        return null;
    }
    
    public final boolean component6() {
        return false;
    }
    
    public final int component7() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.presentation.ui.dialer.DialerUiState copy(int activeTab, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> dialerQueue, @org.jetbrains.annotations.Nullable()
    com.crm.v2.domain.model.DialerRecord currentLead, @org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.CallState callState, boolean isAutoDialing, int autoDialInterval, @org.jetbrains.annotations.Nullable()
    java.lang.String dndStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean isLoading, @org.jetbrains.annotations.Nullable()
    java.lang.String lastCallMessage) {
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