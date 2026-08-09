package com.crm.v2.presentation.ui.dialer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010$\n\u0002\u0010\f\n\u0002\u0018\u0002\n\u0002\bF\b\u0087\b\u0018\u00002\u00020\u0001B\u00d1\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0005\u0012\u001a\b\u0002\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00070\u001a\u0012\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u001f\u001a\u00020\u0003\u0012\b\b\u0002\u0010 \u001a\u00020\u0011\u0012\b\b\u0002\u0010!\u001a\u00020\u0011\u0012\b\b\u0002\u0010\"\u001a\u00020\u0003\u0012\b\b\u0002\u0010#\u001a\u00020\u0011\u0012\b\b\u0002\u0010$\u001a\u00020\u0011\u0012\b\b\u0002\u0010%\u001a\u00020\u0011\u00a2\u0006\u0002\u0010&J\t\u0010B\u001a\u00020\u0003H\u00c6\u0003J\t\u0010C\u001a\u00020\u0011H\u00c6\u0003J\t\u0010D\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010G\u001a\u00020\u0011H\u00c6\u0003J\t\u0010H\u001a\u00020\u0011H\u00c6\u0003J\u000b\u0010I\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010J\u001a\u00020\u0005H\u00c6\u0003J\u001b\u0010K\u001a\u0014\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00070\u001aH\u00c6\u0003J\u000f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007H\u00c6\u0003J\t\u0010M\u001a\u00020\u0005H\u00c6\u0003J\t\u0010N\u001a\u00020\u0011H\u00c6\u0003J\t\u0010O\u001a\u00020\u0003H\u00c6\u0003J\t\u0010P\u001a\u00020\u0011H\u00c6\u0003J\t\u0010Q\u001a\u00020\u0011H\u00c6\u0003J\t\u0010R\u001a\u00020\u0003H\u00c6\u0003J\t\u0010S\u001a\u00020\u0011H\u00c6\u0003J\t\u0010T\u001a\u00020\u0011H\u00c6\u0003J\t\u0010U\u001a\u00020\u0011H\u00c6\u0003J\u000f\u0010V\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010W\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010X\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000b\u0010[\u001a\u0004\u0018\u00010\bH\u00c6\u0003J\t\u0010\\\u001a\u00020\u000fH\u00c6\u0003J\u00d5\u0002\u0010]\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u00112\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00052\u001a\b\u0002\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00070\u001a2\u000e\b\u0002\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00032\b\b\u0002\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020\u00032\b\b\u0002\u0010#\u001a\u00020\u00112\b\b\u0002\u0010$\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u0011H\u00c6\u0001J\u0013\u0010^\u001a\u00020\u00112\b\u0010_\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010`\u001a\u00020\u0003H\u00d6\u0001J\t\u0010a\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\u0012\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010(R\u0011\u0010\"\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010(R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0011\u0010\u0018\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0013\u0010\r\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010*R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u00100R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00100R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b6\u0010*R#\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00070\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0011\u0010 \u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010:R\u0011\u0010\u0015\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010:R\u0011\u0010#\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010:R\u0011\u0010%\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010:R\u0011\u0010\u001e\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010:R\u0011\u0010\u0016\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010:R\u0011\u0010$\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010:R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u00100R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010*R\u0011\u0010\u001f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b=\u0010(R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010*R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u00100R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b@\u0010*R\u0011\u0010!\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\bA\u0010:\u00a8\u0006b"}, d2 = {"Lcom/crm/v2/presentation/ui/dialer/DialerUiState;", "", "activeTab", "", "phoneNumber", "", "dialerQueue", "", "Lcom/crm/v2/domain/model/DialerRecord;", "leadList", "followUpRecords", "ringingRecords", "oneMinRecords", "currentLead", "callState", "Lcom/crm/v2/telephony/CallState;", "isAutoDialing", "", "autoDialInterval", "dndStatus", "error", "isLoading", "isSaving", "lastCallMessage", "contactSearchQuery", "groupedContacts", "", "", "Lcom/crm/v2/domain/model/Contact;", "allContacts", "isRefreshing", "leadPageIndex", "hasMoreLeads", "showUpdateStatus", "callDuration", "isMuted", "isSpeakerOn", "isOnHold", "(ILjava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lcom/crm/v2/domain/model/DialerRecord;Lcom/crm/v2/telephony/CallState;ZILjava/lang/String;Ljava/lang/String;ZZLjava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/util/List;ZIZZIZZZ)V", "getActiveTab", "()I", "getAllContacts", "()Ljava/util/List;", "getAutoDialInterval", "getCallDuration", "getCallState", "()Lcom/crm/v2/telephony/CallState;", "getContactSearchQuery", "()Ljava/lang/String;", "getCurrentLead", "()Lcom/crm/v2/domain/model/DialerRecord;", "getDialerQueue", "getDndStatus", "getError", "getFollowUpRecords", "getGroupedContacts", "()Ljava/util/Map;", "getHasMoreLeads", "()Z", "getLastCallMessage", "getLeadList", "getLeadPageIndex", "getOneMinRecords", "getPhoneNumber", "getRingingRecords", "getShowUpdateStatus", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class DialerUiState {
    private final int activeTab = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String phoneNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.crm.v2.domain.model.DialerRecord> dialerQueue = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.crm.v2.domain.model.DialerRecord> leadList = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.crm.v2.domain.model.DialerRecord> followUpRecords = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.crm.v2.domain.model.DialerRecord> ringingRecords = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.crm.v2.domain.model.DialerRecord> oneMinRecords = null;
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
    private final boolean isSaving = false;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String lastCallMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String contactSearchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.Character, java.util.List<com.crm.v2.domain.model.Contact>> groupedContacts = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.crm.v2.domain.model.Contact> allContacts = null;
    private final boolean isRefreshing = false;
    private final int leadPageIndex = 0;
    private final boolean hasMoreLeads = false;
    private final boolean showUpdateStatus = false;
    private final int callDuration = 0;
    private final boolean isMuted = false;
    private final boolean isSpeakerOn = false;
    private final boolean isOnHold = false;
    
    public DialerUiState(int activeTab, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> dialerQueue, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> leadList, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> followUpRecords, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> ringingRecords, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> oneMinRecords, @org.jetbrains.annotations.Nullable()
    com.crm.v2.domain.model.DialerRecord currentLead, @org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.CallState callState, boolean isAutoDialing, int autoDialInterval, @org.jetbrains.annotations.Nullable()
    java.lang.String dndStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean isLoading, boolean isSaving, @org.jetbrains.annotations.Nullable()
    java.lang.String lastCallMessage, @org.jetbrains.annotations.NotNull()
    java.lang.String contactSearchQuery, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.Character, ? extends java.util.List<com.crm.v2.domain.model.Contact>> groupedContacts, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.Contact> allContacts, boolean isRefreshing, int leadPageIndex, boolean hasMoreLeads, boolean showUpdateStatus, int callDuration, boolean isMuted, boolean isSpeakerOn, boolean isOnHold) {
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> getLeadList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> getFollowUpRecords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> getRingingRecords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> getOneMinRecords() {
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
    
    public final boolean isSaving() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getLastCallMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getContactSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.Character, java.util.List<com.crm.v2.domain.model.Contact>> getGroupedContacts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.Contact> getAllContacts() {
        return null;
    }
    
    public final boolean isRefreshing() {
        return false;
    }
    
    public final int getLeadPageIndex() {
        return 0;
    }
    
    public final boolean getHasMoreLeads() {
        return false;
    }
    
    public final boolean getShowUpdateStatus() {
        return false;
    }
    
    public final int getCallDuration() {
        return 0;
    }
    
    public final boolean isMuted() {
        return false;
    }
    
    public final boolean isSpeakerOn() {
        return false;
    }
    
    public final boolean isOnHold() {
        return false;
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
    
    public final int component11() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component13() {
        return null;
    }
    
    public final boolean component14() {
        return false;
    }
    
    public final boolean component15() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component16() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.Character, java.util.List<com.crm.v2.domain.model.Contact>> component18() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.Contact> component19() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    public final boolean component20() {
        return false;
    }
    
    public final int component21() {
        return 0;
    }
    
    public final boolean component22() {
        return false;
    }
    
    public final boolean component23() {
        return false;
    }
    
    public final int component24() {
        return 0;
    }
    
    public final boolean component25() {
        return false;
    }
    
    public final boolean component26() {
        return false;
    }
    
    public final boolean component27() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.crm.v2.domain.model.DialerRecord> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.crm.v2.domain.model.DialerRecord component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.telephony.CallState component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.presentation.ui.dialer.DialerUiState copy(int activeTab, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> dialerQueue, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> leadList, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> followUpRecords, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> ringingRecords, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.DialerRecord> oneMinRecords, @org.jetbrains.annotations.Nullable()
    com.crm.v2.domain.model.DialerRecord currentLead, @org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.CallState callState, boolean isAutoDialing, int autoDialInterval, @org.jetbrains.annotations.Nullable()
    java.lang.String dndStatus, @org.jetbrains.annotations.Nullable()
    java.lang.String error, boolean isLoading, boolean isSaving, @org.jetbrains.annotations.Nullable()
    java.lang.String lastCallMessage, @org.jetbrains.annotations.NotNull()
    java.lang.String contactSearchQuery, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.Character, ? extends java.util.List<com.crm.v2.domain.model.Contact>> groupedContacts, @org.jetbrains.annotations.NotNull()
    java.util.List<com.crm.v2.domain.model.Contact> allContacts, boolean isRefreshing, int leadPageIndex, boolean hasMoreLeads, boolean showUpdateStatus, int callDuration, boolean isMuted, boolean isSpeakerOn, boolean isOnHold) {
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