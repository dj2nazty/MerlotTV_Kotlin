package com.merlottv.app.data.local.dao

import androidx.room.*
import com.merlottv.app.data.local.entity.*
import kotlinx.coroutines.flow.Flow

// ─────────────────────────────────────────────
// CHANNEL DAO
// ─────────────────────────────────────────────

@Dao
interface ChannelDao {

    @Query("SELECT * FROM channels ORDER BY `group`, sortOrder, name")
    fun getAllChannels(): Flow<List<ChannelEntity>>

    @Query("SELECT * FROM channels WHERE isFavourite = 1 ORDER BY name")
    fun getFavouriteChannels(): Flow<List<ChannelEntity>>

    @Query("SELECT * FROM channels WHERE id = :id LIMIT 1")
    suspend fun getChannel(id: String): ChannelEntity?

    @Query("""
        SELECT * FROM channels
        WHERE name LIKE '%' || :query || '%'
           OR `group` LIKE '%' || :query || '%'
        ORDER BY name
        LIMIT 200
    """)
    fun searchChannels(query: String): Flow<List<ChannelEntity>>

    @Query("SELECT DISTINCT `group` FROM channels ORDER BY `group`")
    fun getGroups(): Flow<List<String>>

    @Query("SELECT * FROM channels WHERE sourceId = :sourceId ORDER BY sortOrder")
    fun getChannelsForSource(sourceId: Long): Flow<List<ChannelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(channels: List<ChannelEntity>)

    @Query("UPDATE channels SET isFavourite = NOT isFavourite WHERE id = :id")
    suspend fun toggleFavourite(id: String)

    @Query("DELETE FROM channels WHERE sourceId = :sourceId")
    suspend fun deleteBySource(sourceId: Long)

    @Query("DELETE FROM channels")
    suspend fun deleteAll()

    @Query("SELECT COUNT(*) FROM channels WHERE sourceId = :sourceId")
    suspend fun countBySource(sourceId: Long): Int
}

// ─────────────────────────────────────────────
// PLAYLIST SOURCE DAO
// ─────────────────────────────────────────────

@Dao
interface PlaylistSourceDao {

    @Query("SELECT * FROM playlist_sources ORDER BY name")
    fun getAll(): Flow<List<PlaylistSourceEntity>>

    @Query("SELECT * FROM playlist_sources WHERE isActive = 1")
    fun getActiveSources(): Flow<List<PlaylistSourceEntity>>

    @Query("SELECT * FROM playlist_sources WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): PlaylistSourceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(source: PlaylistSourceEntity): Long

    @Update
    suspend fun update(source: PlaylistSourceEntity)

    @Query("DELETE FROM playlist_sources WHERE id = :id")
    suspend fun deleteById(id: Long)
}

// ─────────────────────────────────────────────
// EPG / PROGRAMME DAO
// ─────────────────────────────────────────────

@Dao
interface ProgrammeDao {

    @Query("""
        SELECT * FROM programmes
        WHERE channelId = :channelId
          AND endTime >= :startOfDay
          AND startTime < :endOfDay
        ORDER BY startTime
    """)
    fun getProgrammesForChannel(channelId: String, startOfDay: Long, endOfDay: Long): Flow<List<ProgrammeEntity>>

    @Query("""
        SELECT * FROM programmes
        WHERE channelId = :channelId
          AND startTime <= :now
          AND endTime   >  :now
        LIMIT 1
    """)
    fun getCurrentProgramme(channelId: String, now: Long): Flow<ProgrammeEntity?>

    @Query("""
        SELECT * FROM programmes
        WHERE endTime >= :startOfDay
          AND startTime < :endOfDay
        ORDER BY channelId, startTime
    """)
    fun getGuideForDay(startOfDay: Long, endOfDay: Long): Flow<List<ProgrammeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(programmes: List<ProgrammeEntity>)

    /** Remove stale data older than [cutoff] epoch-millis */
    @Query("DELETE FROM programmes WHERE endTime < :cutoff")
    suspend fun deleteOlderThan(cutoff: Long)

    @Query("DELETE FROM programmes")
    suspend fun deleteAll()
}

// ─────────────────────────────────────────────
// EPG SOURCE DAO
// ─────────────────────────────────────────────

@Dao
interface EpgSourceDao {

    @Query("SELECT * FROM epg_sources ORDER BY name")
    fun getAll(): Flow<List<EpgSourceEntity>>

    @Query("SELECT * FROM epg_sources WHERE id = :id LIMIT 1")
    suspend fun getById(id: Long): EpgSourceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(source: EpgSourceEntity): Long

    @Update
    suspend fun update(source: EpgSourceEntity)

    @Query("DELETE FROM epg_sources WHERE id = :id")
    suspend fun deleteById(id: Long)
}

// ─────────────────────────────────────────────
// STREMIO ADDON DAO
// ─────────────────────────────────────────────

@Dao
interface StremioAddonDao {

    @Query("SELECT * FROM stremio_addons ORDER BY installedAt")
    fun getAll(): Flow<List<StremioAddonEntity>>

    @Query("SELECT * FROM stremio_addons WHERE isEnabled = 1 ORDER BY installedAt")
    fun getEnabled(): Flow<List<StremioAddonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(addon: StremioAddonEntity)

    @Update
    suspend fun update(addon: StremioAddonEntity)

    @Query("DELETE FROM stremio_addons WHERE id = :id")
    suspend fun deleteById(id: String)
}
