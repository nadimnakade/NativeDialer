package com.crm.v2.telephony.provider;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0012\b\u0007\u0018\u0000 82\u00020\u0001:\u00018B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001f\u001a\u00020 H\u0096@\u00a2\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020\'H\u0096@\u00a2\u0006\u0002\u0010(J\u0016\u0010)\u001a\u00020 2\u0006\u0010&\u001a\u00020\'H\u0096@\u00a2\u0006\u0002\u0010(J\b\u0010*\u001a\u00020\u0007H\u0016J\u000e\u0010+\u001a\u00020 H\u0096@\u00a2\u0006\u0002\u0010!J\u000e\u0010,\u001a\u00020 H\u0096@\u00a2\u0006\u0002\u0010!J\u0016\u0010-\u001a\u00020 2\u0006\u0010#\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010$J\b\u0010.\u001a\u00020 H\u0002J\u0016\u0010/\u001a\u00020 2\u0006\u00100\u001a\u00020\u001aH\u0096@\u00a2\u0006\u0002\u00101J\b\u00102\u001a\u00020 H\u0002J\u000e\u00103\u001a\u00020 H\u0096@\u00a2\u0006\u0002\u0010!J\u0016\u00104\u001a\u00020 2\u0006\u0010#\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010$J\u0016\u00105\u001a\u00020 2\u0006\u0010&\u001a\u00020\'H\u0096@\u00a2\u0006\u0002\u0010(J\u000e\u00106\u001a\u00020 H\u0096@\u00a2\u0006\u0002\u0010!J\b\u00107\u001a\u00020 H\u0002R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u000e\u0010\u0015\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0010\u00a8\u00069"}, d2 = {"Lcom/crm/v2/telephony/provider/PjsipTelephonyProvider;", "Lcom/crm/v2/telephony/TelephonyManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_callState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/telephony/CallState;", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/crm/v2/telephony/TelephonyEvent;", "_registrationState", "", "callState", "Lkotlinx/coroutines/flow/Flow;", "getCallState", "()Lkotlinx/coroutines/flow/Flow;", "currentCallId", "", "events", "getEvents", "isInitialized", "isReRegistering", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "pendingConfig", "Lcom/crm/v2/telephony/TelephonyConfig;", "pjsipManager", "Lcom/crm/v2/telephony/PjsipManager;", "registrationState", "getRegistrationState", "answer", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bluetooth", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "call", "number", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "conference", "getCurrentState", "hangup", "hold", "mute", "reRegister", "register", "config", "(Lcom/crm/v2/telephony/TelephonyConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerNetworkCallback", "resume", "speaker", "transfer", "unregister", "unregisterNetworkCallback", "Companion", "app_debug"})
public final class PjsipTelephonyProvider implements com.crm.v2.telephony.TelephonyManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "PjsipTelephonyProvider";
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.telephony.PjsipManager pjsipManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.crm.v2.telephony.CallState> _callState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.crm.v2.telephony.CallState> callState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.crm.v2.telephony.TelephonyEvent> _events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.crm.v2.telephony.TelephonyEvent> events = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _registrationState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> registrationState = null;
    private int currentCallId = -1;
    @kotlin.jvm.Volatile()
    private volatile boolean isInitialized = false;
    @kotlin.jvm.Volatile()
    private volatile boolean isReRegistering = false;
    @org.jetbrains.annotations.Nullable()
    private com.crm.v2.telephony.TelephonyConfig pendingConfig;
    @org.jetbrains.annotations.Nullable()
    private android.net.ConnectivityManager.NetworkCallback networkCallback;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.telephony.provider.PjsipTelephonyProvider.Companion Companion = null;
    
    @javax.inject.Inject()
    public PjsipTelephonyProvider(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.crm.v2.telephony.CallState> getCallState() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.crm.v2.telephony.TelephonyEvent> getEvents() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.lang.Boolean> getRegistrationState() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object register(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.TelephonyConfig config, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object unregister(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object call(@org.jetbrains.annotations.NotNull()
    java.lang.String number, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object answer(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object hangup(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object hold(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object resume(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object transfer(@org.jetbrains.annotations.NotNull()
    java.lang.String number, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object conference(@org.jetbrains.annotations.NotNull()
    java.lang.String number, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object mute(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object speaker(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object bluetooth(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.crm.v2.telephony.CallState getCurrentState() {
        return null;
    }
    
    private final void registerNetworkCallback() {
    }
    
    private final void unregisterNetworkCallback() {
    }
    
    private final void reRegister() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/crm/v2/telephony/provider/PjsipTelephonyProvider$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}