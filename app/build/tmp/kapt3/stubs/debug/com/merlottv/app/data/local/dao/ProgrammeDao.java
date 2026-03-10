package com.merlottv.app.data.local.dao;

import androidx.room.*;
import com.merlottv.app.data.local.entity.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ \u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\'J$\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00100\n2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007H\'J,\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00100\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007H\'J\u001c\u0010\u0014\u001a\u00020\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0010H\u00a7@\u00a2\u0006\u0002\u0010\u0016\u00a8\u0006\u0017"}, d2 = {"Lcom/merlottv/app/data/local/dao/ProgrammeDao;", "", "deleteAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOlderThan", "cutoff", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentProgramme", "Lkotlinx/coroutines/flow/Flow;", "Lcom/merlottv/app/data/local/entity/ProgrammeEntity;", "channelId", "", "now", "getGuideForDay", "", "startOfDay", "endOfDay", "getProgrammesForChannel", "insertAll", "programmes", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ProgrammeDao {
    
    @androidx.room.Query(value = "\n        SELECT * FROM programmes\n        WHERE channelId = :channelId\n          AND endTime >= :startOfDay\n          AND startTime < :endOfDay\n        ORDER BY startTime\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.data.local.entity.ProgrammeEntity>> getProgrammesForChannel(@org.jetbrains.annotations.NotNull()
    java.lang.String channelId, long startOfDay, long endOfDay);
    
    @androidx.room.Query(value = "\n        SELECT * FROM programmes\n        WHERE channelId = :channelId\n          AND startTime <= :now\n          AND endTime   >  :now\n        LIMIT 1\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.merlottv.app.data.local.entity.ProgrammeEntity> getCurrentProgramme(@org.jetbrains.annotations.NotNull()
    java.lang.String channelId, long now);
    
    @androidx.room.Query(value = "\n        SELECT * FROM programmes\n        WHERE endTime >= :startOfDay\n          AND startTime < :endOfDay\n        ORDER BY channelId, startTime\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.data.local.entity.ProgrammeEntity>> getGuideForDay(long startOfDay, long endOfDay);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.merlottv.app.data.local.entity.ProgrammeEntity> programmes, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    /**
     * Remove stale data older than [cutoff] epoch-millis
     */
    @androidx.room.Query(value = "DELETE FROM programmes WHERE endTime < :cutoff")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOlderThan(long cutoff, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM programmes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}