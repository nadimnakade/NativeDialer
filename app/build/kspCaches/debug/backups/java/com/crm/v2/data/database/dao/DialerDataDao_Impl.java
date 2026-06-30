package com.crm.v2.data.database.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.crm.v2.data.database.entity.DialerDataEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DialerDataDao_Impl implements DialerDataDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DialerDataEntity> __insertionAdapterOfDialerDataEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteForUser;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByKey;

  public DialerDataDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDialerDataEntity = new EntityInsertionAdapter<DialerDataEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `dialer_data` (`key`,`clientName`,`contact`,`product`,`campaign`,`salary`,`company`,`status`,`callbackDate`,`remarks`,`userKey`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DialerDataEntity entity) {
        statement.bindString(1, entity.getKey());
        if (entity.getClientName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getClientName());
        }
        if (entity.getContact() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getContact());
        }
        if (entity.getProduct() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getProduct());
        }
        if (entity.getCampaign() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCampaign());
        }
        if (entity.getSalary() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSalary());
        }
        if (entity.getCompany() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCompany());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getStatus());
        }
        if (entity.getCallbackDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCallbackDate());
        }
        if (entity.getRemarks() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getRemarks());
        }
        if (entity.getUserKey() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getUserKey());
        }
        statement.bindLong(12, entity.getCachedAt());
      }
    };
    this.__preparedStmtOfDeleteForUser = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM dialer_data WHERE userKey = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteByKey = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM dialer_data WHERE key = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertDialerData(final List<DialerDataEntity> data,
      final Continuation<List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfDialerDataEntity.insertAndReturnIdsList(data);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteForUser(final String userKey, final Continuation<Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteForUser.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, userKey);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteForUser.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteByKey(final String key, final Continuation<Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByKey.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, key);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteByKey.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<DialerDataEntity>> getDialerData(final String userKey) {
    final String _sql = "SELECT * FROM dialer_data WHERE userKey = ? ORDER BY cachedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userKey);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"dialer_data"}, new Callable<List<DialerDataEntity>>() {
      @Override
      @NonNull
      public List<DialerDataEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfClientName = CursorUtil.getColumnIndexOrThrow(_cursor, "clientName");
          final int _cursorIndexOfContact = CursorUtil.getColumnIndexOrThrow(_cursor, "contact");
          final int _cursorIndexOfProduct = CursorUtil.getColumnIndexOrThrow(_cursor, "product");
          final int _cursorIndexOfCampaign = CursorUtil.getColumnIndexOrThrow(_cursor, "campaign");
          final int _cursorIndexOfSalary = CursorUtil.getColumnIndexOrThrow(_cursor, "salary");
          final int _cursorIndexOfCompany = CursorUtil.getColumnIndexOrThrow(_cursor, "company");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCallbackDate = CursorUtil.getColumnIndexOrThrow(_cursor, "callbackDate");
          final int _cursorIndexOfRemarks = CursorUtil.getColumnIndexOrThrow(_cursor, "remarks");
          final int _cursorIndexOfUserKey = CursorUtil.getColumnIndexOrThrow(_cursor, "userKey");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<DialerDataEntity> _result = new ArrayList<DialerDataEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DialerDataEntity _item;
            final String _tmpKey;
            _tmpKey = _cursor.getString(_cursorIndexOfKey);
            final String _tmpClientName;
            if (_cursor.isNull(_cursorIndexOfClientName)) {
              _tmpClientName = null;
            } else {
              _tmpClientName = _cursor.getString(_cursorIndexOfClientName);
            }
            final String _tmpContact;
            if (_cursor.isNull(_cursorIndexOfContact)) {
              _tmpContact = null;
            } else {
              _tmpContact = _cursor.getString(_cursorIndexOfContact);
            }
            final String _tmpProduct;
            if (_cursor.isNull(_cursorIndexOfProduct)) {
              _tmpProduct = null;
            } else {
              _tmpProduct = _cursor.getString(_cursorIndexOfProduct);
            }
            final String _tmpCampaign;
            if (_cursor.isNull(_cursorIndexOfCampaign)) {
              _tmpCampaign = null;
            } else {
              _tmpCampaign = _cursor.getString(_cursorIndexOfCampaign);
            }
            final String _tmpSalary;
            if (_cursor.isNull(_cursorIndexOfSalary)) {
              _tmpSalary = null;
            } else {
              _tmpSalary = _cursor.getString(_cursorIndexOfSalary);
            }
            final String _tmpCompany;
            if (_cursor.isNull(_cursorIndexOfCompany)) {
              _tmpCompany = null;
            } else {
              _tmpCompany = _cursor.getString(_cursorIndexOfCompany);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpCallbackDate;
            if (_cursor.isNull(_cursorIndexOfCallbackDate)) {
              _tmpCallbackDate = null;
            } else {
              _tmpCallbackDate = _cursor.getString(_cursorIndexOfCallbackDate);
            }
            final String _tmpRemarks;
            if (_cursor.isNull(_cursorIndexOfRemarks)) {
              _tmpRemarks = null;
            } else {
              _tmpRemarks = _cursor.getString(_cursorIndexOfRemarks);
            }
            final String _tmpUserKey;
            if (_cursor.isNull(_cursorIndexOfUserKey)) {
              _tmpUserKey = null;
            } else {
              _tmpUserKey = _cursor.getString(_cursorIndexOfUserKey);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new DialerDataEntity(_tmpKey,_tmpClientName,_tmpContact,_tmpProduct,_tmpCampaign,_tmpSalary,_tmpCompany,_tmpStatus,_tmpCallbackDate,_tmpRemarks,_tmpUserKey,_tmpCachedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
