package com.crm.v2.presentation.ui.dialer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u001a\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020\u001cJ\u0006\u0010\'\u001a\u00020\u001cJ\u0006\u0010(\u001a\u00020\u001cJ\u0006\u0010)\u001a\u00020%J\u0006\u0010*\u001a\u00020\u001cJ\b\u0010+\u001a\u00020\u001cH\u0002J\u0006\u0010,\u001a\u00020\u001cJ\u0010\u0010-\u001a\u00020\u001c2\b\b\u0002\u0010.\u001a\u00020/J\u000e\u00100\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%J\b\u00101\u001a\u00020\u001cH\u0014J\u000e\u00102\u001a\u00020\u001c2\u0006\u00103\u001a\u00020%J\u000e\u00104\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020%J\u0006\u00105\u001a\u00020\u001cJ\u0006\u00106\u001a\u00020\u001cJ\u000e\u00107\u001a\u00020\u001c2\u0006\u00108\u001a\u00020\u0016J\u000e\u00109\u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"J\u0006\u0010:\u001a\u00020\u001cJ\u0006\u0010;\u001a\u00020\u001cJZ\u0010<\u001a\u00020\u001c2\u0006\u0010=\u001a\u00020%2\u0006\u0010>\u001a\u00020%2\b\u0010?\u001a\u0004\u0018\u00010%2\b\u0010@\u001a\u0004\u0018\u00010%2\u0006\u0010A\u001a\u00020%2\b\b\u0002\u0010B\u001a\u00020\u00162\b\b\u0002\u0010C\u001a\u00020%2\b\b\u0002\u0010D\u001a\u00020/2\b\b\u0002\u0010E\u001a\u00020/J\u0006\u0010F\u001a\u00020\u001cJ\u0006\u0010G\u001a\u00020\u001cJ\u0006\u0010H\u001a\u00020\u001cR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006I"}, d2 = {"Lcom/crm/v2/presentation/ui/dialer/DialerViewModel;", "Landroidx/lifecycle/ViewModel;", "dialerRepository", "Lcom/crm/v2/domain/repository/DialerRepository;", "leadRepository", "Lcom/crm/v2/domain/repository/LeadRepository;", "contactRepository", "Lcom/crm/v2/domain/repository/ContactRepository;", "telephonyManager", "Lcom/crm/v2/telephony/TelephonyManager;", "authDataStore", "Lcom/crm/v2/data/datastore/AuthDataStore;", "(Lcom/crm/v2/domain/repository/DialerRepository;Lcom/crm/v2/domain/repository/LeadRepository;Lcom/crm/v2/domain/repository/ContactRepository;Lcom/crm/v2/telephony/TelephonyManager;Lcom/crm/v2/data/datastore/AuthDataStore;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/presentation/ui/dialer/DialerUiState;", "autoDialJob", "Lkotlinx/coroutines/Job;", "callStartTime", "", "contactJob", "lastCalledIndex", "", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "autoDialNext", "", "callContact", "contact", "Lcom/crm/v2/domain/model/Contact;", "callLead", "lead", "Lcom/crm/v2/domain/model/DialerRecord;", "callManualNumber", "number", "", "clearError", "clearManualNumber", "dismissUpdateStatus", "getUserKey", "hangup", "loadContacts", "loadDialerQueue", "loadLeads", "refresh", "", "manualCall", "onCleared", "onContactSearchChanged", "query", "onPhoneNumberChanged", "refreshDialerQueue", "refreshLeads", "setActiveTab", "tab", "setCurrentLead", "startAutoDial", "stopAutoDial", "submitStatus", "key", "status", "callbackDate", "remarks", "userKey", "callDuration", "isHLLAPBL", "isCibilBad", "isRealEstate", "toggleHold", "toggleMute", "toggleSpeaker", "app_release"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DialerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.repository.DialerRepository dialerRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.repository.LeadRepository leadRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.repository.ContactRepository contactRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.telephony.TelephonyManager telephonyManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.data.datastore.AuthDataStore authDataStore = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.crm.v2.presentation.ui.dialer.DialerUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.dialer.DialerUiState> uiState = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job autoDialJob;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job contactJob;
    private long callStartTime = 0L;
    private int lastCalledIndex = -1;
    
    @javax.inject.Inject()
    public DialerViewModel(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.repository.DialerRepository dialerRepository, @org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.repository.LeadRepository leadRepository, @org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.repository.ContactRepository contactRepository, @org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.TelephonyManager telephonyManager, @org.jetbrains.annotations.NotNull()
    com.crm.v2.data.datastore.AuthDataStore authDataStore) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.crm.v2.presentation.ui.dialer.DialerUiState> getUiState() {
        return null;
    }
    
    public final void setActiveTab(int tab) {
    }
    
    public final void setCurrentLead(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.DialerRecord lead) {
    }
    
    public final void onPhoneNumberChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String number) {
    }
    
    public final void clearManualNumber() {
    }
    
    public final void onContactSearchChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    private final void loadContacts() {
    }
    
    public final void loadDialerQueue() {
    }
    
    public final void loadLeads(boolean refresh) {
    }
    
    public final void refreshLeads() {
    }
    
    public final void refreshDialerQueue() {
    }
    
    public final void startAutoDial() {
    }
    
    public final void stopAutoDial() {
    }
    
    public final void manualCall(@org.jetbrains.annotations.NotNull()
    java.lang.String number) {
    }
    
    public final void callContact(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.Contact contact) {
    }
    
    public final void callManualNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String number) {
    }
    
    public final void callLead(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.model.DialerRecord lead) {
    }
    
    public final void hangup() {
    }
    
    public final void dismissUpdateStatus() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUserKey() {
        return null;
    }
    
    public final void clearError() {
    }
    
    public final void toggleMute() {
    }
    
    public final void toggleSpeaker() {
    }
    
    public final void toggleHold() {
    }
    
    private final void autoDialNext() {
    }
    
    public final void submitStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String status, @org.jetbrains.annotations.Nullable()
    java.lang.String callbackDate, @org.jetbrains.annotations.Nullable()
    java.lang.String remarks, @org.jetbrains.annotations.NotNull()
    java.lang.String userKey, int callDuration, @org.jetbrains.annotations.NotNull()
    java.lang.String isHLLAPBL, boolean isCibilBad, boolean isRealEstate) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}