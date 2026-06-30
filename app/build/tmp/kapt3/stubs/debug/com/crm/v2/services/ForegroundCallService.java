package com.crm.v2.services;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\"\u0010\u0015\u001a\u00020\u00162\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u001a"}, d2 = {"Lcom/crm/v2/services/ForegroundCallService;", "Landroidx/lifecycle/LifecycleService;", "()V", "telephonyManager", "Lcom/crm/v2/telephony/TelephonyManager;", "getTelephonyManager", "()Lcom/crm/v2/telephony/TelephonyManager;", "setTelephonyManager", "(Lcom/crm/v2/telephony/TelephonyManager;)V", "createNotification", "Landroid/app/Notification;", "title", "", "content", "createNotificationChannel", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onStartCommand", "", "flags", "startId", "Companion", "app_debug"})
public final class ForegroundCallService extends androidx.lifecycle.LifecycleService {
    @javax.inject.Inject()
    public com.crm.v2.telephony.TelephonyManager telephonyManager;
    public static final int NOTIFICATION_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.services.ForegroundCallService.Companion Companion = null;
    
    public ForegroundCallService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.telephony.TelephonyManager getTelephonyManager() {
        return null;
    }
    
    public final void setTelephonyManager(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.TelephonyManager p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
    
    private final void createNotificationChannel() {
    }
    
    private final android.app.Notification createNotification(java.lang.String title, java.lang.String content) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/crm/v2/services/ForegroundCallService$Companion;", "", "()V", "NOTIFICATION_ID", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}