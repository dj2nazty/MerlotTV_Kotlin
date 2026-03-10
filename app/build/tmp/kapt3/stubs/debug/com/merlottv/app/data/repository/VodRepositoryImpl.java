package com.merlottv.app.data.repository;

import com.merlottv.app.data.local.dao.StremioAddonDao;
import com.merlottv.app.data.local.entity.StremioAddonEntity;
import com.merlottv.app.data.network.api.CinemetaApi;
import com.merlottv.app.data.network.api.StremioAddonApi;
import com.merlottv.app.data.network.dto.*;
import com.merlottv.app.domain.model.*;
import com.merlottv.app.domain.repository.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ<\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J*\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\r0\f2\u0006\u0010\u0019\u001a\u00020\u0012H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0014\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\r0\u001dH\u0016J,\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\f2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b!\u0010\"JF\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\r0\f2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010%\u001a\u0004\u0018\u00010\u00142\b\u0010&\u001a\u0004\u0018\u00010\u0014H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\'\u0010(J$\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001e0\f2\u0006\u0010*\u001a\u00020\u0012H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b+\u0010\u001bJ\u0016\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0012H\u0096@\u00a2\u0006\u0002\u0010\u001bJ*\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u00100\u001a\u00020\u0012H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b1\u0010\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u00062"}, d2 = {"Lcom/merlottv/app/data/repository/VodRepositoryImpl;", "Lcom/merlottv/app/domain/repository/VodRepository;", "cinemetaApi", "Lcom/merlottv/app/data/network/api/CinemetaApi;", "addonApi", "Lcom/merlottv/app/data/network/api/StremioAddonApi;", "addonDao", "Lcom/merlottv/app/data/local/dao/StremioAddonDao;", "gson", "Lcom/google/gson/Gson;", "(Lcom/merlottv/app/data/network/api/CinemetaApi;Lcom/merlottv/app/data/network/api/StremioAddonApi;Lcom/merlottv/app/data/local/dao/StremioAddonDao;Lcom/google/gson/Gson;)V", "getCatalogue", "Lkotlin/Result;", "", "Lcom/merlottv/app/domain/model/VodItem;", "type", "Lcom/merlottv/app/domain/model/VodType;", "genre", "", "page", "", "getCatalogue-BWLJW6A", "(Lcom/merlottv/app/domain/model/VodType;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEpisodes", "Lcom/merlottv/app/domain/model/Episode;", "seriesId", "getEpisodes-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInstalledAddons", "Lkotlinx/coroutines/flow/Flow;", "Lcom/merlottv/app/domain/repository/StremioAddon;", "getMeta", "id", "getMeta-0E7RQCE", "(Ljava/lang/String;Lcom/merlottv/app/domain/model/VodType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStreams", "Lcom/merlottv/app/domain/model/VodStream;", "season", "episode", "getStreams-yxL6bBk", "(Ljava/lang/String;Lcom/merlottv/app/domain/model/VodType;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "installAddon", "manifestUrl", "installAddon-gIAlu-s", "removeAddon", "", "addonId", "search", "query", "search-gIAlu-s", "app_debug"})
public final class VodRepositoryImpl implements com.merlottv.app.domain.repository.VodRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.data.network.api.CinemetaApi cinemetaApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.data.network.api.StremioAddonApi addonApi = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.data.local.dao.StremioAddonDao addonDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.gson.Gson gson = null;
    
    @javax.inject.Inject()
    public VodRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.network.api.CinemetaApi cinemetaApi, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.network.api.StremioAddonApi addonApi, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.dao.StremioAddonDao addonDao, @org.jetbrains.annotations.NotNull()
    com.google.gson.Gson gson) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.repository.StremioAddon>> getInstalledAddons() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removeAddon(@org.jetbrains.annotations.NotNull()
    java.lang.String addonId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}