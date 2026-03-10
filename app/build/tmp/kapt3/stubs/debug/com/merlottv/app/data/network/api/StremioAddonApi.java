package com.merlottv.app.data.network.api;

import com.merlottv.app.data.network.dto.*;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Generic addon stream endpoint — called against any installed addon base URL.
 * Pattern: {addonBaseUrl}/stream/{type}/{id}.json
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\"\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u00052\b\b\u0001\u0010\r\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/merlottv/app/data/network/api/StremioAddonApi;", "", "getCatalogByUrl", "Lcom/merlottv/app/data/network/dto/CatalogResponse;", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getManifest", "Lcom/merlottv/app/data/network/dto/AddonManifest;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStreams", "Lcom/merlottv/app/data/network/dto/StreamResponse;", "type", "id", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface StremioAddonApi {
    
    @retrofit2.http.GET(value = "stream/{type}/{id}.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStreams(@retrofit2.http.Path(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.data.network.dto.StreamResponse> $completion);
    
    @retrofit2.http.GET(value = "manifest.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getManifest(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.data.network.dto.AddonManifest> $completion);
    
    /**
     * Catalogue from arbitrary URL — used for installed addons
     */
    @retrofit2.http.GET()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCatalogByUrl(@retrofit2.http.Url()
    @org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.data.network.dto.CatalogResponse> $completion);
}