package com.merlottv.app.data.network.dto

import com.google.gson.annotations.SerializedName

// ─────────────────────────────────────────────
// CATALOGUE
// ─────────────────────────────────────────────

data class CatalogResponse(
    @SerializedName("metas") val metas: List<MetaDto> = emptyList(),
)

data class MetaDto(
    @SerializedName("id")          val id: String = "",
    @SerializedName("type")        val type: String = "",
    @SerializedName("name")        val name: String = "",
    @SerializedName("poster")      val poster: String? = null,
    @SerializedName("background")  val background: String? = null,
    @SerializedName("year")        val year: Any? = null,          // can be Int or String
    @SerializedName("description") val description: String? = null,
    @SerializedName("genres")      val genres: List<String> = emptyList(),
    @SerializedName("imdbRating")  val imdbRating: String? = null,
    @SerializedName("cast")        val cast: List<String> = emptyList(),
    @SerializedName("director")    val director: List<String> = emptyList(),
    @SerializedName("runtime")     val runtime: String? = null,
    @SerializedName("videos")      val videos: List<VideoDto> = emptyList(),
)

// ─────────────────────────────────────────────
// META (single item detail)
// ─────────────────────────────────────────────

data class MetaResponse(
    @SerializedName("meta") val meta: MetaDto?,
)

data class VideoDto(
    @SerializedName("id")         val id: String = "",
    @SerializedName("title")      val title: String = "",
    @SerializedName("season")     val season: Int = 0,
    @SerializedName("episode")    val episode: Int = 0,
    @SerializedName("overview")   val overview: String = "",
    @SerializedName("thumbnail")  val thumbnail: String? = null,
    @SerializedName("released")   val released: String? = null,
)

// ─────────────────────────────────────────────
// STREAMS
// ─────────────────────────────────────────────

data class StreamResponse(
    @SerializedName("streams") val streams: List<StreamDto> = emptyList(),
)

data class StreamDto(
    @SerializedName("url")             val url: String? = null,
    @SerializedName("infoHash")        val infoHash: String? = null,
    @SerializedName("fileIdx")         val fileIdx: Int? = null,
    @SerializedName("title")           val title: String? = null,
    @SerializedName("name")            val name: String? = null,
    @SerializedName("description")     val description: String? = null,
    @SerializedName("behaviorHints")   val behaviorHints: BehaviorHintsDto? = null,
)

data class BehaviorHintsDto(
    @SerializedName("notWebReady") val notWebReady: Boolean = false,
    @SerializedName("bingeGroup")  val bingeGroup: String? = null,
)

// ─────────────────────────────────────────────
// ADDON MANIFEST
// ─────────────────────────────────────────────

data class AddonManifest(
    @SerializedName("id")          val id: String = "",
    @SerializedName("name")        val name: String = "",
    @SerializedName("version")     val version: String = "",
    @SerializedName("description") val description: String = "",
    @SerializedName("types")       val types: List<String> = emptyList(),
    @SerializedName("catalogs")    val catalogs: List<CatalogManifestDto> = emptyList(),
    @SerializedName("resources")   val resources: List<Any> = emptyList(),
)

data class CatalogManifestDto(
    @SerializedName("type")  val type: String = "",
    @SerializedName("id")    val id: String = "",
    @SerializedName("name")  val name: String = "",
    @SerializedName("extra") val extra: List<ExtraDto> = emptyList(),
)

data class ExtraDto(
    @SerializedName("name")     val name: String = "",
    @SerializedName("isRequired") val isRequired: Boolean = false,
    @SerializedName("options")  val options: List<String> = emptyList(),
)
