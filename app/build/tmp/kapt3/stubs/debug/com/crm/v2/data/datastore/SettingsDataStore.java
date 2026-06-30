package com.crm.v2.data.datastore;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\r\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u001cJ\u0016\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u001fR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\tR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\t\u00a8\u0006!"}, d2 = {"Lcom/crm/v2/data/datastore/SettingsDataStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "autoDialInterval", "Lkotlinx/coroutines/flow/Flow;", "", "getAutoDialInterval", "()Lkotlinx/coroutines/flow/Flow;", "callRecordingEnabled", "", "getCallRecordingEnabled", "darkMode", "Lcom/crm/v2/data/datastore/DarkModePreference;", "getDarkMode", "lastSyncTimestamp", "", "getLastSyncTimestamp", "setAutoDialInterval", "", "seconds", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setCallRecordingEnabled", "enabled", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setDarkMode", "mode", "(Lcom/crm/v2/data/datastore/DarkModePreference;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setLastSyncTimestamp", "timestamp", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class SettingsDataStore {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.crm.v2.data.datastore.DarkModePreference> darkMode = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> autoDialInterval = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> callRecordingEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Long> lastSyncTimestamp = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> DARK_MODE_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Integer> AUTO_DIAL_INTERVAL_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> CALL_RECORDING_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Long> LAST_SYNC_KEY = null;
    private static final int DEFAULT_DIAL_INTERVAL = 10;
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.data.datastore.SettingsDataStore.Companion Companion = null;
    
    public SettingsDataStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.crm.v2.data.datastore.DarkModePreference> getDarkMode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getAutoDialInterval() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getCallRecordingEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Long> getLastSyncTimestamp() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setDarkMode(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.datastore.DarkModePreference mode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setAutoDialInterval(int seconds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setCallRecordingEnabled(boolean enabled, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setLastSyncTimestamp(long timestamp, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/crm/v2/data/datastore/SettingsDataStore$Companion;", "", "()V", "AUTO_DIAL_INTERVAL_KEY", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "CALL_RECORDING_KEY", "", "DARK_MODE_KEY", "", "DEFAULT_DIAL_INTERVAL", "LAST_SYNC_KEY", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}