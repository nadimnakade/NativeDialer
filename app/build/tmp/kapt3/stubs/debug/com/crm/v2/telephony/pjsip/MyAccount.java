package com.crm.v2.telephony.pjsip;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/crm/v2/telephony/pjsip/MyAccount;", "Lorg/pjsip/pjsua2/Account;", "manager", "Lcom/crm/v2/telephony/pjsip/PjsipManager;", "(Lcom/crm/v2/telephony/pjsip/PjsipManager;)V", "onIncomingCall", "", "prm", "Lorg/pjsip/pjsua2/OnIncomingCallParam;", "onRegState", "Lorg/pjsip/pjsua2/OnRegStateParam;", "app_debug"})
public final class MyAccount extends org.pjsip.pjsua2.Account {
    @org.jetbrains.annotations.NotNull()
    private final com.crm.v2.telephony.pjsip.PjsipManager manager = null;
    
    public MyAccount(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.pjsip.PjsipManager manager) {
        super(0L, false);
    }
    
    @java.lang.Override()
    public void onRegState(@org.jetbrains.annotations.NotNull()
    org.pjsip.pjsua2.OnRegStateParam prm) {
    }
    
    @java.lang.Override()
    public void onIncomingCall(@org.jetbrains.annotations.NotNull()
    org.pjsip.pjsua2.OnIncomingCallParam prm) {
    }
}