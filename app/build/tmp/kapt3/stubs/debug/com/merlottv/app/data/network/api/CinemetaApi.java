package com.merlottv.app.data.network.api;

import com.merlottv.app.data.network.dto.*;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J,\u0010\b\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u00052\b\b\u0001\u0010\t\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\nJ\"\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\"\u0010\r\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0001\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\u000f"}, d2 = {"Lcom/merlottv/app/data/network/api/CinemetaApi;", "", "getCatalog", "Lcom/merlottv/app/data/network/dto/CatalogResponse;", "type", "", "id", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCatalogFiltered", "extra", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMeta", "Lcom/merlottv/app/data/network/dto/MetaResponse;", "search", "query", "app_debug"})
public abstract interface CinemetaApi {
    
    /**
     * Browse catalogue: /catalog/{type}/{id}.json
     */
    @retrofit2.http.GET(value = "catalog/{type}/{id}.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCatalog(@retrofit2.http.Path(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.data.network.dto.CatalogResponse> $completion);
    
    /**
     * Filtered catalogue: /catalog/{type}/{id}/{extra}.json
     */
    @retrofit2.http.GET(value = "catalog/{type}/{id}/{extra}.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCatalogFiltered(@retrofit2.http.Path(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @retrofit2.http.Path(value = "extra")
    @org.jetbrains.annotations.NotNull()
    java.lang.String extra, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.data.network.dto.CatalogResponse> $completion);
    
    /**
     * Full metadata: /meta/{type}/{id}.json
     */
    @retrofit2.http.GET(value = "meta/{type}/{id}.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getMeta(@retrofit2.http.Path(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.data.network.dto.MetaResponse> $completion);
    
    /**
     * Search: /catalog/{type}/search={query}.json
     */
    @retrofit2.http.GET(value = "catalog/{type}/search={query}.json")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object search(@retrofit2.http.Path(value = "type")
    @org.jetbrains.annotations.NotNull()
    java.lang.String type, @retrofit2.http.Path(value = "query")
    @org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.data.network.dto.CatalogResponse> $completion);
}