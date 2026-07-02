package com.crm.v2.telephony.pjsip;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0007\u0018\u0000 A2\u00020\u0001:\u0001AB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010 \u001a\u00020!J\u0006\u0010\"\u001a\u00020!J\u0016\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020$J\u0006\u0010\'\u001a\u00020!J\u0006\u0010(\u001a\u00020!J\u0010\u0010)\u001a\u00020\u00182\b\b\u0002\u0010*\u001a\u00020+J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010,\u001a\u00020!2\u0006\u0010-\u001a\u00020$J\u000e\u0010.\u001a\u00020!2\u0006\u0010/\u001a\u00020\u0018J\u001d\u00100\u001a\u00020!2\u0006\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u000203H\u0000\u00a2\u0006\u0002\b4J\u0006\u00105\u001a\u00020!J\u0016\u00106\u001a\u00020!2\u0006\u00107\u001a\u00020$2\u0006\u00108\u001a\u00020+J\u000e\u00109\u001a\u00020\u00182\u0006\u0010:\u001a\u00020\u001dJ\u0006\u0010;\u001a\u00020!J\u000e\u0010<\u001a\u00020!2\u0006\u0010=\u001a\u00020\u0018J\u000e\u0010>\u001a\u00020!2\u0006\u0010=\u001a\u00020\u0018J\u000e\u0010?\u001a\u00020!2\u0006\u0010-\u001a\u00020$J\u0006\u0010@\u001a\u00020!R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0080\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00070\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0016\u00a8\u0006B"}, d2 = {"Lcom/crm/v2/telephony/pjsip/PjsipManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/crm/v2/telephony/pjsip/SipEvent;", "get_events$app_debug", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "_sipState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/telephony/pjsip/SipCallState;", "audioManager", "Landroid/media/AudioManager;", "currentCall", "Lcom/crm/v2/telephony/pjsip/MyCall;", "ep", "Lorg/pjsip/pjsua2/Endpoint;", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "isInitialized", "", "isRegistered", "myAccount", "Lcom/crm/v2/telephony/pjsip/MyAccount;", "pendingRegistration", "Lcom/crm/v2/telephony/pjsip/SipAccountConfig;", "sipState", "getSipState", "answer", "", "destroy", "formatSipUri", "", "number", "server", "hangup", "hold", "initialize", "logLevel", "", "makeCall", "targetUri", "mute", "muted", "onCallState", "call", "ci", "Lorg/pjsip/pjsua2/CallInfo;", "onCallState$app_debug", "onPermissionGranted", "onRegState", "accountId", "statusCode", "register", "config", "resume", "setBluetooth", "on", "setSpeaker", "transfer", "unregister", "Companion", "app_debug"})
public final class PjsipManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "PjsipManager";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.crm.v2.telephony.pjsip.SipCallState> _sipState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.crm.v2.telephony.pjsip.SipCallState> sipState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.crm.v2.telephony.pjsip.SipEvent> _events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.crm.v2.telephony.pjsip.SipEvent> events = null;
    @org.jetbrains.annotations.Nullable()
    private org.pjsip.pjsua2.Endpoint ep;
    @org.jetbrains.annotations.Nullable()
    private com.crm.v2.telephony.pjsip.MyAccount myAccount;
    @org.jetbrains.annotations.Nullable()
    private com.crm.v2.telephony.pjsip.MyCall currentCall;
    private boolean isInitialized = false;
    private boolean isRegistered = false;
    @org.jetbrains.annotations.Nullable()
    private com.crm.v2.telephony.pjsip.SipAccountConfig pendingRegistration;
    @org.jetbrains.annotations.NotNull()
    private final android.media.AudioManager audioManager = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.telephony.pjsip.PjsipManager.Companion Companion = null;
    
    @javax.inject.Inject()
    public PjsipManager(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.crm.v2.telephony.pjsip.SipCallState> getSipState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableSharedFlow<com.crm.v2.telephony.pjsip.SipEvent> get_events$app_debug() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.crm.v2.telephony.pjsip.SipEvent> getEvents() {
        return null;
    }
    
    public final void onPermissionGranted() {
    }
    
    public final boolean initialize(int logLevel) {
        return false;
    }
    
    public final boolean register(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.pjsip.SipAccountConfig config) {
        return false;
    }
    
    public final void unregister() {
    }
    
    public final void destroy() {
    }
    
    public final void makeCall(@org.jetbrains.annotations.NotNull()
    java.lang.String targetUri) {
    }
    
    public final void answer() {
    }
    
    public final void hangup() {
    }
    
    public final void hold() {
    }
    
    public final void resume() {
    }
    
    public final void transfer(@org.jetbrains.annotations.NotNull()
    java.lang.String targetUri) {
    }
    
    public final void mute(boolean muted) {
    }
    
    public final void setSpeaker(boolean on) {
    }
    
    public final void setBluetooth(boolean on) {
    }
    
    public final boolean isRegistered() {
        return false;
    }
    
    public final boolean isInitialized() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatSipUri(@org.jetbrains.annotations.NotNull()
    java.lang.String number, @org.jetbrains.annotations.NotNull()
    java.lang.String server) {
        return null;
    }
    
    public final void onCallState$app_debug(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.pjsip.MyCall call, @org.jetbrains.annotations.NotNull()
    org.pjsip.pjsua2.CallInfo ci) {
    }
    
    public final void onRegState(@org.jetbrains.annotations.NotNull()
    java.lang.String accountId, int statusCode) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/crm/v2/telephony/pjsip/PjsipManager$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}