package com.crm.v2.data.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.crm.v2.data.database.entity.LeadEntity;
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
public final class LeadDao_Impl implements LeadDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<LeadEntity> __insertionAdapterOfLeadEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteLeadsForUser;

  public LeadDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfLeadEntity = new EntityInsertionAdapter<LeadEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `leads` (`key`,`clientName`,`contact`,`product`,`salary`,`company`,`status`,`callerName`,`callbackDate`,`isHLLAPBL`,`isCibilBad`,`isRealEstate`,`remarks`,`createdDate`,`userKey`,`cachedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final LeadEntity entity) {
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
        if (entity.getSalary() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getSalary());
        }
        if (entity.getCompany() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCompany());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getStatus());
        }
        if (entity.getCallerName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getCallerName());
        }
        if (entity.getCallbackDate() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCallbackDate());
        }
        if (entity.isHLLAPBL() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.isHLLAPBL());
        }
        final int _tmp = entity.isCibilBad() ? 1 : 0;
        statement.bindLong(11, _tmp);
        final int _tmp_1 = entity.isRealEstate() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
        if (entity.getRemarks() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getRemarks());
        }
        if (entity.getCreatedDate() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getCreatedDate());
        }
        if (entity.getUserKey() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getUserKey());
        }
        statement.bindLong(16, entity.getCachedAt());
      }
    };
    this.__preparedStmtOfDeleteLeadsForUser = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM leads WHERE userKey = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertLeads(final List<LeadEntity> leads,
      final Continuation<List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfLeadEntity.insertAndReturnIdsList(leads);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertLead(final LeadEntity lead, final Continuation<Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfLeadEntity.insertAndReturnId(lead);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLeadsForUser(final String userKey, final Continuation<Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteLeadsForUser.acquire();
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
          __preparedStmtOfDeleteLeadsForUser.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<LeadEntity>> getLeads(final String userKey) {
    final String _sql = "SELECT * FROM leads WHERE userKey = ? ORDER BY cachedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userKey);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"leads"}, new Callable<List<LeadEntity>>() {
      @Override
      @NonNull
      public List<LeadEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfClientName = CursorUtil.getColumnIndexOrThrow(_cursor, "clientName");
          final int _cursorIndexOfContact = CursorUtil.getColumnIndexOrThrow(_cursor, "contact");
          final int _cursorIndexOfProduct = CursorUtil.getColumnIndexOrThrow(_cursor, "product");
          final int _cursorIndexOfSalary = CursorUtil.getColumnIndexOrThrow(_cursor, "salary");
          final int _cursorIndexOfCompany = CursorUtil.getColumnIndexOrThrow(_cursor, "company");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCallerName = CursorUtil.getColumnIndexOrThrow(_cursor, "callerName");
          final int _cursorIndexOfCallbackDate = CursorUtil.getColumnIndexOrThrow(_cursor, "callbackDate");
          final int _cursorIndexOfIsHLLAPBL = CursorUtil.getColumnIndexOrThrow(_cursor, "isHLLAPBL");
          final int _cursorIndexOfIsCibilBad = CursorUtil.getColumnIndexOrThrow(_cursor, "isCibilBad");
          final int _cursorIndexOfIsRealEstate = CursorUtil.getColumnIndexOrThrow(_cursor, "isRealEstate");
          final int _cursorIndexOfRemarks = CursorUtil.getColumnIndexOrThrow(_cursor, "remarks");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final int _cursorIndexOfUserKey = CursorUtil.getColumnIndexOrThrow(_cursor, "userKey");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<LeadEntity> _result = new ArrayList<LeadEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final LeadEntity _item;
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
            final String _tmpCallerName;
            if (_cursor.isNull(_cursorIndexOfCallerName)) {
              _tmpCallerName = null;
            } else {
              _tmpCallerName = _cursor.getString(_cursorIndexOfCallerName);
            }
            final String _tmpCallbackDate;
            if (_cursor.isNull(_cursorIndexOfCallbackDate)) {
              _tmpCallbackDate = null;
            } else {
              _tmpCallbackDate = _cursor.getString(_cursorIndexOfCallbackDate);
            }
            final String _tmpIsHLLAPBL;
            if (_cursor.isNull(_cursorIndexOfIsHLLAPBL)) {
              _tmpIsHLLAPBL = null;
            } else {
              _tmpIsHLLAPBL = _cursor.getString(_cursorIndexOfIsHLLAPBL);
            }
            final boolean _tmpIsCibilBad;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCibilBad);
            _tmpIsCibilBad = _tmp != 0;
            final boolean _tmpIsRealEstate;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsRealEstate);
            _tmpIsRealEstate = _tmp_1 != 0;
            final String _tmpRemarks;
            if (_cursor.isNull(_cursorIndexOfRemarks)) {
              _tmpRemarks = null;
            } else {
              _tmpRemarks = _cursor.getString(_cursorIndexOfRemarks);
            }
            final String _tmpCreatedDate;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmpCreatedDate = null;
            } else {
              _tmpCreatedDate = _cursor.getString(_cursorIndexOfCreatedDate);
            }
            final String _tmpUserKey;
            if (_cursor.isNull(_cursorIndexOfUserKey)) {
              _tmpUserKey = null;
            } else {
              _tmpUserKey = _cursor.getString(_cursorIndexOfUserKey);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new LeadEntity(_tmpKey,_tmpClientName,_tmpContact,_tmpProduct,_tmpSalary,_tmpCompany,_tmpStatus,_tmpCallerName,_tmpCallbackDate,_tmpIsHLLAPBL,_tmpIsCibilBad,_tmpIsRealEstate,_tmpRemarks,_tmpCreatedDate,_tmpUserKey,_tmpCachedAt);
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
  public Object getLeadByKey(final String key, final Continuation<LeadEntity> $completion) {
    final String _sql = "SELECT * FROM leads WHERE key = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, key);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<LeadEntity>() {
      @Override
      @Nullable
      public LeadEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfKey = CursorUtil.getColumnIndexOrThrow(_cursor, "key");
          final int _cursorIndexOfClientName = CursorUtil.getColumnIndexOrThrow(_cursor, "clientName");
          final int _cursorIndexOfContact = CursorUtil.getColumnIndexOrThrow(_cursor, "contact");
          final int _cursorIndexOfProduct = CursorUtil.getColumnIndexOrThrow(_cursor, "product");
          final int _cursorIndexOfSalary = CursorUtil.getColumnIndexOrThrow(_cursor, "salary");
          final int _cursorIndexOfCompany = CursorUtil.getColumnIndexOrThrow(_cursor, "company");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfCallerName = CursorUtil.getColumnIndexOrThrow(_cursor, "callerName");
          final int _cursorIndexOfCallbackDate = CursorUtil.getColumnIndexOrThrow(_cursor, "callbackDate");
          final int _cursorIndexOfIsHLLAPBL = CursorUtil.getColumnIndexOrThrow(_cursor, "isHLLAPBL");
          final int _cursorIndexOfIsCibilBad = CursorUtil.getColumnIndexOrThrow(_cursor, "isCibilBad");
          final int _cursorIndexOfIsRealEstate = CursorUtil.getColumnIndexOrThrow(_cursor, "isRealEstate");
          final int _cursorIndexOfRemarks = CursorUtil.getColumnIndexOrThrow(_cursor, "remarks");
          final int _cursorIndexOfCreatedDate = CursorUtil.getColumnIndexOrThrow(_cursor, "createdDate");
          final int _cursorIndexOfUserKey = CursorUtil.getColumnIndexOrThrow(_cursor, "userKey");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final LeadEntity _result;
          if (_cursor.moveToFirst()) {
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
            final String _tmpCallerName;
            if (_cursor.isNull(_cursorIndexOfCallerName)) {
              _tmpCallerName = null;
            } else {
              _tmpCallerName = _cursor.getString(_cursorIndexOfCallerName);
            }
            final String _tmpCallbackDate;
            if (_cursor.isNull(_cursorIndexOfCallbackDate)) {
              _tmpCallbackDate = null;
            } else {
              _tmpCallbackDate = _cursor.getString(_cursorIndexOfCallbackDate);
            }
            final String _tmpIsHLLAPBL;
            if (_cursor.isNull(_cursorIndexOfIsHLLAPBL)) {
              _tmpIsHLLAPBL = null;
            } else {
              _tmpIsHLLAPBL = _cursor.getString(_cursorIndexOfIsHLLAPBL);
            }
            final boolean _tmpIsCibilBad;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCibilBad);
            _tmpIsCibilBad = _tmp != 0;
            final boolean _tmpIsRealEstate;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsRealEstate);
            _tmpIsRealEstate = _tmp_1 != 0;
            final String _tmpRemarks;
            if (_cursor.isNull(_cursorIndexOfRemarks)) {
              _tmpRemarks = null;
            } else {
              _tmpRemarks = _cursor.getString(_cursorIndexOfRemarks);
            }
            final String _tmpCreatedDate;
            if (_cursor.isNull(_cursorIndexOfCreatedDate)) {
              _tmpCreatedDate = null;
            } else {
              _tmpCreatedDate = _cursor.getString(_cursorIndexOfCreatedDate);
            }
            final String _tmpUserKey;
            if (_cursor.isNull(_cursorIndexOfUserKey)) {
              _tmpUserKey = null;
            } else {
              _tmpUserKey = _cursor.getString(_cursorIndexOfUserKey);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _result = new LeadEntity(_tmpKey,_tmpClientName,_tmpContact,_tmpProduct,_tmpSalary,_tmpCompany,_tmpStatus,_tmpCallerName,_tmpCallbackDate,_tmpIsHLLAPBL,_tmpIsCibilBad,_tmpIsRealEstate,_tmpRemarks,_tmpCreatedDate,_tmpUserKey,_tmpCachedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
