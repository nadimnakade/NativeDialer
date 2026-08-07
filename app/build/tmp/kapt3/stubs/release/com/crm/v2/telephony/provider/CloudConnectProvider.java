package com.crm.v2.telephony.provider;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u0000 <2\u00020\u0001:\u0001<B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001d\u001a\u00020\u001eH\u0096@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u0016H\u0096@\u00a2\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u0016H\u0096@\u00a2\u0006\u0002\u0010%J>\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0(2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u00162\n\b\u0002\u0010-\u001a\u0004\u0018\u00010\u0016H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b.\u0010/J\b\u00100\u001a\u00020\u0007H\u0016J\u0006\u00101\u001a\u00020\u0016J\u000e\u00102\u001a\u00020\u001eH\u0096@\u00a2\u0006\u0002\u0010\u001fJ\u000e\u00103\u001a\u00020\u001eH\u0096@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u00104\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\"J\u0016\u00105\u001a\u00020\u001e2\u0006\u00106\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u00107J\u000e\u00108\u001a\u00020\u001eH\u0096@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u00109\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\"J\u0016\u0010:\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u0016H\u0096@\u00a2\u0006\u0002\u0010%J\u000e\u0010;\u001a\u00020\u001eH\u0096@\u00a2\u0006\u0002\u0010\u001fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u000eX\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006="}, d2 = {"Lcom/crm/v2/telephony/provider/CloudConnectProvider;", "Lcom/crm/v2/telephony/TelephonyManager;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_callState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/crm/v2/telephony/CallState;", "_events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/crm/v2/telephony/TelephonyEvent;", "_registrationState", "", "callState", "Lkotlinx/coroutines/flow/Flow;", "getCallState", "()Lkotlinx/coroutines/flow/Flow;", "client", "Lokhttp3/OkHttpClient;", "currentConfig", "Lcom/crm/v2/telephony/TelephonyConfig;", "currentUniqueToken", "", "events", "getEvents", "json", "Lkotlinx/serialization/json/Json;", "registrationState", "getRegistrationState", "answer", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bluetooth", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "call", "number", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "conference", "getCallLog", "Lkotlin/Result;", "", "Lcom/crm/v2/data/network/dto/cloudconnect/CallLogEntry;", "startDate", "endDate", "uniqueToken", "getCallLog-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentState", "getCurrentUniqueToken", "hangup", "hold", "mute", "register", "config", "(Lcom/crm/v2/telephony/TelephonyConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resume", "speaker", "transfer", "unregister", "Companion", "app_release"})
public final class CloudConnectProvider implements com.crm.v2.telephony.TelephonyManager {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "CloudConnectProvider";
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient client = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.serialization.json.Json json = null;
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
    private java.lang.String currentUniqueToken = "";
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.telephony.provider.CloudConnectProvider.Companion Companion = null;
    
    @javax.inject.Inject()
    public CloudConnectProvider(@dagger.hilt.android.qualifiers.ApplicationContext()
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
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCurrentUniqueToken() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/crm/v2/telephony/provider/CloudConnectProvider$Companion;", "", "()V", "TAG", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}