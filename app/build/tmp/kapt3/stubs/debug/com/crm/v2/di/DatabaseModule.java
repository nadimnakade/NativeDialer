package com.crm.v2.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00020\u0004H\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/crm/v2/di/DatabaseModule;", "", "()V", "provideAppDatabase", "Lcom/crm/v2/data/database/AppDatabase;", "context", "Landroid/content/Context;", "provideCallLogDao", "Lcom/crm/v2/data/database/dao/CallLogDao;", "db", "provideContactDao", "Lcom/crm/v2/data/database/dao/ContactDao;", "provideDialerDataDao", "Lcom/crm/v2/data/database/dao/DialerDataDao;", "provideLeadDao", "Lcom/crm/v2/data/database/dao/LeadDao;", "provideSyncQueueDao", "Lcom/crm/v2/data/database/dao/SyncQueueDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.crm.v2.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.database.AppDatabase provideAppDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.database.dao.LeadDao provideLeadDao(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.AppDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.database.dao.CallLogDao provideCallLogDao(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.AppDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.database.dao.DialerDataDao provideDialerDataDao(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.AppDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.database.dao.ContactDao provideContactDao(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.AppDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.crm.v2.data.database.dao.SyncQueueDao provideSyncQueueDao(@org.jetbrains.annotations.NotNull()
    com.crm.v2.data.database.AppDatabase db) {
        return null;
    }
}