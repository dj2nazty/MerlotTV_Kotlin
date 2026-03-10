package com.merlottv.app.data.network.api

import com.merlottv.app.data.network.dto.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

// ─────────────────────────────────────────────
// Cinemeta / Stremio Addon API
//
// Base URL pattern:  https://v3-cinemeta.strem.io
// ─────────────────────────────────────────────

interface CinemetaApi {

    /** Browse catalogue: /catalog/{type}/{id}.json */
    @GET("catalog/{type}/{id}.json")
    suspend fun getCatalog(
        @Path("type") type: String,      // "movie" or "series"
        @Path("id")   id: String,        // "top" | "popular" | "new" etc.
    ): CatalogResponse

    /** Filtered catalogue: /catalog/{type}/{id}/{extra}.json */
    @GET("catalog/{type}/{id}/{extra}.json")
    suspend fun getCatalogFiltered(
        @Path("type")  type: String,
        @Path("id")    id: String,
        @Path("extra") extra: String,    // e.g. "genre=Action&skip=100"
    ): CatalogResponse

    /** Full metadata: /meta/{type}/{id}.json */
    @GET("meta/{type}/{id}.json")
    suspend fun getMeta(
        @Path("type") type: String,
        @Path("id")   id: String,
    ): MetaResponse

    /** Search: /catalog/{type}/search={query}.json */
    @GET("catalog/{type}/search={query}.json")
    suspend fun search(
        @Path("type")  type: String,
        @Path("query") query: String,
    ): CatalogResponse
}

/**
 * Generic addon stream endpoint — called against any installed addon base URL.
 * Pattern: {addonBaseUrl}/stream/{type}/{id}.json
 */
interface StremioAddonApi {

    @GET("stream/{type}/{id}.json")
    suspend fun getStreams(
        @Path("type") type: String,
        @Path("id")   id: String,
    ): StreamResponse

    @GET("manifest.json")
    suspend fun getManifest(): AddonManifest

    /** Catalogue from arbitrary URL — used for installed addons */
    @GET
    suspend fun getCatalogByUrl(@Url url: String): CatalogResponse
}
