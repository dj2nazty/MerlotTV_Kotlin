package com.merlottv.app.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.merlottv.app.data.local.entity.StremioAddonEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StremioAddonDao_Impl implements StremioAddonDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StremioAddonEntity> __insertionAdapterOfStremioAddonEntity;

  private final EntityDeletionOrUpdateAdapter<StremioAddonEntity> __updateAdapterOfStremioAddonEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public StremioAddonDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStremioAddonEntity = new EntityInsertionAdapter<StremioAddonEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `stremio_addons` (`id`,`name`,`version`,`manifestUrl`,`typesJson`,`catalogsJson`,`isEnabled`,`installedAt`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StremioAddonEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getVersion() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getVersion());
        }
        if (entity.getManifestUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getManifestUrl());
        }
        if (entity.getTypesJson() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTypesJson());
        }
        if (entity.getCatalogsJson() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCatalogsJson());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getInstalledAt());
      }
    };
    this.__updateAdapterOfStremioAddonEntity = new EntityDeletionOrUpdateAdapter<StremioAddonEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `stremio_addons` SET `id` = ?,`name` = ?,`version` = ?,`manifestUrl` = ?,`typesJson` = ?,`catalogsJson` = ?,`isEnabled` = ?,`installedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StremioAddonEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getVersion() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getVersion());
        }
        if (entity.getManifestUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getManifestUrl());
        }
        if (entity.getTypesJson() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTypesJson());
        }
        if (entity.getCatalogsJson() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCatalogsJson());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getInstalledAt());
        if (entity.getId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM stremio_addons WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final StremioAddonEntity addon,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStremioAddonEntity.insert(addon);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final StremioAddonEntity addon,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfStremioAddonEntity.handle(addon);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, id);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<StremioAddonEntity>> getAll() {
    final String _sql = "SELECT * FROM stremio_addons ORDER BY installedAt";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stremio_addons"}, new Callable<List<StremioAddonEntity>>() {
      @Override
      @NonNull
      public List<StremioAddonEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
          final int _cursorIndexOfManifestUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "manifestUrl");
          final int _cursorIndexOfTypesJson = CursorUtil.getColumnIndexOrThrow(_cursor, "typesJson");
          final int _cursorIndexOfCatalogsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "catalogsJson");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
          final List<StremioAddonEntity> _result = new ArrayList<StremioAddonEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StremioAddonEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpVersion;
            if (_cursor.isNull(_cursorIndexOfVersion)) {
              _tmpVersion = null;
            } else {
              _tmpVersion = _cursor.getString(_cursorIndexOfVersion);
            }
            final String _tmpManifestUrl;
            if (_cursor.isNull(_cursorIndexOfManifestUrl)) {
              _tmpManifestUrl = null;
            } else {
              _tmpManifestUrl = _cursor.getString(_cursorIndexOfManifestUrl);
            }
            final String _tmpTypesJson;
            if (_cursor.isNull(_cursorIndexOfTypesJson)) {
              _tmpTypesJson = null;
            } else {
              _tmpTypesJson = _cursor.getString(_cursorIndexOfTypesJson);
            }
            final String _tmpCatalogsJson;
            if (_cursor.isNull(_cursorIndexOfCatalogsJson)) {
              _tmpCatalogsJson = null;
            } else {
              _tmpCatalogsJson = _cursor.getString(_cursorIndexOfCatalogsJson);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final long _tmpInstalledAt;
            _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
            _item = new StremioAddonEntity(_tmpId,_tmpName,_tmpVersion,_tmpManifestUrl,_tmpTypesJson,_tmpCatalogsJson,_tmpIsEnabled,_tmpInstalledAt);
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
  public Flow<List<StremioAddonEntity>> getEnabled() {
    final String _sql = "SELECT * FROM stremio_addons WHERE isEnabled = 1 ORDER BY installedAt";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stremio_addons"}, new Callable<List<StremioAddonEntity>>() {
      @Override
      @NonNull
      public List<StremioAddonEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "version");
          final int _cursorIndexOfManifestUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "manifestUrl");
          final int _cursorIndexOfTypesJson = CursorUtil.getColumnIndexOrThrow(_cursor, "typesJson");
          final int _cursorIndexOfCatalogsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "catalogsJson");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
          final List<StremioAddonEntity> _result = new ArrayList<StremioAddonEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StremioAddonEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpVersion;
            if (_cursor.isNull(_cursorIndexOfVersion)) {
              _tmpVersion = null;
            } else {
              _tmpVersion = _cursor.getString(_cursorIndexOfVersion);
            }
            final String _tmpManifestUrl;
            if (_cursor.isNull(_cursorIndexOfManifestUrl)) {
              _tmpManifestUrl = null;
            } else {
              _tmpManifestUrl = _cursor.getString(_cursorIndexOfManifestUrl);
            }
            final String _tmpTypesJson;
            if (_cursor.isNull(_cursorIndexOfTypesJson)) {
              _tmpTypesJson = null;
            } else {
              _tmpTypesJson = _cursor.getString(_cursorIndexOfTypesJson);
            }
            final String _tmpCatalogsJson;
            if (_cursor.isNull(_cursorIndexOfCatalogsJson)) {
              _tmpCatalogsJson = null;
            } else {
              _tmpCatalogsJson = _cursor.getString(_cursorIndexOfCatalogsJson);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final long _tmpInstalledAt;
            _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
            _item = new StremioAddonEntity(_tmpId,_tmpName,_tmpVersion,_tmpManifestUrl,_tmpTypesJson,_tmpCatalogsJson,_tmpIsEnabled,_tmpInstalledAt);
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
