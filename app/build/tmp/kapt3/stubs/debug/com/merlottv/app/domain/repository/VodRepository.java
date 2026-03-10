package com.merlottv.app.domain.repository;

import com.merlottv.app.domain.model.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\f\u0010\rJ*\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00040\u00032\u0006\u0010\u0010\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0011\u0010\u0012J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00040\u0014H&J,\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019JJ\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00040\u00032\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u000bH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001e\u0010\u001fJ$\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00150\u00032\u0006\u0010!\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\"\u0010\u0012J\u0016\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\tH\u00a6@\u00a2\u0006\u0002\u0010\u0012J*\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u0006\u0010\'\u001a\u00020\tH\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b(\u0010\u0012\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006)"}, d2 = {"Lcom/merlottv/app/domain/repository/VodRepository;", "", "getCatalogue", "Lkotlin/Result;", "", "Lcom/merlottv/app/domain/model/VodItem;", "type", "Lcom/merlottv/app/domain/model/VodType;", "genre", "", "page", "", "getCatalogue-BWLJW6A", "(Lcom/merlottv/app/domain/model/VodType;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getEpisodes", "Lcom/merlottv/app/domain/model/Episode;", "seriesId", "getEpisodes-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInstalledAddons", "Lkotlinx/coroutines/flow/Flow;", "Lcom/merlottv/app/domain/repository/StremioAddon;", "getMeta", "id", "getMeta-0E7RQCE", "(Ljava/lang/String;Lcom/merlottv/app/domain/model/VodType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStreams", "Lcom/merlottv/app/domain/model/VodStream;", "season", "episode", "getStreams-yxL6bBk", "(Ljava/lang/String;Lcom/merlottv/app/domain/model/VodType;Ljava/lang/Integer;Ljava/lang/Integer;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "installAddon", "manifestUrl", "installAddon-gIAlu-s", "removeAddon", "", "addonId", "search", "query", "search-gIAlu-s", "app_debug"})
public abstract interface VodRepository {
    
    /**
     * Addon management
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.repository.StremioAddon>> getInstalledAddons();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeAddon(@org.jetbrains.annotations.NotNull()
    java.lang.String addonId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}