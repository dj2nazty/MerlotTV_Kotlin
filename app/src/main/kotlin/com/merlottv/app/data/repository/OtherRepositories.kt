package com.merlottv.app.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.merlottv.app.data.local.dao.EpgSourceDao
import com.merlottv.app.data.local.dao.ProgrammeDao
import com.merlottv.app.data.local.entity.EpgSourceEntity
import com.merlottv.app.data.local.entity.ProgrammeEntity
import com.merlottv.app.data.parser.XmltvParser
import com.merlottv.app.domain.model.*
import com.merlottv.app.domain.repository.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.Calendar
import javax.inject.Inject
import javax.inject.Singleton

// ──────────────────────────────────────────────────────────────────────────────
// SETTINGS REPOSITORY
// ──────────────────────────────────────────────────────────────────────────────

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : SettingsRepository {

    private object Keys {
        val USER_AGENT       = stringPreferencesKey("user_agent")
        val EPG_INTERVAL     = intPreferencesKey("epg_interval")
        val M3U_INTERVAL     = intPreferencesKey("m3u_interval")
        val HW_DECODING      = booleanPreferencesKey("hw_decoding")
        val BUFFER_SECONDS   = intPreferencesKey("buffer_seconds")
        val SUBTITLES        = booleanPreferencesKey("subtitles")
        val AUDIO_LANG       = stringPreferencesKey("audio_lang")
        val PARENTAL_ENABLED = booleanPreferencesKey("parental_enabled")
        val PARENTAL_PIN     = stringPreferencesKey("parental_pin")
        val THEME            = stringPreferencesKey("theme")
        val EPG_DAYS         = intPreferencesKey("epg_days")
    }

    override fun getSettings(): Flow<AppSettings> =
        context.dataStore.data.map { prefs ->
            AppSettings(
                defaultUserAgent          = prefs[Keys.USER_AGENT]       ?: "MerlotTV/2.0",
                epgRefreshIntervalHours   = prefs[Keys.EPG_INTERVAL]     ?: 12,
                m3uRefreshIntervalHours   = prefs[Keys.M3U_INTERVAL]     ?: 24,
                hardwareDecoding          = prefs[Keys.HW_DECODING]      ?: true,
                bufferSizeSeconds         = prefs[Keys.BUFFER_SECONDS]   ?: 30,
                subtitlesEnabled          = prefs[Keys.SUBTITLES]        ?: true,
                defaultAudioLanguage      = prefs[Keys.AUDIO_LANG]       ?: "en",
                parenralControlEnabled    = prefs[Keys.PARENTAL_ENABLED] ?: false,
                parentalPin               = prefs[Keys.PARENTAL_PIN]     ?: "",
                themeMode                 = prefs[Keys.THEME]            ?: "dark",
                epgDaysAhead              = prefs[Keys.EPG_DAYS]         ?: 3,
            )
        }

    override suspend fun saveSettings(settings: AppSettings) {
        context.dataStore.edit { prefs ->
            prefs[Keys.USER_AGENT]       = settings.defaultUserAgent
            prefs[Keys.EPG_INTERVAL]     = settings.epgRefreshIntervalHours
            prefs[Keys.M3U_INTERVAL]     = settings.m3uRefreshIntervalHours
            prefs[Keys.HW_DECODING]      = settings.hardwareDecoding
            prefs[Keys.BUFFER_SECONDS]   = settings.bufferSizeSeconds
            prefs[Keys.SUBTITLES]        = settings.subtitlesEnabled
            prefs[Keys.AUDIO_LANG]       = settings.defaultAudioLanguage
            prefs[Keys.PARENTAL_ENABLED] = settings.parenralControlEnabled
            prefs[Keys.PARENTAL_PIN]     = settings.parentalPin
            prefs[Keys.THEME]            = settings.themeMode
            prefs[Keys.EPG_DAYS]         = settings.epgDaysAhead
        }
    }

    override suspend fun updateSetting(key: String, value: String) {
        context.dataStore.edit { prefs ->
            prefs[stringPreferencesKey(key)] = value
        }
    }
}

// ──────────────────────────────────────────────────────────────────────────────
// EPG REPOSITORY
// ──────────────────────────────────────────────────────────────────────────────

