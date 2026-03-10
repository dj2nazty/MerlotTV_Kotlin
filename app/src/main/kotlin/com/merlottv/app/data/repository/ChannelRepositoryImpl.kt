package com.merlottv.app.data.repository

import com.merlottv.app.data.local.dao.ChannelDao
import com.merlottv.app.data.local.dao.PlaylistSourceDao
import com.merlottv.app.data.local.entity.ChannelEntity
import com.merlottv.app.data.local.entity.PlaylistSourceEntity
import com.merlottv.app.data.parser.M3uParser
import com.merlottv.app.domain.model.*
import com.merlottv.app.domain.repository.ChannelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChannelRepositoryImpl @Inject constructor(
    private val channelDao: ChannelDao,
    private val sourceDao: PlaylistSourceDao,
    private val parser: M3uParser,
    private val okHttp: OkHttpClient,
) : ChannelRepository {

    override fun getSources(): Flow<List<PlaylistSource>> =
        sourceDao.getAll().map { list -> list.map { it.toDomain() } }

    override fun getChannelGroups(): Flow<List<ChannelGroup>> =
        channelDao.getAllChannels().map { entities ->
            entities
                .map { it.toDomain() }
                .groupBy { it.group }
                .map { (group, channels) -> ChannelGroup(group, channels) }
                .sortedBy { it.name }
        }

    override fun getAllChannels(): Flow<List<Channel>> =
        channelDao.getAllChannels().map { it.map(ChannelEntity::toDomain) }

    override fun getFavouriteChannels(): Flow<List<Channel>> =
        channelDao.getFavouriteChannels().map { it.map(ChannelEntity::toDomain) }

    override fun searchChannels(query: String): Flow<List<Channel>> =
        channelDao.searchChannels(query).map { it.map(ChannelEntity::toDomain) }

    override suspend fun getChannel(id: String): Channel? =
        channelDao.getChannel(id)?.toDomain()

    override suspend fun addSource(source: PlaylistSource): Long =
        sourceDao.insert(source.toEntity())

    override suspend fun removeSource(id: Long) {
        channelDao.deleteBySource(id)
        sourceDao.deleteById(id)
    }

    override suspend fun toggleFavourite(channelId: String) =
        channelDao.toggleFavourite(channelId)

    override suspend fun refreshSource(id: Long): Result<Int> = withContext(Dispatchers.IO) {
        runCatching {
            val source = sourceDao.getById(id) ?: error("Source $id not found")
            val channels = downloadAndParse(source.url)
            channelDao.deleteBySource(id)
            channelDao.insertAll(channels.mapIndexed { i, ch ->
                ch.toEntity(sourceId = id, sortOrder = i)
            })
            val count = channels.size
            sourceDao.update(
                source.copy(lastRefreshed = System.currentTimeMillis(), channelCount = count)
            )
            count
        }
    }

    override suspend fun refreshAllSources(): Result<Unit> = runCatching {
        sourceDao.getActiveSources()
            .first()
            .forEach { source -> refreshSource(source.id) }
    }

    private suspend fun downloadAndParse(url: String): List<Channel> = withContext(Dispatchers.IO) {
        when {
            url.startsWith("file://") || url.startsWith("/") -> {
                java.io.File(url.removePrefix("file://")).inputStream().use(parser::parse)
            }
            else -> {
                val request = Request.Builder().url(url).build()
                okHttp.newCall(request).execute().use { response ->
                    if (!response.isSuccessful) error("HTTP ${response.code}: $url")
                    parser.parse(response.body!!.byteStream())
                }
            }
        }
    }
}

private fun ChannelEntity.toDomain() = Channel(
    id          = id,
    name        = name,
    url         = url,
    logoUrl     = logoUrl,
    group       = group,
    tvgName     = tvgName,
    isRadio     = isRadio,
    isFavourite = isFavourite,
    userAgent   = userAgent,
    referrer    = referrer,
)

private fun Channel.toEntity(sourceId: Long, sortOrder: Int = 0) = ChannelEntity(
    id          = id,
    sourceId    = sourceId,
    name        = name,
    url         = url,
    logoUrl     = logoUrl,
    group       = group,
    tvgId       = id,
    tvgName     = tvgName,
    isRadio     = isRadio,
    isFavourite = isFavourite,
    userAgent   = userAgent,
    referrer    = referrer,
    sortOrder   = sortOrder,
)

private fun PlaylistSourceEntity.toDomain() = PlaylistSource(
    id            = id,
    name          = name,
    url           = url,
    lastRefreshed = lastRefreshed,
    channelCount  = channelCount,
    isActive      = isActive,
)

private fun PlaylistSource.toEntity() = PlaylistSourceEntity(
    id            = id,
    name          = name,
    url           = url,
    lastRefreshed = lastRefreshed,
    channelCount  = channelCount,
    isActive      = isActive,
)
