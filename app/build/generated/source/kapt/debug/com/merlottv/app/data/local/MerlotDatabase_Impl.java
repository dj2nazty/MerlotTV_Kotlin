package com.merlottv.app.data.local;

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
import com.merlottv.app.data.local.dao.ChannelDao;
import com.merlottv.app.data.local.dao.ChannelDao_Impl;
import com.merlottv.app.data.local.dao.EpgSourceDao;
import com.merlottv.app.data.local.dao.EpgSourceDao_Impl;
import com.merlottv.app.data.local.dao.PlaylistSourceDao;
import com.merlottv.app.data.local.dao.PlaylistSourceDao_Impl;
import com.merlottv.app.data.local.dao.ProgrammeDao;
import com.merlottv.app.data.local.dao.ProgrammeDao_Impl;
import com.merlottv.app.data.local.dao.StremioAddonDao;
import com.merlottv.app.data.local.dao.StremioAddonDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MerlotDatabase_Impl extends MerlotDatabase {
  private volatile ChannelDao _channelDao;

  private volatile PlaylistSourceDao _playlistSourceDao;

  private volatile ProgrammeDao _programmeDao;

  private volatile EpgSourceDao _epgSourceDao;

  private volatile StremioAddonDao _stremioAddonDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `channels` (`id` TEXT NOT NULL, `sourceId` INTEGER NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `logoUrl` TEXT NOT NULL, `group` TEXT NOT NULL, `tvgId` TEXT NOT NULL, `tvgName` TEXT NOT NULL, `isRadio` INTEGER NOT NULL, `isFavourite` INTEGER NOT NULL, `userAgent` TEXT NOT NULL, `referrer` TEXT NOT NULL, `sortOrder` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_channels_sourceId` ON `channels` (`sourceId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_channels_group` ON `channels` (`group`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_channels_tvgId` ON `channels` (`tvgId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_channels_isFavourite` ON `channels` (`isFavourite`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `playlist_sources` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `lastRefreshed` INTEGER NOT NULL, `channelCount` INTEGER NOT NULL, `isActive` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `programmes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `channelId` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `startTime` INTEGER NOT NULL, `endTime` INTEGER NOT NULL, `category` TEXT NOT NULL, `iconUrl` TEXT NOT NULL, `rating` TEXT NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_programmes_channelId` ON `programmes` (`channelId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_programmes_startTime` ON `programmes` (`startTime`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_programmes_endTime` ON `programmes` (`endTime`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `epg_sources` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `url` TEXT NOT NULL, `lastRefreshed` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `stremio_addons` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `version` TEXT NOT NULL, `manifestUrl` TEXT NOT NULL, `typesJson` TEXT NOT NULL, `catalogsJson` TEXT NOT NULL, `isEnabled` INTEGER NOT NULL, `installedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a245dd4dcefb285da210042d5e4e6e04')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `channels`");
        db.execSQL("DROP TABLE IF EXISTS `playlist_sources`");
        db.execSQL("DROP TABLE IF EXISTS `programmes`");
        db.execSQL("DROP TABLE IF EXISTS `epg_sources`");
        db.execSQL("DROP TABLE IF EXISTS `stremio_addons`");
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
        final HashMap<String, TableInfo.Column> _columnsChannels = new HashMap<String, TableInfo.Column>(13);
        _columnsChannels.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("sourceId", new TableInfo.Column("sourceId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("logoUrl", new TableInfo.Column("logoUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("group", new TableInfo.Column("group", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("tvgId", new TableInfo.Column("tvgId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("tvgName", new TableInfo.Column("tvgName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("isRadio", new TableInfo.Column("isRadio", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("isFavourite", new TableInfo.Column("isFavourite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("userAgent", new TableInfo.Column("userAgent", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("referrer", new TableInfo.Column("referrer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsChannels.put("sortOrder", new TableInfo.Column("sortOrder", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysChannels = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesChannels = new HashSet<TableInfo.Index>(4);
        _indicesChannels.add(new TableInfo.Index("index_channels_sourceId", false, Arrays.asList("sourceId"), Arrays.asList("ASC")));
        _indicesChannels.add(new TableInfo.Index("index_channels_group", false, Arrays.asList("group"), Arrays.asList("ASC")));
        _indicesChannels.add(new TableInfo.Index("index_channels_tvgId", false, Arrays.asList("tvgId"), Arrays.asList("ASC")));
        _indicesChannels.add(new TableInfo.Index("index_channels_isFavourite", false, Arrays.asList("isFavourite"), Arrays.asList("ASC")));
        final TableInfo _infoChannels = new TableInfo("channels", _columnsChannels, _foreignKeysChannels, _indicesChannels);
        final TableInfo _existingChannels = TableInfo.read(db, "channels");
        if (!_infoChannels.equals(_existingChannels)) {
          return new RoomOpenHelper.ValidationResult(false, "channels(com.merlottv.app.data.local.entity.ChannelEntity).\n"
                  + " Expected:\n" + _infoChannels + "\n"
                  + " Found:\n" + _existingChannels);
        }
        final HashMap<String, TableInfo.Column> _columnsPlaylistSources = new HashMap<String, TableInfo.Column>(6);
        _columnsPlaylistSources.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistSources.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistSources.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistSources.put("lastRefreshed", new TableInfo.Column("lastRefreshed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistSources.put("channelCount", new TableInfo.Column("channelCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlaylistSources.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlaylistSources = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlaylistSources = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlaylistSources = new TableInfo("playlist_sources", _columnsPlaylistSources, _foreignKeysPlaylistSources, _indicesPlaylistSources);
        final TableInfo _existingPlaylistSources = TableInfo.read(db, "playlist_sources");
        if (!_infoPlaylistSources.equals(_existingPlaylistSources)) {
          return new RoomOpenHelper.ValidationResult(false, "playlist_sources(com.merlottv.app.data.local.entity.PlaylistSourceEntity).\n"
                  + " Expected:\n" + _infoPlaylistSources + "\n"
                  + " Found:\n" + _existingPlaylistSources);
        }
        final HashMap<String, TableInfo.Column> _columnsProgrammes = new HashMap<String, TableInfo.Column>(9);
        _columnsProgrammes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgrammes.put("channelId", new TableInfo.Column("channelId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgrammes.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgrammes.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgrammes.put("startTime", new TableInfo.Column("startTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgrammes.put("endTime", new TableInfo.Column("endTime", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgrammes.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgrammes.put("iconUrl", new TableInfo.Column("iconUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProgrammes.put("rating", new TableInfo.Column("rating", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProgrammes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProgrammes = new HashSet<TableInfo.Index>(3);
        _indicesProgrammes.add(new TableInfo.Index("index_programmes_channelId", false, Arrays.asList("channelId"), Arrays.asList("ASC")));
        _indicesProgrammes.add(new TableInfo.Index("index_programmes_startTime", false, Arrays.asList("startTime"), Arrays.asList("ASC")));
        _indicesProgrammes.add(new TableInfo.Index("index_programmes_endTime", false, Arrays.asList("endTime"), Arrays.asList("ASC")));
        final TableInfo _infoProgrammes = new TableInfo("programmes", _columnsProgrammes, _foreignKeysProgrammes, _indicesProgrammes);
        final TableInfo _existingProgrammes = TableInfo.read(db, "programmes");
        if (!_infoProgrammes.equals(_existingProgrammes)) {
          return new RoomOpenHelper.ValidationResult(false, "programmes(com.merlottv.app.data.local.entity.ProgrammeEntity).\n"
                  + " Expected:\n" + _infoProgrammes + "\n"
                  + " Found:\n" + _existingProgrammes);
        }
        final HashMap<String, TableInfo.Column> _columnsEpgSources = new HashMap<String, TableInfo.Column>(4);
        _columnsEpgSources.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpgSources.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpgSources.put("url", new TableInfo.Column("url", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpgSources.put("lastRefreshed", new TableInfo.Column("lastRefreshed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEpgSources = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesEpgSources = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoEpgSources = new TableInfo("epg_sources", _columnsEpgSources, _foreignKeysEpgSources, _indicesEpgSources);
        final TableInfo _existingEpgSources = TableInfo.read(db, "epg_sources");
        if (!_infoEpgSources.equals(_existingEpgSources)) {
          return new RoomOpenHelper.ValidationResult(false, "epg_sources(com.merlottv.app.data.local.entity.EpgSourceEntity).\n"
                  + " Expected:\n" + _infoEpgSources + "\n"
                  + " Found:\n" + _existingEpgSources);
        }
        final HashMap<String, TableInfo.Column> _columnsStremioAddons = new HashMap<String, TableInfo.Column>(8);
        _columnsStremioAddons.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStremioAddons.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStremioAddons.put("version", new TableInfo.Column("version", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStremioAddons.put("manifestUrl", new TableInfo.Column("manifestUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStremioAddons.put("typesJson", new TableInfo.Column("typesJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStremioAddons.put("catalogsJson", new TableInfo.Column("catalogsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStremioAddons.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStremioAddons.put("installedAt", new TableInfo.Column("installedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStremioAddons = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStremioAddons = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStremioAddons = new TableInfo("stremio_addons", _columnsStremioAddons, _foreignKeysStremioAddons, _indicesStremioAddons);
        final TableInfo _existingStremioAddons = TableInfo.read(db, "stremio_addons");
        if (!_infoStremioAddons.equals(_existingStremioAddons)) {
          return new RoomOpenHelper.ValidationResult(false, "stremio_addons(com.merlottv.app.data.local.entity.StremioAddonEntity).\n"
                  + " Expected:\n" + _infoStremioAddons + "\n"
                  + " Found:\n" + _existingStremioAddons);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a245dd4dcefb285da210042d5e4e6e04", "fc31f2e4b48db03c7dd2f7fd7735b62b");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "channels","playlist_sources","programmes","epg_sources","stremio_addons");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `channels`");
      _db.execSQL("DELETE FROM `playlist_sources`");
      _db.execSQL("DELETE FROM `programmes`");
      _db.execSQL("DELETE FROM `epg_sources`");
      _db.execSQL("DELETE FROM `stremio_addons`");
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
    _typeConvertersMap.put(ChannelDao.class, ChannelDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlaylistSourceDao.class, PlaylistSourceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProgrammeDao.class, ProgrammeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EpgSourceDao.class, EpgSourceDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StremioAddonDao.class, StremioAddonDao_Impl.getRequiredConverters());
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
  public ChannelDao channelDao() {
    if (_channelDao != null) {
      return _channelDao;
    } else {
      synchronized(this) {
        if(_channelDao == null) {
          _channelDao = new ChannelDao_Impl(this);
        }
        return _channelDao;
      }
    }
  }

  @Override
  public PlaylistSourceDao playlistSourceDao() {
    if (_playlistSourceDao != null) {
      return _playlistSourceDao;
    } else {
      synchronized(this) {
        if(_playlistSourceDao == null) {
          _playlistSourceDao = new PlaylistSourceDao_Impl(this);
        }
        return _playlistSourceDao;
      }
    }
  }

  @Override
  public ProgrammeDao programmeDao() {
    if (_programmeDao != null) {
      return _programmeDao;
    } else {
      synchronized(this) {
        if(_programmeDao == null) {
          _programmeDao = new ProgrammeDao_Impl(this);
        }
        return _programmeDao;
      }
    }
  }

  @Override
  public EpgSourceDao epgSourceDao() {
    if (_epgSourceDao != null) {
      return _epgSourceDao;
    } else {
      synchronized(this) {
        if(_epgSourceDao == null) {
          _epgSourceDao = new EpgSourceDao_Impl(this);
        }
        return _epgSourceDao;
      }
    }
  }

  @Override
  public StremioAddonDao stremioAddonDao() {
    if (_stremioAddonDao != null) {
      return _stremioAddonDao;
    } else {
      synchronized(this) {
        if(_stremioAddonDao == null) {
          _stremioAddonDao = new StremioAddonDao_Impl(this);
        }
        return _stremioAddonDao;
      }
    }
  }
}
