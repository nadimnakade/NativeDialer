package com.crm.v2.telephony.pjsip;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0007\u0018\u0000 12\u00020\u0001:\u00011B\u0019\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u001b\u001a\u00020\u001cH\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010 J\u0016\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0096@\u00a2\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0096@\u00a2\u0006\u0002\u0010$J\b\u0010&\u001a\u00020\tH\u0016J\u000e\u0010\'\u001a\u00020\u001cH\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u000e\u0010(\u001a\u00020\u001cH\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010)\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010 J\u0016\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010,J\u000e\u0010-\u001a\u00020\u001cH\u0096@\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010.\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010 J\u0016\u0010/\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020#H\u0096@\u00a2\u0006\u0002\u0010$J\u000e\u00100\u001a\u00020\u001cH\u0096@\u00a2\u0006\u0002\u0010\u001dR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\t0\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/crm/v2/telephony/pjsip/PjsipTelephonyProvider;", "Lcom/crm/v2/telephony/TelephonyManager;", "context", "Landroid/content/Context;", "pjsipManager", "Lcom/crm/v2/telephony/pjsip/PjsipManager;", "(Landroid/content/Context;Lcom/crm/v2/telephony/pjsip/PjsipManager;)V", "_callState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/telephony/CallState;", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/crm/v2/telephony/TelephonyEvent;", "_registrationState", "", "callState", "Lkotlinx/coroutines/flow/Flow;", "getCallState", "()Lkotlinx/coroutines/flow/Flow;", "currentConfig", "Lcom/crm/v2/telephony/TelephonyConfig;", "events", "getEvents", "registrationState", "getRegistrationState", "scope", "Lkotlinx/coroutines/CoroutineScope;", "answer", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bluetooth", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "call", "number", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "conference", "getCurrentState", "hangup", "hold", "mute", "register", "config", "(Lcom/crm/v2/telephony/TelephonyConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resume", "speaker", "transfer", "unregister", "Companion", "app_debug"})
public final class PjsipTelephonyProvider implements com.crm.v2.telephony.TelephonyManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.telephony.pjsip.PjsipManager pjsipManager = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "PjsipTelephonyProvider";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SIP_SERVER = "11.111.111.111";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SIP_PORT = "5060";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope scope = null;
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
    @org.jetbrains.annotations.Nullable()
    private com.crm.v2.telephony.TelephonyConfig currentConfig;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.telephony.pjsip.PjsipTelephonyProvider.Companion Companion = null;
    
    @javax.inject.Inject()
    public PjsipTelephonyProvider(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.pjsip.PjsipManager pjsipManager) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/crm/v2/telephony/pjsip/PjsipTelephonyProvider$Companion;", "", "()V", "SIP_PORT", "", "SIP_SERVER", "TAG", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}