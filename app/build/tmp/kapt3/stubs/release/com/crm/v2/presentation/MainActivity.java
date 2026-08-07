package com.crm.v2.presentation;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010 \u001a\u00020\u0010H\u0002J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0002J\u0012\u0010$\u001a\u00020\"2\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\b\u0010\'\u001a\u00020\"H\u0014R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R+\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u00188B@BX\u0082\u008e\u0002\u00a2\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\u00a8\u0006)"}, d2 = {"Lcom/crm/v2/presentation/MainActivity;", "Landroidx/activity/ComponentActivity;", "()V", "authDataStore", "Lcom/crm/v2/data/datastore/AuthDataStore;", "getAuthDataStore", "()Lcom/crm/v2/data/datastore/AuthDataStore;", "setAuthDataStore", "(Lcom/crm/v2/data/datastore/AuthDataStore;)V", "criticalLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "criticalPermissions", "[Ljava/lang/String;", "criticalRequestInFlight", "", "optionalLaunchedOnce", "optionalLauncher", "optionalPermissions", "getOptionalPermissions", "()[Ljava/lang/String;", "optionalRequestInFlight", "<set-?>", "", "permissionTick", "getPermissionTick", "()I", "setPermissionTick", "(I)V", "permissionTick$delegate", "Landroidx/compose/runtime/MutableIntState;", "criticalGranted", "launchCriticalPermissions", "", "launchOptionalPermissions", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "Companion", "app_release"})
public final class MainActivity extends androidx.activity.ComponentActivity {
    @javax.inject.Inject()
    public com.crm.v2.data.datastore.AuthDataStore authDataStore;
    @org.jetbrains.annotations.NotNull()
    private final androidx.compose.runtime.MutableIntState permissionTick$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String[] criticalPermissions = {"android.permission.RECORD_AUDIO", "android.permission.CALL_PHONE", "android.permission.READ_PHONE_STATE"};
    private boolean criticalRequestInFlight = false;
    private boolean optionalRequestInFlight = false;
    private boolean optionalLaunchedOnce = false;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> criticalLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> optionalLauncher = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "MainActivity";
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.presentation.MainActivity.Companion Companion = null;
    
    public MainActivity() {
        super(0);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.datastore.AuthDataStore getAuthDataStore() {
        return null;
    }
    
    public final void setAuthDataStore(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.datastore.AuthDataStore p0) {
    }
    
    private final int getPermissionTick() {
        return 0;
    }
    
    private final void setPermissionTick(int p0) {
    }
    
    private final java.lang.String[] getOptionalPermissions() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    private final boolean criticalGranted() {
        return false;
    }
    
    private final void launchCriticalPermissions() {
    }
    
    private final void launchOptionalPermissions() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/crm/v2/presentation/MainActivity$Companion;", "", "()V", "TAG", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}