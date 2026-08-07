package com.crm.v2.data.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\r"}, d2 = {"Lcom/crm/v2/data/database/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "callLogDao", "Lcom/crm/v2/data/database/dao/CallLogDao;", "contactDao", "Lcom/crm/v2/data/database/dao/ContactDao;", "dialerDataDao", "Lcom/crm/v2/data/database/dao/DialerDataDao;", "leadDao", "Lcom/crm/v2/data/database/dao/LeadDao;", "syncQueueDao", "Lcom/crm/v2/data/database/dao/SyncQueueDao;", "app_release"})
@androidx.room.Database(entities = {com.crm.v2.data.database.entity.LeadEntity.class, com.crm.v2.data.database.entity.CallLogEntity.class, com.crm.v2.data.database.entity.DialerDataEntity.class, com.crm.v2.data.database.entity.ContactEntity.class, com.crm.v2.data.database.entity.SyncQueueEntity.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.data.database.dao.LeadDao leadDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.data.database.dao.CallLogDao callLogDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.data.database.dao.DialerDataDao dialerDataDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.data.database.dao.ContactDao contactDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.crm.v2.data.database.dao.SyncQueueDao syncQueueDao();
}