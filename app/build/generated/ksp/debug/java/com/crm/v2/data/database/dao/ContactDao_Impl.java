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
import com.crm.v2.data.database.entity.ContactEntity;
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
public final class ContactDao_Impl implements ContactDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ContactEntity> __insertionAdapterOfContactEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ContactDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContactEntity = new EntityInsertionAdapter<ContactEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `contacts` (`id`,`name`,`phoneNumbers`,`email`,`photoUri`,`cachedAt`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ContactEntity entity) {
        statement.bindString(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getPhoneNumbers() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPhoneNumbers());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getEmail());
        }
        if (entity.getPhotoUri() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPhotoUri());
        }
        statement.bindLong(6, entity.getCachedAt());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM contacts";
        return _query;
      }
    };
  }

  @Override
  public Object insertContacts(final List<ContactEntity> contacts,
      final Continuation<List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfContactEntity.insertAndReturnIdsList(contacts);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
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
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ContactEntity>> getAllContacts() {
    final String _sql = "SELECT * FROM contacts ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"contacts"}, new Callable<List<ContactEntity>>() {
      @Override
      @NonNull
      public List<ContactEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhoneNumbers = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumbers");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "photoUri");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<ContactEntity> _result = new ArrayList<ContactEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ContactEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPhoneNumbers;
            if (_cursor.isNull(_cursorIndexOfPhoneNumbers)) {
              _tmpPhoneNumbers = null;
            } else {
              _tmpPhoneNumbers = _cursor.getString(_cursorIndexOfPhoneNumbers);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPhotoUri;
            if (_cursor.isNull(_cursorIndexOfPhotoUri)) {
              _tmpPhotoUri = null;
            } else {
              _tmpPhotoUri = _cursor.getString(_cursorIndexOfPhotoUri);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new ContactEntity(_tmpId,_tmpName,_tmpPhoneNumbers,_tmpEmail,_tmpPhotoUri,_tmpCachedAt);
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
  public Flow<List<ContactEntity>> searchContacts(final String query) {
    final String _sql = "SELECT * FROM contacts WHERE name LIKE '%' || ? || '%' OR phoneNumbers LIKE '%' || ? || '%'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"contacts"}, new Callable<List<ContactEntity>>() {
      @Override
      @NonNull
      public List<ContactEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfPhoneNumbers = CursorUtil.getColumnIndexOrThrow(_cursor, "phoneNumbers");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPhotoUri = CursorUtil.getColumnIndexOrThrow(_cursor, "photoUri");
          final int _cursorIndexOfCachedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "cachedAt");
          final List<ContactEntity> _result = new ArrayList<ContactEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ContactEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpPhoneNumbers;
            if (_cursor.isNull(_cursorIndexOfPhoneNumbers)) {
              _tmpPhoneNumbers = null;
            } else {
              _tmpPhoneNumbers = _cursor.getString(_cursorIndexOfPhoneNumbers);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPhotoUri;
            if (_cursor.isNull(_cursorIndexOfPhotoUri)) {
              _tmpPhotoUri = null;
            } else {
              _tmpPhotoUri = _cursor.getString(_cursorIndexOfPhotoUri);
            }
            final long _tmpCachedAt;
            _tmpCachedAt = _cursor.getLong(_cursorIndexOfCachedAt);
            _item = new ContactEntity(_tmpId,_tmpName,_tmpPhoneNumbers,_tmpEmail,_tmpPhotoUri,_tmpCachedAt);
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
