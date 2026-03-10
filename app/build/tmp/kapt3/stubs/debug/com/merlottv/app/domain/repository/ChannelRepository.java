package com.merlottv.app.domain.repository;

import com.merlottv.app.domain.model.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH&J\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\t0\bH&J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH&J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\t0\bH&J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0016\u0010\u0017J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u00142\u0006\u0010\f\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u001a\u0010\u001bJ\u0016\u0010\u001c\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u001bJ\u001c\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u001e\u001a\u00020\rH&J\u0016\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\rH\u00a6@\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006!"}, d2 = {"Lcom/merlottv/app/domain/repository/ChannelRepository;", "", "addSource", "", "source", "Lcom/merlottv/app/domain/model/PlaylistSource;", "(Lcom/merlottv/app/domain/model/PlaylistSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllChannels", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/merlottv/app/domain/model/Channel;", "getChannel", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChannelGroups", "Lcom/merlottv/app/domain/model/ChannelGroup;", "getFavouriteChannels", "getSources", "refreshAllSources", "Lkotlin/Result;", "", "refreshAllSources-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshSource", "", "refreshSource-gIAlu-s", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeSource", "searchChannels", "query", "toggleFavourite", "channelId", "app_debug"})
public abstract interface ChannelRepository {
    
    /**
     * All saved playlist sources
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.PlaylistSource>> getSources();
    
    /**
     * All channels across all active sources, grouped
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.ChannelGroup>> getChannelGroups();
    
    /**
     * Flat list — used by the player and checker
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.Channel>> getAllChannels();
    
    /**
     * Favourite channels only
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.Channel>> getFavouriteChannels();
    
    /**
     * Search by name / group
     */
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.Channel>> searchChannels(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addSource(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.PlaylistSource source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeSource(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object toggleFavourite(@org.jetbrains.annotations.NotNull()
    java.lang.String channelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getChannel(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.domain.model.Channel> $completion);
}