package com.merlottv.app.data.local.dao;

import androidx.room.*;
import com.merlottv.app.data.local.entity.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fH\'J\u0018\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fH\'J\u0014\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\r0\fH\'J\u001c\u0010\u0016\u001a\u00020\b2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010\u001a\u001a\u00020\u0011H\'J\u0016\u0010\u001b\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u001c"}, d2 = {"Lcom/merlottv/app/data/local/dao/ChannelDao;", "", "countBySource", "", "sourceId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBySource", "getAllChannels", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/merlottv/app/data/local/entity/ChannelEntity;", "getChannel", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChannelsForSource", "getFavouriteChannels", "getGroups", "insertAll", "channels", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchChannels", "query", "toggleFavourite", "app_debug"})
@androidx.room.Dao()
public abstract interface ChannelDao {
    
    @androidx.room.Query(value = "SELECT * FROM channels ORDER BY `group`, sortOrder, name")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.data.local.entity.ChannelEntity>> getAllChannels();
    
    @androidx.room.Query(value = "SELECT * FROM channels WHERE isFavourite = 1 ORDER BY name")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.data.local.entity.ChannelEntity>> getFavouriteChannels();
    
    @androidx.room.Query(value = "SELECT * FROM channels WHERE id = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getChannel(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.data.local.entity.ChannelEntity> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM channels\n        WHERE name LIKE \'%\' || :query || \'%\'\n           OR `group` LIKE \'%\' || :query || \'%\'\n        ORDER BY name\n        LIMIT 200\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.data.local.entity.ChannelEntity>> searchChannels(@org.jetbrains.annotations.NotNull()
    java.lang.String query);
    
    @androidx.room.Query(value = "SELECT DISTINCT `group` FROM channels ORDER BY `group`")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getGroups();
    
    @androidx.room.Query(value = "SELECT * FROM channels WHERE sourceId = :sourceId ORDER BY sortOrder")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.data.local.entity.ChannelEntity>> getChannelsForSource(long sourceId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.merlottv.app.data.local.entity.ChannelEntity> channels, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE channels SET isFavourite = NOT isFavourite WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object toggleFavourite(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM channels WHERE sourceId = :sourceId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteBySource(long sourceId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM channels")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM channels WHERE sourceId = :sourceId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object countBySource(long sourceId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}