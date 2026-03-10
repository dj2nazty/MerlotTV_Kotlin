package com.merlottv.app.data.local.dao;

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
import com.merlottv.app.data.local.entity.ChannelEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class ChannelDao_Impl implements ChannelDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ChannelEntity> __insertionAdapterOfChannelEntity;

  private final SharedSQLiteStatement __preparedStmtOfToggleFavourite;

  private final SharedSQLiteStatement __preparedStmtOfDeleteBySource;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ChannelDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChannelEntity = new EntityInsertionAdapter<ChannelEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `channels` (`id`,`sourceId`,`name`,`url`,`logoUrl`,`group`,`tvgId`,`tvgName`,`isRadio`,`isFavourite`,`userAgent`,`referrer`,`sortOrder`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ChannelEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        statement.bindLong(2, entity.getSourceId());
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getUrl() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getUrl());
        }
        if (entity.getLogoUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getLogoUrl());
        }
        if (entity.getGroup() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getGroup());
        }
        if (entity.getTvgId() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getTvgId());
        }
        if (entity.getTvgName() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getTvgName());
        }
        final int _tmp = entity.isRadio() ? 1 : 0;
        statement.bindLong(9, _tmp);
        final int _tmp_1 = entity.isFavourite() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        if (entity.getUserAgent() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getUserAgent());
        }
        if (entity.getReferrer() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getReferrer());
        }
        statement.bindLong(13, entity.getSortOrder());
      }
    };
    this.__preparedStmtOfToggleFavourite = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE channels SET isFavourite = NOT isFavourite WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteBySource = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM channels WHERE sourceId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM channels";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<ChannelEntity> channels,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfChannelEntity.insert(channels);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object toggleFavourite(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfToggleFavourite.acquire();
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
          __preparedStmtOfToggleFavourite.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteBySource(final long sourceId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteBySource.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, sourceId);
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
          __preparedStmtOfDeleteBySource.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
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
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ChannelEntity>> getAllChannels() {
    final String _sql = "SELECT * FROM channels ORDER BY `group`, sortOrder, name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"channels"}, new Callable<List<ChannelEntity>>() {
      @Override
      @NonNull
      public List<ChannelEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfLogoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "logoUrl");
          final int _cursorIndexOfGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "group");
          final int _cursorIndexOfTvgId = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgId");
          final int _cursorIndexOfTvgName = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgName");
          final int _cursorIndexOfIsRadio = CursorUtil.getColumnIndexOrThrow(_cursor, "isRadio");
          final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavourite");
          final int _cursorIndexOfUserAgent = CursorUtil.getColumnIndexOrThrow(_cursor, "userAgent");
          final int _cursorIndexOfReferrer = CursorUtil.getColumnIndexOrThrow(_cursor, "referrer");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final List<ChannelEntity> _result = new ArrayList<ChannelEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChannelEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpSourceId;
            _tmpSourceId = _cursor.getLong(_cursorIndexOfSourceId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpLogoUrl;
            if (_cursor.isNull(_cursorIndexOfLogoUrl)) {
              _tmpLogoUrl = null;
            } else {
              _tmpLogoUrl = _cursor.getString(_cursorIndexOfLogoUrl);
            }
            final String _tmpGroup;
            if (_cursor.isNull(_cursorIndexOfGroup)) {
              _tmpGroup = null;
            } else {
              _tmpGroup = _cursor.getString(_cursorIndexOfGroup);
            }
            final String _tmpTvgId;
            if (_cursor.isNull(_cursorIndexOfTvgId)) {
              _tmpTvgId = null;
            } else {
              _tmpTvgId = _cursor.getString(_cursorIndexOfTvgId);
            }
            final String _tmpTvgName;
            if (_cursor.isNull(_cursorIndexOfTvgName)) {
              _tmpTvgName = null;
            } else {
              _tmpTvgName = _cursor.getString(_cursorIndexOfTvgName);
            }
            final boolean _tmpIsRadio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRadio);
            _tmpIsRadio = _tmp != 0;
            final boolean _tmpIsFavourite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavourite);
            _tmpIsFavourite = _tmp_1 != 0;
            final String _tmpUserAgent;
            if (_cursor.isNull(_cursorIndexOfUserAgent)) {
              _tmpUserAgent = null;
            } else {
              _tmpUserAgent = _cursor.getString(_cursorIndexOfUserAgent);
            }
            final String _tmpReferrer;
            if (_cursor.isNull(_cursorIndexOfReferrer)) {
              _tmpReferrer = null;
            } else {
              _tmpReferrer = _cursor.getString(_cursorIndexOfReferrer);
            }
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _item = new ChannelEntity(_tmpId,_tmpSourceId,_tmpName,_tmpUrl,_tmpLogoUrl,_tmpGroup,_tmpTvgId,_tmpTvgName,_tmpIsRadio,_tmpIsFavourite,_tmpUserAgent,_tmpReferrer,_tmpSortOrder);
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
  public Flow<List<ChannelEntity>> getFavouriteChannels() {
    final String _sql = "SELECT * FROM channels WHERE isFavourite = 1 ORDER BY name";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"channels"}, new Callable<List<ChannelEntity>>() {
      @Override
      @NonNull
      public List<ChannelEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfLogoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "logoUrl");
          final int _cursorIndexOfGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "group");
          final int _cursorIndexOfTvgId = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgId");
          final int _cursorIndexOfTvgName = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgName");
          final int _cursorIndexOfIsRadio = CursorUtil.getColumnIndexOrThrow(_cursor, "isRadio");
          final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavourite");
          final int _cursorIndexOfUserAgent = CursorUtil.getColumnIndexOrThrow(_cursor, "userAgent");
          final int _cursorIndexOfReferrer = CursorUtil.getColumnIndexOrThrow(_cursor, "referrer");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final List<ChannelEntity> _result = new ArrayList<ChannelEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChannelEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpSourceId;
            _tmpSourceId = _cursor.getLong(_cursorIndexOfSourceId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpLogoUrl;
            if (_cursor.isNull(_cursorIndexOfLogoUrl)) {
              _tmpLogoUrl = null;
            } else {
              _tmpLogoUrl = _cursor.getString(_cursorIndexOfLogoUrl);
            }
            final String _tmpGroup;
            if (_cursor.isNull(_cursorIndexOfGroup)) {
              _tmpGroup = null;
            } else {
              _tmpGroup = _cursor.getString(_cursorIndexOfGroup);
            }
            final String _tmpTvgId;
            if (_cursor.isNull(_cursorIndexOfTvgId)) {
              _tmpTvgId = null;
            } else {
              _tmpTvgId = _cursor.getString(_cursorIndexOfTvgId);
            }
            final String _tmpTvgName;
            if (_cursor.isNull(_cursorIndexOfTvgName)) {
              _tmpTvgName = null;
            } else {
              _tmpTvgName = _cursor.getString(_cursorIndexOfTvgName);
            }
            final boolean _tmpIsRadio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRadio);
            _tmpIsRadio = _tmp != 0;
            final boolean _tmpIsFavourite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavourite);
            _tmpIsFavourite = _tmp_1 != 0;
            final String _tmpUserAgent;
            if (_cursor.isNull(_cursorIndexOfUserAgent)) {
              _tmpUserAgent = null;
            } else {
              _tmpUserAgent = _cursor.getString(_cursorIndexOfUserAgent);
            }
            final String _tmpReferrer;
            if (_cursor.isNull(_cursorIndexOfReferrer)) {
              _tmpReferrer = null;
            } else {
              _tmpReferrer = _cursor.getString(_cursorIndexOfReferrer);
            }
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _item = new ChannelEntity(_tmpId,_tmpSourceId,_tmpName,_tmpUrl,_tmpLogoUrl,_tmpGroup,_tmpTvgId,_tmpTvgName,_tmpIsRadio,_tmpIsFavourite,_tmpUserAgent,_tmpReferrer,_tmpSortOrder);
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
  public Object getChannel(final String id, final Continuation<? super ChannelEntity> $completion) {
    final String _sql = "SELECT * FROM channels WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ChannelEntity>() {
      @Override
      @Nullable
      public ChannelEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfLogoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "logoUrl");
          final int _cursorIndexOfGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "group");
          final int _cursorIndexOfTvgId = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgId");
          final int _cursorIndexOfTvgName = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgName");
          final int _cursorIndexOfIsRadio = CursorUtil.getColumnIndexOrThrow(_cursor, "isRadio");
          final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavourite");
          final int _cursorIndexOfUserAgent = CursorUtil.getColumnIndexOrThrow(_cursor, "userAgent");
          final int _cursorIndexOfReferrer = CursorUtil.getColumnIndexOrThrow(_cursor, "referrer");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final ChannelEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpSourceId;
            _tmpSourceId = _cursor.getLong(_cursorIndexOfSourceId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpLogoUrl;
            if (_cursor.isNull(_cursorIndexOfLogoUrl)) {
              _tmpLogoUrl = null;
            } else {
              _tmpLogoUrl = _cursor.getString(_cursorIndexOfLogoUrl);
            }
            final String _tmpGroup;
            if (_cursor.isNull(_cursorIndexOfGroup)) {
              _tmpGroup = null;
            } else {
              _tmpGroup = _cursor.getString(_cursorIndexOfGroup);
            }
            final String _tmpTvgId;
            if (_cursor.isNull(_cursorIndexOfTvgId)) {
              _tmpTvgId = null;
            } else {
              _tmpTvgId = _cursor.getString(_cursorIndexOfTvgId);
            }
            final String _tmpTvgName;
            if (_cursor.isNull(_cursorIndexOfTvgName)) {
              _tmpTvgName = null;
            } else {
              _tmpTvgName = _cursor.getString(_cursorIndexOfTvgName);
            }
            final boolean _tmpIsRadio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRadio);
            _tmpIsRadio = _tmp != 0;
            final boolean _tmpIsFavourite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavourite);
            _tmpIsFavourite = _tmp_1 != 0;
            final String _tmpUserAgent;
            if (_cursor.isNull(_cursorIndexOfUserAgent)) {
              _tmpUserAgent = null;
            } else {
              _tmpUserAgent = _cursor.getString(_cursorIndexOfUserAgent);
            }
            final String _tmpReferrer;
            if (_cursor.isNull(_cursorIndexOfReferrer)) {
              _tmpReferrer = null;
            } else {
              _tmpReferrer = _cursor.getString(_cursorIndexOfReferrer);
            }
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _result = new ChannelEntity(_tmpId,_tmpSourceId,_tmpName,_tmpUrl,_tmpLogoUrl,_tmpGroup,_tmpTvgId,_tmpTvgName,_tmpIsRadio,_tmpIsFavourite,_tmpUserAgent,_tmpReferrer,_tmpSortOrder);
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

  @Override
  public Flow<List<ChannelEntity>> searchChannels(final String query) {
    final String _sql = "\n"
            + "        SELECT * FROM channels\n"
            + "        WHERE name LIKE '%' || ? || '%'\n"
            + "           OR `group` LIKE '%' || ? || '%'\n"
            + "        ORDER BY name\n"
            + "        LIMIT 200\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"channels"}, new Callable<List<ChannelEntity>>() {
      @Override
      @NonNull
      public List<ChannelEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfLogoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "logoUrl");
          final int _cursorIndexOfGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "group");
          final int _cursorIndexOfTvgId = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgId");
          final int _cursorIndexOfTvgName = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgName");
          final int _cursorIndexOfIsRadio = CursorUtil.getColumnIndexOrThrow(_cursor, "isRadio");
          final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavourite");
          final int _cursorIndexOfUserAgent = CursorUtil.getColumnIndexOrThrow(_cursor, "userAgent");
          final int _cursorIndexOfReferrer = CursorUtil.getColumnIndexOrThrow(_cursor, "referrer");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final List<ChannelEntity> _result = new ArrayList<ChannelEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChannelEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpSourceId;
            _tmpSourceId = _cursor.getLong(_cursorIndexOfSourceId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpLogoUrl;
            if (_cursor.isNull(_cursorIndexOfLogoUrl)) {
              _tmpLogoUrl = null;
            } else {
              _tmpLogoUrl = _cursor.getString(_cursorIndexOfLogoUrl);
            }
            final String _tmpGroup;
            if (_cursor.isNull(_cursorIndexOfGroup)) {
              _tmpGroup = null;
            } else {
              _tmpGroup = _cursor.getString(_cursorIndexOfGroup);
            }
            final String _tmpTvgId;
            if (_cursor.isNull(_cursorIndexOfTvgId)) {
              _tmpTvgId = null;
            } else {
              _tmpTvgId = _cursor.getString(_cursorIndexOfTvgId);
            }
            final String _tmpTvgName;
            if (_cursor.isNull(_cursorIndexOfTvgName)) {
              _tmpTvgName = null;
            } else {
              _tmpTvgName = _cursor.getString(_cursorIndexOfTvgName);
            }
            final boolean _tmpIsRadio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRadio);
            _tmpIsRadio = _tmp != 0;
            final boolean _tmpIsFavourite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavourite);
            _tmpIsFavourite = _tmp_1 != 0;
            final String _tmpUserAgent;
            if (_cursor.isNull(_cursorIndexOfUserAgent)) {
              _tmpUserAgent = null;
            } else {
              _tmpUserAgent = _cursor.getString(_cursorIndexOfUserAgent);
            }
            final String _tmpReferrer;
            if (_cursor.isNull(_cursorIndexOfReferrer)) {
              _tmpReferrer = null;
            } else {
              _tmpReferrer = _cursor.getString(_cursorIndexOfReferrer);
            }
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _item = new ChannelEntity(_tmpId,_tmpSourceId,_tmpName,_tmpUrl,_tmpLogoUrl,_tmpGroup,_tmpTvgId,_tmpTvgName,_tmpIsRadio,_tmpIsFavourite,_tmpUserAgent,_tmpReferrer,_tmpSortOrder);
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
  public Flow<List<String>> getGroups() {
    final String _sql = "SELECT DISTINCT `group` FROM channels ORDER BY `group`";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"channels"}, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
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
  public Flow<List<ChannelEntity>> getChannelsForSource(final long sourceId) {
    final String _sql = "SELECT * FROM channels WHERE sourceId = ? ORDER BY sortOrder";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sourceId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"channels"}, new Callable<List<ChannelEntity>>() {
      @Override
      @NonNull
      public List<ChannelEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSourceId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfLogoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "logoUrl");
          final int _cursorIndexOfGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "group");
          final int _cursorIndexOfTvgId = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgId");
          final int _cursorIndexOfTvgName = CursorUtil.getColumnIndexOrThrow(_cursor, "tvgName");
          final int _cursorIndexOfIsRadio = CursorUtil.getColumnIndexOrThrow(_cursor, "isRadio");
          final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavourite");
          final int _cursorIndexOfUserAgent = CursorUtil.getColumnIndexOrThrow(_cursor, "userAgent");
          final int _cursorIndexOfReferrer = CursorUtil.getColumnIndexOrThrow(_cursor, "referrer");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final List<ChannelEntity> _result = new ArrayList<ChannelEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChannelEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final long _tmpSourceId;
            _tmpSourceId = _cursor.getLong(_cursorIndexOfSourceId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpLogoUrl;
            if (_cursor.isNull(_cursorIndexOfLogoUrl)) {
              _tmpLogoUrl = null;
            } else {
              _tmpLogoUrl = _cursor.getString(_cursorIndexOfLogoUrl);
            }
            final String _tmpGroup;
            if (_cursor.isNull(_cursorIndexOfGroup)) {
              _tmpGroup = null;
            } else {
              _tmpGroup = _cursor.getString(_cursorIndexOfGroup);
            }
            final String _tmpTvgId;
            if (_cursor.isNull(_cursorIndexOfTvgId)) {
              _tmpTvgId = null;
            } else {
              _tmpTvgId = _cursor.getString(_cursorIndexOfTvgId);
            }
            final String _tmpTvgName;
            if (_cursor.isNull(_cursorIndexOfTvgName)) {
              _tmpTvgName = null;
            } else {
              _tmpTvgName = _cursor.getString(_cursorIndexOfTvgName);
            }
            final boolean _tmpIsRadio;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsRadio);
            _tmpIsRadio = _tmp != 0;
            final boolean _tmpIsFavourite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavourite);
            _tmpIsFavourite = _tmp_1 != 0;
            final String _tmpUserAgent;
            if (_cursor.isNull(_cursorIndexOfUserAgent)) {
              _tmpUserAgent = null;
            } else {
              _tmpUserAgent = _cursor.getString(_cursorIndexOfUserAgent);
            }
            final String _tmpReferrer;
            if (_cursor.isNull(_cursorIndexOfReferrer)) {
              _tmpReferrer = null;
            } else {
              _tmpReferrer = _cursor.getString(_cursorIndexOfReferrer);
            }
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _item = new ChannelEntity(_tmpId,_tmpSourceId,_tmpName,_tmpUrl,_tmpLogoUrl,_tmpGroup,_tmpTvgId,_tmpTvgName,_tmpIsRadio,_tmpIsFavourite,_tmpUserAgent,_tmpReferrer,_tmpSortOrder);
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
  public Object countBySource(final long sourceId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM channels WHERE sourceId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, sourceId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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
