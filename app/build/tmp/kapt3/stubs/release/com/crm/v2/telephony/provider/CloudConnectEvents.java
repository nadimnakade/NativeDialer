package com.crm.v2.telephony.provider;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0010"}, d2 = {"Lcom/crm/v2/telephony/provider/CloudConnectEvents;", "", "()V", "<set-?>", "Lcom/crm/v2/telephony/CallState;", "currentCallState", "getCurrentCallState", "()Lcom/crm/v2/telephony/CallState;", "events", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/crm/v2/telephony/TelephonyEvent;", "getEvents", "()Lkotlinx/coroutines/flow/MutableSharedFlow;", "updateState", "", "state", "app_release"})
public final class CloudConnectEvents {
    @org.jetbrains.annotations.NotNull()
    private static final kotlinx.coroutines.flow.MutableSharedFlow<com.crm.v2.telephony.TelephonyEvent> events = null;
    @org.jetbrains.annotations.NotNull()
    private static com.crm.v2.telephony.CallState currentCallState;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.telephony.provider.CloudConnectEvents INSTANCE = null;
    
    private CloudConnectEvents() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableSharedFlow<com.crm.v2.telephony.TelephonyEvent> getEvents() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.telephony.CallState getCurrentCallState() {
        return null;
    }
    
    public final void updateState(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.CallState state) {
    }
}