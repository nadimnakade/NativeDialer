package com.crm.v2.telephony.provider;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016\u00a8\u0006\n"}, d2 = {"Lcom/crm/v2/telephony/provider/CloudConnectWebhookReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "Companion", "app_release"})
public final class CloudConnectWebhookReceiver extends android.content.BroadcastReceiver {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "CloudConnectWebhook";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CALL_EVENT = "com.crm.v2.CALL_EVENT";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_UNIQUE_TOKEN = "unique_token";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_UUID = "uuid";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_EXTENSION_NUMBER = "extension_number";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_CALLER_NAME = "caller_name";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_CALLER_NUMBER = "caller_number";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_CALL_STATUS = "call_status";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_CALL_DIRECTION = "call_direction";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_CALL_DURATION = "call_duration";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_CALL_ANSWER_TIME = "call_answer_time";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_REASON = "reason";
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.telephony.provider.CloudConnectWebhookReceiver.Companion Companion = null;
    
    public CloudConnectWebhookReceiver() {
        super();
    }
    
    @java.lang.Override()
    public void onReceive(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/crm/v2/telephony/provider/CloudConnectWebhookReceiver$Companion;", "", "()V", "ACTION_CALL_EVENT", "", "EXTRA_CALLER_NAME", "EXTRA_CALLER_NUMBER", "EXTRA_CALL_ANSWER_TIME", "EXTRA_CALL_DIRECTION", "EXTRA_CALL_DURATION", "EXTRA_CALL_STATUS", "EXTRA_EXTENSION_NUMBER", "EXTRA_REASON", "EXTRA_UNIQUE_TOKEN", "EXTRA_UUID", "TAG", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}