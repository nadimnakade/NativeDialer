package com.crm.v2.telephony.pjsip;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/crm/v2/telephony/pjsip/MyCall;", "Lorg/pjsip/pjsua2/Call;", "account", "Lcom/crm/v2/telephony/pjsip/MyAccount;", "callId", "", "manager", "Lcom/crm/v2/telephony/pjsip/PjsipManager;", "(Lcom/crm/v2/telephony/pjsip/MyAccount;ILcom/crm/v2/telephony/pjsip/PjsipManager;)V", "onCallMediaState", "", "prm", "Lorg/pjsip/pjsua2/OnCallMediaStateParam;", "onCallState", "Lorg/pjsip/pjsua2/OnCallStateParam;", "app_debug"})
public final class MyCall extends org.pjsip.pjsua2.Call {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.telephony.pjsip.PjsipManager manager = null;
    
    public MyCall(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.pjsip.MyAccount account, int callId, @org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.pjsip.PjsipManager manager) {
        super(0L, false);
    }
    
    @java.lang.Override()
    public void onCallState(@org.jetbrains.annotations.NotNull()
    org.pjsip.pjsua2.OnCallStateParam prm) {
    }
    
    @java.lang.Override()
    public void onCallMediaState(@org.jetbrains.annotations.NotNull()
    org.pjsip.pjsua2.OnCallMediaStateParam prm) {
    }
}