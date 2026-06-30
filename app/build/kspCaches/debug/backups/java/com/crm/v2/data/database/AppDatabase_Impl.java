package com.crm.v2.data.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.crm.v2.data.database.dao.CallLogDao;
import com.crm.v2.data.database.dao.CallLogDao_Impl;
import com.crm.v2.data.database.dao.ContactDao;
import com.crm.v2.data.database.dao.ContactDao_Impl;
import com.crm.v2.data.database.dao.DialerDataDao;
import com.crm.v2.data.database.dao.DialerDataDao_Impl;
import com.crm.v2.data.database.dao.LeadDao;
import com.crm.v2.data.database.dao.LeadDao_Impl;
import com.crm.v2.data.database.dao.SyncQueueDao;
import com.crm.v2.data.database.dao.SyncQueueDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile LeadDao _leadDao;

  private volatile CallLogDao _callLogDao;

  private volatile DialerDataDao _dialerDataDao;

  private volatile ContactDao _contactDao;

  private volatile SyncQueueDao _syncQueueDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `leads` (`key` TEXT NOT NULL, `clientName` TEXT, `contact` TEXT, `product` TEXT, `salary` TEXT, `company` TEXT, `status` TEXT, `callerName` TEXT, `callbackDate` TEXT, `isHLLAPBL` TEXT, `isCibilBad` INTEGER NOT NULL, `isRealEstate` INTEGER NOT NULL, `remarks` TEXT, `createdDate` TEXT, `userKey` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`key`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `call_logs` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `key` TEXT, `name` TEXT, `number` TEXT, `type` TEXT, `duration` INTEGER NOT NULL, `callDate` TEXT, `userKey` TEXT, `synced` INTEGER NOT NULL, `cachedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `dialer_data` (`key` TEXT NOT NULL, `clientName` TEXT, `contact` TEXT, `product` TEXT, `campaign` TEXT, `salary` TEXT, `company` TEXT, `status` TEXT, `callbackDate` TEXT, `remarks` TEXT, `userKey` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`key`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `contacts` (`id` TEXT NOT NULL, `name` TEXT, `phoneNumbers` TEXT, `email` TEXT, `photoUri` TEXT, `cachedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `sync_queue` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `entityType` TEXT NOT NULL, `action` TEXT NOT NULL, `payload` TEXT NOT NULL, `retryCount` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '838e07b6569fd12dc2ca017227d4a5ba')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `leads`");
        db.execSQL("DROP TABLE IF EXISTS `call_logs`");
        db.execSQL("DROP TABLE IF EXISTS `dialer_data`");
        db.execSQL("DROP TABLE IF EXISTS `contacts`");
        db.execSQL("DROP TABLE IF EXISTS `sync_queue`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsLeads = new HashMap<String, TableInfo.Column>(16);
        _columnsLeads.put("key", new TableInfo.Column("key", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("clientName", new TableInfo.Column("clientName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("contact", new TableInfo.Column("contact", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("product", new TableInfo.Column("product", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("salary", new TableInfo.Column("salary", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("company", new TableInfo.Column("company", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("callerName", new TableInfo.Column("callerName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("callbackDate", new TableInfo.Column("callbackDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("isHLLAPBL", new TableInfo.Column("isHLLAPBL", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("isCibilBad", new TableInfo.Column("isCibilBad", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("isRealEstate", new TableInfo.Column("isRealEstate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("remarks", new TableInfo.Column("remarks", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("createdDate", new TableInfo.Column("createdDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("userKey", new TableInfo.Column("userKey", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLeads.put("cachedAt", new TableInfo.Column("cachedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLeads = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLeads = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLeads = new TableInfo("leads", _columnsLeads, _foreignKeysLeads, _indicesLeads);
        final TableInfo _existingLeads = TableInfo.read(db, "leads");
        if (!_infoLeads.equals(_existingLeads)) {
          return new RoomOpenHelper.ValidationResult(false, "leads(com.crm.v2.data.database.entity.LeadEntity).\n"
                  + " Expected:\n" + _infoLeads + "\n"
                  + " Found:\n" + _existingLeads);
        }
        final HashMap<String, TableInfo.Column> _columnsCallLogs = new HashMap<String, TableInfo.Column>(10);
        _columnsCallLogs.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCallLogs.put("key", new TableInfo.Column("key", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCallLogs.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCallLogs.put("number", new TableInfo.Column("number", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCallLogs.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCallLogs.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCallLogs.put("callDate", new TableInfo.Column("callDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCallLogs.put("userKey", new TableInfo.Column("userKey", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCallLogs.put("synced", new TableInfo.Column("synced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCallLogs.put("cachedAt", new TableInfo.Column("cachedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCallLogs = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCallLogs = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCallLogs = new TableInfo("call_logs", _columnsCallLogs, _foreignKeysCallLogs, _indicesCallLogs);
        final TableInfo _existingCallLogs = TableInfo.read(db, "call_logs");
        if (!_infoCallLogs.equals(_existingCallLogs)) {
          return new RoomOpenHelper.ValidationResult(false, "call_logs(com.crm.v2.data.database.entity.CallLogEntity).\n"
                  + " Expected:\n" + _infoCallLogs + "\n"
                  + " Found:\n" + _existingCallLogs);
        }
        final HashMap<String, TableInfo.Column> _columnsDialerData = new HashMap<String, TableInfo.Column>(12);
        _columnsDialerData.put("key", new TableInfo.Column("key", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("clientName", new TableInfo.Column("clientName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("contact", new TableInfo.Column("contact", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("product", new TableInfo.Column("product", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("campaign", new TableInfo.Column("campaign", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("salary", new TableInfo.Column("salary", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("company", new TableInfo.Column("company", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("status", new TableInfo.Column("status", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("callbackDate", new TableInfo.Column("callbackDate", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("remarks", new TableInfo.Column("remarks", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("userKey", new TableInfo.Column("userKey", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDialerData.put("cachedAt", new TableInfo.Column("cachedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDialerData = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDialerData = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDialerData = new TableInfo("dialer_data", _columnsDialerData, _foreignKeysDialerData, _indicesDialerData);
        final TableInfo _existingDialerData = TableInfo.read(db, "dialer_data");
        if (!_infoDialerData.equals(_existingDialerData)) {
          return new RoomOpenHelper.ValidationResult(false, "dialer_data(com.crm.v2.data.database.entity.DialerDataEntity).\n"
                  + " Expected:\n" + _infoDialerData + "\n"
                  + " Found:\n" + _existingDialerData);
        }
        final HashMap<String, TableInfo.Column> _columnsContacts = new HashMap<String, TableInfo.Column>(6);
        _columnsContacts.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("phoneNumbers", new TableInfo.Column("phoneNumbers", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("email", new TableInfo.Column("email", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("photoUri", new TableInfo.Column("photoUri", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsContacts.put("cachedAt", new TableInfo.Column("cachedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysContacts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesContacts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoContacts = new TableInfo("contacts", _columnsContacts, _foreignKeysContacts, _indicesContacts);
        final TableInfo _existingContacts = TableInfo.read(db, "contacts");
        if (!_infoContacts.equals(_existingContacts)) {
          return new RoomOpenHelper.ValidationResult(false, "contacts(com.crm.v2.data.database.entity.ContactEntity).\n"
                  + " Expected:\n" + _infoContacts + "\n"
                  + " Found:\n" + _existingContacts);
        }
        final HashMap<String, TableInfo.Column> _columnsSyncQueue = new HashMap<String, TableInfo.Column>(6);
        _columnsSyncQueue.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSyncQueue.put("entityType", new TableInfo.Column("entityType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSyncQueue.put("action", new TableInfo.Column("action", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSyncQueue.put("payload", new TableInfo.Column("payload", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSyncQueue.put("retryCount", new TableInfo.Column("retryCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSyncQueue.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSyncQueue = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSyncQueue = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSyncQueue = new TableInfo("sync_queue", _columnsSyncQueue, _foreignKeysSyncQueue, _indicesSyncQueue);
        final TableInfo _existingSyncQueue = TableInfo.read(db, "sync_queue");
        if (!_infoSyncQueue.equals(_existingSyncQueue)) {
          return new RoomOpenHelper.ValidationResult(false, "sync_queue(com.crm.v2.data.database.entity.SyncQueueEntity).\n"
                  + " Expected:\n" + _infoSyncQueue + "\n"
                  + " Found:\n" + _existingSyncQueue);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "838e07b6569fd12dc2ca017227d4a5ba", "ac15a88ec4be353d3cbd5acce2ed3fc2");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "leads","call_logs","dialer_data","contacts","sync_queue");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `leads`");
      _db.execSQL("DELETE FROM `call_logs`");
      _db.execSQL("DELETE FROM `dialer_data`");
      _db.execSQL("DELETE FROM `contacts`");
      _db.execSQL("DELETE FROM `sync_queue`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(LeadDao.class, LeadDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CallLogDao.class, CallLogDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DialerDataDao.class, DialerDataDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ContactDao.class, ContactDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SyncQueueDao.class, SyncQueueDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public LeadDao leadDao() {
    if (_leadDao != null) {
      return _leadDao;
    } else {
      synchronized(this) {
        if(_leadDao == null) {
          _leadDao = new LeadDao_Impl(this);
        }
        return _leadDao;
      }
    }
  }

  @Override
  public CallLogDao callLogDao() {
    if (_callLogDao != null) {
      return _callLogDao;
    } else {
      synchronized(this) {
        if(_callLogDao == null) {
          _callLogDao = new CallLogDao_Impl(this);
        }
        return _callLogDao;
      }
    }
  }

  @Override
  public DialerDataDao dialerDataDao() {
    if (_dialerDataDao != null) {
      return _dialerDataDao;
    } else {
      synchronized(this) {
        if(_dialerDataDao == null) {
          _dialerDataDao = new DialerDataDao_Impl(this);
        }
        return _dialerDataDao;
      }
    }
  }

  @Override
  public ContactDao contactDao() {
    if (_contactDao != null) {
      return _contactDao;
    } else {
      synchronized(this) {
        if(_contactDao == null) {
          _contactDao = new ContactDao_Impl(this);
        }
        return _contactDao;
      }
    }
  }

  @Override
  public SyncQueueDao syncQueueDao() {
    if (_syncQueueDao != null) {
      return _syncQueueDao;
    } else {
      synchronized(this) {
        if(_syncQueueDao == null) {
          _syncQueueDao = new SyncQueueDao_Impl(this);
        }
        return _syncQueueDao;
      }
    }
  }
}
