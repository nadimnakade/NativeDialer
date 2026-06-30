package com.crm.v2.telephony.pjsip;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 G2\u00020\u0001:\u0001GB\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001aJ\u0006\u0010\u001d\u001a\u00020\u0017J\u0006\u0010\u001e\u001a\u00020\u0017J\u0010\u0010\u001f\u001a\u00020\u00122\b\b\u0002\u0010 \u001a\u00020!J\u0006\u0010\u0011\u001a\u00020\u0012J\u0006\u0010\u0013\u001a\u00020\u0012J\u000e\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u001aJ\u000e\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u0012J\t\u0010&\u001a\u00020!H\u0082 J\t\u0010\'\u001a\u00020!H\u0082 J\t\u0010(\u001a\u00020!H\u0082 J\t\u0010)\u001a\u00020!H\u0082 J\u0011\u0010*\u001a\u00020!2\u0006\u0010 \u001a\u00020!H\u0082 J\u0011\u0010+\u001a\u00020!2\u0006\u0010#\u001a\u00020\u001aH\u0082 J\u0011\u0010,\u001a\u00020!2\u0006\u0010$\u001a\u00020\u0012H\u0082 JA\u0010-\u001a\u00020!2\u0006\u0010.\u001a\u00020\u001a2\u0006\u0010/\u001a\u00020\u001a2\u0006\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u00020\u001a2\u0006\u00102\u001a\u00020\u001a2\u0006\u00103\u001a\u00020\u001a2\u0006\u00104\u001a\u00020\u0012H\u0082 J\t\u00105\u001a\u00020!H\u0082 J\u0011\u00106\u001a\u00020!2\u0006\u00107\u001a\u00020\u0012H\u0082 J\u0011\u00108\u001a\u00020!2\u0006\u0010#\u001a\u00020\u001aH\u0082 J\t\u00109\u001a\u00020!H\u0082 J&\u0010:\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u001a2\u0006\u0010<\u001a\u00020\u001a2\u0006\u0010=\u001a\u00020!2\u0006\u0010>\u001a\u00020\u001aJ\u000e\u0010?\u001a\u00020\u00122\u0006\u0010@\u001a\u00020AJ\u0006\u0010B\u001a\u00020\u0017J\u000e\u0010C\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u0012J\u000e\u0010D\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u0012J\u000e\u0010E\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u001aJ\u0006\u0010F\u001a\u00020\u0017R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010\u00a8\u0006H"}, d2 = {"Lcom/crm/v2/telephony/pjsip/PjsipManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/crm/v2/telephony/pjsip/SipEvent;", "_sipState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/telephony/pjsip/SipCallState;", "audioManager", "Landroid/media/AudioManager;", "events", "Lkotlinx/coroutines/flow/Flow;", "getEvents", "()Lkotlinx/coroutines/flow/Flow;", "isInitialized", "", "isRegistered", "sipState", "getSipState", "answer", "", "destroy", "formatSipUri", "", "number", "server", "hangup", "hold", "initialize", "logLevel", "", "makeCall", "targetUri", "mute", "muted", "nativeAnswer", "nativeDestroy", "nativeHangup", "nativeHold", "nativeInit", "nativeMakeCall", "nativeMute", "nativeRegister", "accountId", "registrarUri", "username", "password", "realm", "proxyUri", "iceEnabled", "nativeResume", "nativeSetSpeaker", "on", "nativeTransfer", "nativeUnregister", "onNativeEvent", "eventType", "callId", "statusCode", "reason", "register", "config", "Lcom/crm/v2/telephony/pjsip/SipAccountConfig;", "resume", "setBluetooth", "setSpeaker", "transfer", "unregister", "Companion", "app_debug"})
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
    private boolean isInitialized = false;
    private boolean isRegistered = false;
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
    public final kotlinx.coroutines.flow.Flow<com.crm.v2.telephony.pjsip.SipEvent> getEvents() {
        return null;
    }
    
    private final native int nativeInit(int logLevel) {
        return 0;
    }
    
    private final native int nativeDestroy() {
        return 0;
    }
    
    private final native int nativeRegister(java.lang.String accountId, java.lang.String registrarUri, java.lang.String username, java.lang.String password, java.lang.String realm, java.lang.String proxyUri, boolean iceEnabled) {
        return 0;
    }
    
    private final native int nativeUnregister() {
        return 0;
    }
    
    private final native int nativeMakeCall(java.lang.String targetUri) {
        return 0;
    }
    
    private final native int nativeHangup() {
        return 0;
    }
    
    private final native int nativeHold() {
        return 0;
    }
    
    private final native int nativeResume() {
        return 0;
    }
    
    private final native int nativeTransfer(java.lang.String targetUri) {
        return 0;
    }
    
    private final native int nativeAnswer() {
        return 0;
    }
    
    private final native int nativeMute(boolean mute) {
        return 0;
    }
    
    private final native int nativeSetSpeaker(boolean on) {
        return 0;
    }
    
    public final boolean initialize(int logLevel) {
        return false;
    }
    
    public final void destroy() {
    }
    
    public final boolean register(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.pjsip.SipAccountConfig config) {
        return false;
    }
    
    public final void unregister() {
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
    
    public final void onNativeEvent(@org.jetbrains.annotations.NotNull()
    java.lang.String eventType, @org.jetbrains.annotations.NotNull()
    java.lang.String callId, int statusCode, @org.jetbrains.annotations.NotNull()
    java.lang.String reason) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/crm/v2/telephony/pjsip/PjsipManager$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}