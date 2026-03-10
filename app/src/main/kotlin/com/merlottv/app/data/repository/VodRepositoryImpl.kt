package com.merlottv.app.data.repository

import com.merlottv.app.data.local.dao.StremioAddonDao
import com.merlottv.app.data.local.entity.StremioAddonEntity
import com.merlottv.app.data.network.api.CinemetaApi
import com.merlottv.app.data.network.api.StremioAddonApi
import com.merlottv.app.data.network.dto.*
import com.merlottv.app.domain.model.*
import com.merlottv.app.domain.repository.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VodRepositoryImpl @Inject constructor(
    private val cinemetaApi: CinemetaApi,
    private val addonApi: StremioAddonApi,
    private val addonDao: StremioAddonDao,
    private val gson: Gson,
) : VodRepository {

    override suspend fun getCatalogue(type: VodType, genre: String?, page: Int): Result<List<VodItem>> =
        runCatching {
            val typeStr = type.toStremioType()
            val skip    = (page - 1) * 100
            val response = if (genre != null) {
                cinemetaApi.getCatalogFiltered(typeStr, "top", "genre=$genre&skip=$skip")
            } else {
                cinemetaApi.getCatalogFiltered(typeStr, "top", "skip=$skip")
            }
            response.metas.map { it.toVodItem() }
        }

    override suspend fun search(query: String): Result<List<VodItem>> =
        runCatching {
            val movies  = cinemetaApi.search("movie", query).metas.map { it.toVodItem() }
            val series  = cinemetaApi.search("series", query).metas.map { it.toVodItem() }
            (movies + series).distinctBy { it.id }
        }

    override suspend fun getMeta(id: String, type: VodType): Result<VodItem> =
        runCatching {
            cinemetaApi.getMeta(type.toStremioType(), id).meta?.toVodItem()
                ?: error("No meta for $id")
        }

    override suspend fun getEpisodes(seriesId: String): Result<List<Episode>> =
        runCatching {
            val meta = cinemetaApi.getMeta("series", seriesId).meta ?: error("No meta for $seriesId")
            meta.videos.map { it.toEpisode(seriesId) }
        }

    override suspend fun getStreams(
        id: String,
        type: VodType,
        season: Int?,
        episode: Int?,
    ): Result<List<VodStream>> = runCatching {
        val streamId = if (season != null && episode != null) "$id:$season:$episode" else id
        addonApi.getStreams(type.toStremioType(), streamId)
            .streams
            .mapNotNull { it.toVodStream() }
    }

    // ─── Addon management ────────────────────────────────────────

    override fun getInstalledAddons(): Flow<List<StremioAddon>> =
        addonDao.getAll().map { list -> list.map { it.toDomain(gson) } }

    override suspend fun installAddon(manifestUrl: String): Result<StremioAddon> =
        runCatching {
            val manifest = addonApi.getManifest()   // base URL must match manifestUrl
            val addon = StremioAddon(
                id          = manifest.id,
                name        = manifest.name,
                version     = manifest.version,
                manifestUrl = manifestUrl,
                types       = manifest.types,
                catalogs    = manifest.catalogs.map { AddonCatalog(it.type, it.id, it.name) },
            )
            addonDao.insert(addon.toEntity(gson))
            addon
        }

    override suspend fun removeAddon(addonId: String) = addonDao.deleteById(addonId)
}

// ─── Mappers ─────────────────────────────────────────────────────────────────

private fun VodType.toStremioType() = if (this == VodType.MOVIE) "movie" else "series"

private fun MetaDto.toVodItem() = VodItem(
    id            = id,
    type          = if (type == "movie") VodType.MOVIE else VodType.SERIES,
    name          = name,
    posterUrl     = poster ?: "",
    backgroundUrl = background ?: "",
    year          = year?.toString() ?: "",
    description   = description ?: "",
    genres        = genres,
    imdbRating    = imdbRating ?: "",
    cast          = cast,
    director      = director,
    runtime       = runtime ?: "",
)

private fun VideoDto.toEpisode(seriesId: String) = Episode(
    id         = id,
    seriesId   = seriesId,
    season     = season,
    episode    = episode,
    title      = title,
    overview   = overview,
    thumbUrl   = thumbnail ?: "",
    firstAired = released ?: "",
)

private fun StreamDto.toVodStream(): VodStream? {
    val streamUrl = url ?: return null    // skip torrent-only streams
    return VodStream(
        url         = streamUrl,
        title       = title ?: name ?: "",
        description = description ?: "",
        behaviorHints = BehaviorHints(
            notWebReady = behaviorHints?.notWebReady ?: false,
            bingeGroup  = behaviorHints?.bingeGroup,
        ),
    )
}

private fun StremioAddonEntity.toDomain(gson: Gson): StremioAddon {
    val typeToken = object : TypeToken<List<String>>() {}.type
    val catToken  = object : TypeToken<List<AddonCatalog>>() {}.type
    return StremioAddon(
        id          = id,
        name        = name,
        version     = version,
        manifestUrl = manifestUrl,
        types       = gson.fromJson(typesJson, typeToken),
        catalogs    = gson.fromJson(catalogsJson, catToken),
        isEnabled   = isEnabled,
    )
}

private fun StremioAddon.toEntity(gson: Gson) = StremioAddonEntity(
    id           = id,
    name         = name,
    version      = version,
    manifestUrl  = manifestUrl,
    typesJson    = gson.toJson(types),
    catalogsJson = gson.toJson(catalogs),
    isEnabled    = isEnabled,
)
