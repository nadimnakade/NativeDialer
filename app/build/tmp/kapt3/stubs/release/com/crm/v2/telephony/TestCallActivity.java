package com.crm.v2.telephony;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J+\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016\u00a2\u0006\u0002\u0010\u001fR\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\fR\u001e\u0010\r\u001a\u00020\u000e8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u00a8\u0006 "}, d2 = {"Lcom/crm/v2/telephony/TestCallActivity;", "Landroidx/activity/ComponentActivity;", "()V", "authDataStore", "Lcom/crm/v2/data/datastore/AuthDataStore;", "getAuthDataStore", "()Lcom/crm/v2/data/datastore/AuthDataStore;", "setAuthDataStore", "(Lcom/crm/v2/data/datastore/AuthDataStore;)V", "requiredPermissions", "", "", "[Ljava/lang/String;", "telephonyProvider", "Lcom/crm/v2/telephony/TelephonyManager;", "getTelephonyProvider", "()Lcom/crm/v2/telephony/TelephonyManager;", "setTelephonyProvider", "(Lcom/crm/v2/telephony/TelephonyManager;)V", "hasPermissions", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onRequestPermissionsResult", "requestCode", "", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "app_release"})
public final class TestCallActivity extends androidx.activity.ComponentActivity {
    @javax.inject.Inject()
    public com.crm.v2.telephony.TelephonyManager telephonyProvider;
    @javax.inject.Inject()
    public com.crm.v2.data.datastore.AuthDataStore authDataStore;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String[] requiredPermissions = {"android.permission.RECORD_AUDIO", "android.permission.CALL_PHONE", "android.permission.READ_PHONE_STATE"};
    
    public TestCallActivity() {
        super(0);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.telephony.TelephonyManager getTelephonyProvider() {
        return null;
    }
    
    public final void setTelephonyProvider(@org.jetbrains.annotations.NotNull()
    com.crm.v2.telephony.TelephonyManager p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.datastore.AuthDataStore getAuthDataStore() {
        return null;
    }
    
    public final void setAuthDataStore(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.datastore.AuthDataStore p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull()
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull()
    int[] grantResults) {
    }
    
    private final boolean hasPermissions() {
        return false;
    }
}