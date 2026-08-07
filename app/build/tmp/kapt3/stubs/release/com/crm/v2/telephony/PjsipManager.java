package com.crm.v2.telephony;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0007\u0018\u0000 +2\u00020\u0001:\u0002*+B\u0005\u00a2\u0006\u0002\u0010\u0002J.\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bJ\u001e\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0006J\u0018\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u0006J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0006J\u000e\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006J\u000e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ1\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\bH\u0082 J!\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0006H\u0082 J\u0019\u0010 \u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0082 J\t\u0010!\u001a\u00020\u0006H\u0082 J\t\u0010\"\u001a\u00020\u0015H\u0082 J\t\u0010#\u001a\u00020\u0006H\u0082 J\u0011\u0010$\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006H\u0082 J\u0011\u0010%\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\bH\u0082 J\u0011\u0010&\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001aH\u0082 J\u0019\u0010\'\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0004H\u0082 J\u0016\u0010)\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/crm/v2/telephony/PjsipManager;", "", "()V", "isCreated", "", "addAccount", "", "sipUri", "", "regUri", "username", "password", "realm", "addTransport", "type", "server", "port", "answer", "callId", "code", "destroy", "", "getActiveCallId", "hangup", "init", "callback", "Lcom/crm/v2/telephony/PjsipManager$Callback;", "makeCall", "nativeAddAccount", "uri", "nativeAddTransport", "transportType", "nativeAnswerCall", "nativeCreate", "nativeDestroy", "nativeGetActiveCallId", "nativeHangupCall", "nativeMakeCall", "nativeSetCallback", "nativeSetMute", "mute", "setMute", "Callback", "Companion", "app_release"})
public final class PjsipManager {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "PjsipManager";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LIB_NAME = "pjsip_bridge";
    public static final int TRANSPORT_UDP = 0;
    public static final int TRANSPORT_TLS = 1;
    public static final int TRANSPORT_WSS = 2;
    public static final int TRANSPORT_TCP = 3;
    private boolean isCreated = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.telephony.PjsipManager.Companion Companion = null;
    
    public PjsipManager() {
        super();
    }
    
    private final native int nativeCreate() {
        return 0;
    }
    
    private final native int nativeAddTransport(int transportType, java.lang.String server, int port) {
        return 0;
    }
    
    private final native int nativeAddAccount(java.lang.String uri, java.lang.String regUri, java.lang.String username, java.lang.String password, java.lang.String realm) {
        return 0;
    }
    
    private final native int nativeMakeCall(java.lang.String uri) {
        return 0;
    }
    
    private final native int nativeHangupCall(int callId) {
        return 0;
    }
    
    private final native int nativeAnswerCall(int callId, int code) {
        return 0;
    }
    
    private final native int nativeGetActiveCallId() {
        return 0;
    }
    
    private final native void nativeSetCallback(com.crm.v2.telephony.PjsipManager.Callback callback) {
    }
    
    private final native int nativeSetMute(int callId, boolean mute) {
        return 0;
    }
    
    private final native void nativeDestroy() {
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.PjsipManager.Callback callback) {
    }
    
    public final int addTransport(int type, @org.jetbrains.annotations.NotNull()
    java.lang.String server, int port) {
        return 0;
    }
    
    public final int addAccount(@org.jetbrains.annotations.NotNull()
    java.lang.String sipUri, @org.jetbrains.annotations.NotNull()
    java.lang.String regUri, @org.jetbrains.annotations.NotNull()
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String realm) {
        return 0;
    }
    
    public final int makeCall(@org.jetbrains.annotations.NotNull()
    java.lang.String sipUri) {
        return 0;
    }
    
    public final int hangup(int callId) {
        return 0;
    }
    
    public final int getActiveCallId() {
        return 0;
    }
    
    public final int answer(int callId, int code) {
        return 0;
    }
    
    public final int setMute(int callId, boolean mute) {
        return 0;
    }
    
    public final void destroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J(\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\nH&J \u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&\u00a8\u0006\u0010"}, d2 = {"Lcom/crm/v2/telephony/PjsipManager$Callback;", "", "onCallMediaState", "", "callId", "", "onCallState", "state", "lastStatus", "reason", "", "onIncomingCall", "accId", "remoteUri", "onRegState", "code", "app_release"})
    public static abstract interface Callback {
        
        public abstract void onRegState(int accId, int code, @org.jetbrains.annotations.NotNull()
        java.lang.String reason);
        
        public abstract void onCallState(int callId, int state, int lastStatus, @org.jetbrains.annotations.NotNull()
        java.lang.String reason);
        
        public abstract void onIncomingCall(int accId, int callId, @org.jetbrains.annotations.NotNull()
        java.lang.String remoteUri);
        
        public abstract void onCallMediaState(int callId);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/crm/v2/telephony/PjsipManager$Companion;", "", "()V", "LIB_NAME", "", "TAG", "TRANSPORT_TCP", "", "TRANSPORT_TLS", "TRANSPORT_UDP", "TRANSPORT_WSS", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}