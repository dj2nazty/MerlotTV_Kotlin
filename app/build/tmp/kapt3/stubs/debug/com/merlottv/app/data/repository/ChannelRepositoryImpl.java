package com.merlottv.app.data.repository;

import com.merlottv.app.data.local.dao.ChannelDao;
import com.merlottv.app.data.local.dao.PlaylistSourceDao;
import com.merlottv.app.data.local.entity.ChannelEntity;
import com.merlottv.app.data.local.entity.PlaylistSourceEntity;
import com.merlottv.app.data.parser.M3uParser;
import com.merlottv.app.domain.model.*;
import com.merlottv.app.domain.repository.ChannelRepository;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0082@\u00a2\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0017H\u0016J\u0018\u0010\u0018\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0019\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0015J\u0014\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00110\u0017H\u0016J\u0014\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0017H\u0016J\u0014\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00110\u0017H\u0016J\u001c\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b!\u0010\"J$\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001f2\u0006\u0010\u0019\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b%\u0010&J\u0016\u0010\'\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010&J\u001c\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00172\u0006\u0010)\u001a\u00020\u0014H\u0016J\u0016\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u0014H\u0096@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006,"}, d2 = {"Lcom/merlottv/app/data/repository/ChannelRepositoryImpl;", "Lcom/merlottv/app/domain/repository/ChannelRepository;", "channelDao", "Lcom/merlottv/app/data/local/dao/ChannelDao;", "sourceDao", "Lcom/merlottv/app/data/local/dao/PlaylistSourceDao;", "parser", "Lcom/merlottv/app/data/parser/M3uParser;", "okHttp", "Lokhttp3/OkHttpClient;", "(Lcom/merlottv/app/data/local/dao/ChannelDao;Lcom/merlottv/app/data/local/dao/PlaylistSourceDao;Lcom/merlottv/app/data/parser/M3uParser;Lokhttp3/OkHttpClient;)V", "addSource", "", "source", "Lcom/merlottv/app/domain/model/PlaylistSource;", "(Lcom/merlottv/app/domain/model/PlaylistSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadAndParse", "", "Lcom/merlottv/app/domain/model/Channel;", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllChannels", "Lkotlinx/coroutines/flow/Flow;", "getChannel", "id", "getChannelGroups", "Lcom/merlottv/app/domain/model/ChannelGroup;", "getFavouriteChannels", "getSources", "refreshAllSources", "Lkotlin/Result;", "", "refreshAllSources-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshSource", "", "refreshSource-gIAlu-s", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeSource", "searchChannels", "query", "toggleFavourite", "channelId", "app_debug"})
public final class ChannelRepositoryImpl implements com.merlottv.app.domain.repository.ChannelRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.data.local.dao.ChannelDao channelDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.data.local.dao.PlaylistSourceDao sourceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.data.parser.M3uParser parser = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient okHttp = null;
    
    @javax.inject.Inject()
    public ChannelRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.dao.ChannelDao channelDao, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.dao.PlaylistSourceDao sourceDao, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.parser.M3uParser parser, @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttp) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.PlaylistSource>> getSources() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.ChannelGroup>> getChannelGroups() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.Channel>> getAllChannels() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.Channel>> getFavouriteChannels() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.Channel>> searchChannels(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object getChannel(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.domain.model.Channel> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addSource(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.PlaylistSource source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removeSource(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object toggleFavourite(@org.jetbrains.annotations.NotNull()
    java.lang.String channelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object downloadAndParse(java.lang.String url, kotlin.coroutines.Continuation<? super java.util.List<com.merlottv.app.domain.model.Channel>> $completion) {
        return null;
    }
}