package com.merlottv.app.domain.repository

import com.merlottv.app.domain.model.*
import kotlinx.coroutines.flow.Flow

// ─────────────────────────────────────────────
// IPTV
// ─────────────────────────────────────────────

interface ChannelRepository {
    /** All saved playlist sources */
    fun getSources(): Flow<List<PlaylistSource>>

    /** All channels across all active sources, grouped */
    fun getChannelGroups(): Flow<List<ChannelGroup>>

    /** Flat list — used by the player and checker */
    fun getAllChannels(): Flow<List<Channel>>

    /** Favourite channels only */
    fun getFavouriteChannels(): Flow<List<Channel>>

    /** Search by name / group */
    fun searchChannels(query: String): Flow<List<Channel>>

    suspend fun addSource(source: PlaylistSource): Long
    suspend fun removeSource(id: Long)
    suspend fun refreshSource(id: Long): Result<Int>  // returns channel count
    suspend fun refreshAllSources(): Result<Unit>

    suspend fun toggleFavourite(channelId: String)
    suspend fun getChannel(id: String): Channel?
}

// ─────────────────────────────────────────────
// EPG / TV Guide
// ─────────────────────────────────────────────

interface EpgRepository {
    fun getEpgSources(): Flow<List<EpgSource>>
    fun getProgrammesForChannel(channelId: String, date: Long): Flow<List<Programme>>
    fun getCurrentProgramme(channelId: String): Flow<Programme?>
    fun getGuideForDate(date: Long): Flow<Map<String, List<Programme>>>

    suspend fun addEpgSource(source: EpgSource): Long
    suspend fun removeEpgSource(id: Long)
    suspend fun refreshEpg(sourceId: Long): Result<Unit>
    suspend fun refreshAllEpg(): Result<Unit>
}

// ─────────────────────────────────────────────
// VOD
// ─────────────────────────────────────────────

interface VodRepository {
    /** Catalogue browsing */
    suspend fun getCatalogue(
        type: VodType,
        genre: String? = null,
        page: Int = 1,
    ): Result<List<VodItem>>

    /** Search Cinemeta / installed addons */
    suspend fun search(query: String): Result<List<VodItem>>

    /** Full metadata for a single item */
    suspend fun getMeta(id: String, type: VodType): Result<VodItem>

    /** Episodes for a series */
    suspend fun getEpisodes(seriesId: String): Result<List<Episode>>

    /** Resolve stream URLs from all installed addons */
    suspend fun getStreams(id: String, type: VodType, season: Int? = null, episode: Int? = null): Result<List<VodStream>>

    /** Addon management */
    fun getInstalledAddons(): Flow<List<StremioAddon>>
    suspend fun installAddon(manifestUrl: String): Result<StremioAddon>
    suspend fun removeAddon(addonId: String)
}

data class StremioAddon(
    val id: String,
    val name: String,
    val version: String,
    val manifestUrl: String,
    val types: List<String>,
    val catalogs: List<AddonCatalog>,
    val isEnabled: Boolean = true,
)

data class AddonCatalog(
    val type: String,
    val id: String,
    val name: String,
)

// ─────────────────────────────────────────────
// CHANNEL CHECKER
// ─────────────────────────────────────────────

interface CheckerRepository {
    suspend fun checkChannel(channel: Channel): CheckResult
    suspend fun checkAll(channels: List<Channel>): Flow<CheckResult>
    fun getLastResults(): Flow<List<CheckResult>>
}

// ─────────────────────────────────────────────
// SETTINGS
// ─────────────────────────────────────────────

interface SettingsRepository {
    fun getSettings(): Flow<AppSettings>
    suspend fun saveSettings(settings: AppSettings)
    suspend fun updateSetting(key: String, value: String)
}

data class AppSettings(
    val defaultUserAgent: String = "MerlotTV/2.0",
    val epgRefreshIntervalHours: Int = 12,
    val m3uRefreshIntervalHours: Int = 24,
    val preferExternalPlayer: Boolean = false,
    val hardwareDecoding: Boolean = true,
    val bufferSizeSeconds: Int = 30,
    val subtitlesEnabled: Boolean = true,
    val defaultAudioLanguage: String = "en",
    val parenralControlEnabled: Boolean = false,
    val parentalPin: String = "",
    val themeMode: String = "dark",      // dark | light | system
    val epgDaysAhead: Int = 3,
)