@Singleton
class EpgRepositoryImpl @Inject constructor(
    private val epgSourceDao: EpgSourceDao,
    private val programmeDao: ProgrammeDao,
    private val parser: XmltvParser,
    private val okHttp: OkHttpClient,
) : EpgRepository {

    override fun getEpgSources(): Flow<List<EpgSource>> =
        epgSourceDao.getAll().map { list -> list.map { it.toDomain() } }

    override fun getProgrammesForChannel(channelId: String, date: Long): Flow<List<Programme>> {
        val (start, end) = dayBounds(date)
        return programmeDao.getProgrammesForChannel(channelId, start, end)
            .map { it.map(ProgrammeEntity::toDomain) }
    }

    override fun getCurrentProgramme(channelId: String): Flow<Programme?> =
        programmeDao.getCurrentProgramme(channelId, System.currentTimeMillis())
            .map { it?.toDomain() }

    override fun getGuideForDate(date: Long): Flow<Map<String, List<Programme>>> {
        val (start, end) = dayBounds(date)
        return programmeDao.getGuideForDay(start, end).map { entities ->
            entities
                .map { it.toDomain() }
                .groupBy { it.channelId }
        }
    }

    override suspend fun addEpgSource(source: EpgSource): Long =
        epgSourceDao.insert(source.toEntity())

    override suspend fun removeEpgSource(id: Long) = epgSourceDao.deleteById(id)

    override suspend fun refreshEpg(sourceId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val source = epgSourceDao.getById(sourceId) ?: error("EPG source $sourceId not found")
            val programmes = downloadAndParse(source.url)
            programmeDao.deleteOlderThan(System.currentTimeMillis() - 24 * 60 * 60_000L)
            programmeDao.insertAll(programmes.map { it.toEntity() })
            epgSourceDao.update(source.copy(lastRefreshed = System.currentTimeMillis()))
        }
    }

    override suspend fun refreshAllEpg(): Result<Unit> = runCatching {
        epgSourceDao.getAll().first().forEach { refreshEpg(it.id) }
    }

    private suspend fun downloadAndParse(url: String): List<Programme> = withContext(Dispatchers.IO) {
        val request = Request.Builder().url(url).build()
        okHttp.newCall(request).execute().use { response ->
            if (!response.isSuccessful) error("HTTP ${response.code}: $url")
            parser.parse(response.body!!.byteStream())
        }
    }

    private fun dayBounds(date: Long): Pair<Long, Long> {
        val cal = Calendar.getInstance().apply { timeInMillis = date }
        cal.set(Calendar.HOUR_OF_DAY, 0); cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0);      cal.set(Calendar.MILLISECOND, 0)
        val start = cal.timeInMillis
        return start to (start + 24 * 60 * 60_000L)
    }
}

// ──────────────────────────────────────────────────────────────────────────────
// CHECKER REPOSITORY
// ──────────────────────────────────────────────────────────────────────────────

@Singleton
class CheckerRepositoryImpl @Inject constructor(
    private val okHttp: OkHttpClient,
) : CheckerRepository {

    private val lastResults = MutableStateFlow<List<CheckResult>>(emptyList())

    override suspend fun checkChannel(channel: Channel): CheckResult = withContext(Dispatchers.IO) {
        val start = System.currentTimeMillis()
        runCatching {
            val request = Request.Builder()
                .url(channel.url)
                .head()   // HEAD request — checks reachability without downloading stream
                .apply { if (channel.userAgent.isNotEmpty()) header("User-Agent", channel.userAgent) }
                .apply { if (channel.referrer.isNotEmpty()) header("Referer", channel.referrer) }
                .build()
            val response = okHttp.newCall(request).execute()
            val elapsed  = System.currentTimeMillis() - start
            response.close()
            CheckResult(
                channel        = channel,
                status         = if (response.isSuccessful || response.code in 200..499) StreamStatus.ONLINE else StreamStatus.OFFLINE,
                responseTimeMs = elapsed,
                httpCode       = response.code,
            )
        }.getOrElse { error ->
            val elapsed = System.currentTimeMillis() - start
            CheckResult(
                channel        = channel,
                status         = if (elapsed >= 10_000) StreamStatus.TIMEOUT else StreamStatus.ERROR,
                responseTimeMs = elapsed,
                error          = error.message,
            )
        }
    }

    override suspend fun checkAll(channels: List<Channel>): Flow<CheckResult> = flow {
        val results = mutableListOf<CheckResult>()
        channels.forEach { channel ->
            val result = checkChannel(channel)
            results += result
            lastResults.value = results.toList()
            emit(result)
        }
    }

    override fun getLastResults(): Flow<List<CheckResult>> = lastResults
}

// ─── Entity / Domain mappers ──────────────────────────────────────────────────

private fun EpgSourceEntity.toDomain() = EpgSource(id, name, url, lastRefreshed)
private fun EpgSource.toEntity() = EpgSourceEntity(id, name, url, lastRefreshed)

private fun ProgrammeEntity.toDomain() = Programme(
    channelId   = channelId,
    title       = title,
    description = description,
    startTime   = startTime,
    endTime     = endTime,
    category    = category,
    iconUrl     = iconUrl,
    rating      = rating,
)

private fun Programme.toEntity() = ProgrammeEntity(
    channelId   = channelId,
    title       = title,
    description = description,
    startTime   = startTime,
    endTime     = endTime,
    category    = category,
    iconUrl     = iconUrl,
    rating      = rating,
)
