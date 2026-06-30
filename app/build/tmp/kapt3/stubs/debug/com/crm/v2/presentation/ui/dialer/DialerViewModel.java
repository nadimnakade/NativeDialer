package com.crm.v2.presentation.ui.dialer;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0006\u0010\u0014\u001a\u00020\u0013J\u0006\u0010\u0015\u001a\u00020\u0013J\u0006\u0010\u0016\u001a\u00020\u0013J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\b\u0010\u001a\u001a\u00020\u0013H\u0014J\u000e\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u0013J\u0006\u0010 \u001a\u00020\u0013J\u0006\u0010!\u001a\u00020\u0013J\u0006\u0010\"\u001a\u00020\u0013J\u0006\u0010#\u001a\u00020\u0013R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006$"}, d2 = {"Lcom/crm/v2/presentation/ui/dialer/DialerViewModel;", "Landroidx/lifecycle/ViewModel;", "dialerRepository", "Lcom/crm/v2/domain/repository/DialerRepository;", "telephonyManager", "Lcom/crm/v2/telephony/TelephonyManager;", "authDataStore", "Lcom/crm/v2/data/datastore/AuthDataStore;", "(Lcom/crm/v2/domain/repository/DialerRepository;Lcom/crm/v2/telephony/TelephonyManager;Lcom/crm/v2/data/datastore/AuthDataStore;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/presentation/ui/dialer/DialerUiState;", "autoDialJob", "Lkotlinx/coroutines/Job;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "autoDialNext", "", "clearError", "hangup", "loadDialerQueue", "manualCall", "number", "", "onCleared", "onPhoneNumberChanged", "setActiveTab", "tab", "", "startAutoDial", "stopAutoDial", "toggleHold", "toggleMute", "toggleSpeaker", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DialerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.domain.repository.DialerRepository dialerRepository = null;
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
    
    @javax.inject.Inject()
    public DialerViewModel(@org.jetbrains.annotations.NotNull()
    com.crm.v2.domain.repository.DialerRepository dialerRepository, @org.jetbrains.annotations.NotNull()
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
    
    public final void onPhoneNumberChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String number) {
    }
    
    public final void loadDialerQueue() {
    }
    
    public final void startAutoDial() {
    }
    
    public final void stopAutoDial() {
    }
    
    public final void manualCall(@org.jetbrains.annotations.NotNull()
    java.lang.String number) {
    }
    
    public final void hangup() {
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
    
    @java.lang.Override()
    protected void onCleared() {
    }
}