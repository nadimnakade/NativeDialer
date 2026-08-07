package com.crm.v2.data.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.crm.v2.data.database.entity.CallLogEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
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
public final class CallLogDao_Impl implements CallLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CallLogEntity> __insertionAdapterOfCallLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldSyncedLogs;

  public CallLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCallLogEntity = new EntityInsertionAdapter<CallLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `call_logs` (`id`,`key`,`name`,`number`,`type`,`duration`,`callDate`,`userKey`,`synced`,`cachedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CallLogEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getKey() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getKey());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getNumber());
        }
        if (entity.getType() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getType());
        }
        statement.bindLong(6, entity.getDuration());
        if (entity.getCallDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCallDate());
        }
        if (entity.getUserKey() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getUserKey());
        }
        final int _tmp = entity.getSynced() ? 1 : 0;
        statement.bindLong(9, _tmp);
        statement.bindLong(10, entity.getCachedAt());
      }
    };
    this.__preparedStmtOfDeleteOldSyncedLogs = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM call_logs WHERE synced = 1 AND cachedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertCallLogs(final List<CallLogEntity> logs,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfCallLogEntity.insertAndReturnIdsList(logs);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCallLog(final CallLogEntity log,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCallLogEntity.insertAndReturnId(log);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldSyncedLogs(final long before,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldSyncedLogs.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, before);
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
          __preparedStmtOfDeleteOldSyncedLogs.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CallLogEntity>> getCallLogs(final String userKey, final int limit) {
    final String _sql = "SELECT * FROM call_logs WHERE userKey = ? ORDER BY callDate DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userKey == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userKey);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"call_logs"}, new Callable<List<CallLogEntity>>() {
      @Override
      @NonNull
      public List<CallLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfCallDate = CursorUtil.getColumnIndexOrThrow(_cursor, "callDate");
          final int _cursorIndexOfUserKey = CursorUtil.getColumnIndexOrThrow(_cursor, "userKey");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<CallLogEntity> _result = new ArrayList<CallLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CallLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpKey;
            if (_cursor.isNull(_cursorIndexOfKey)) {
              _tmpKey = null;
            } else {
              _tmpKey = _cursor.getString(_cursorIndexOfKey);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpNumber;
            if (_cursor.isNull(_cursorIndexOfNumber)) {
              _tmpNumber = null;
            } else {
              _tmpNumber = _cursor.getString(_cursorIndexOfNumber);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final int _tmpDuration;
            _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            final String _tmpCallDate;
            if (_cursor.isNull(_cursorIndexOfCallDate)) {
              _tmpCallDate = null;
            } else {
              _tmpCallDate = _cursor.getString(_cursorIndexOfCallDate);
            }
            final String _tmpUserKey;
            if (_cursor.isNull(_cursorIndexOfUserKey)) {
              _tmpUserKey = null;
            } else {
              _tmpUserKey = _cursor.getString(_cursorIndexOfUserKey);
            }
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new CallLogEntity(_tmpId,_tmpKey,_tmpName,_tmpNumber,_tmpType,_tmpDuration,_tmpCallDate,_tmpUserKey,_tmpSynced,_tmpCachedAt);
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

  @Override
  public Object getUnsyncedCallLogs(final Continuation<? super List<CallLogEntity>> $completion) {
    final String _sql = "SELECT * FROM call_logs WHERE synced = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CallLogEntity>>() {
      @Override
      @NonNull
      public List<CallLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfCallDate = CursorUtil.getColumnIndexOrThrow(_cursor, "callDate");
          final int _cursorIndexOfUserKey = CursorUtil.getColumnIndexOrThrow(_cursor, "userKey");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<CallLogEntity> _result = new ArrayList<CallLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CallLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpKey;
            if (_cursor.isNull(_cursorIndexOfKey)) {
              _tmpKey = null;
            } else {
              _tmpKey = _cursor.getString(_cursorIndexOfKey);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpNumber;
            if (_cursor.isNull(_cursorIndexOfNumber)) {
              _tmpNumber = null;
            } else {
              _tmpNumber = _cursor.getString(_cursorIndexOfNumber);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final int _tmpDuration;
            _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            final String _tmpCallDate;
            if (_cursor.isNull(_cursorIndexOfCallDate)) {
              _tmpCallDate = null;
            } else {
              _tmpCallDate = _cursor.getString(_cursorIndexOfCallDate);
            }
            final String _tmpUserKey;
            if (_cursor.isNull(_cursorIndexOfUserKey)) {
              _tmpUserKey = null;
            } else {
              _tmpUserKey = _cursor.getString(_cursorIndexOfUserKey);
            }
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new CallLogEntity(_tmpId,_tmpKey,_tmpName,_tmpNumber,_tmpType,_tmpDuration,_tmpCallDate,_tmpUserKey,_tmpSynced,_tmpCachedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsSynced(final List<Long> ids,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("UPDATE call_logs SET synced = 1 WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (Long _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindLong(_argIndex, _item);
          }
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          final Integer _result = _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
