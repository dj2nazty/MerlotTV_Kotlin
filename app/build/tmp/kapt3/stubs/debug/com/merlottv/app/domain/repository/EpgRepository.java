package com.merlottv.app.domain.repository;

import com.merlottv.app.domain.model.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\b2\u0006\u0010\n\u001a\u00020\u000bH&J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\bH&J(\u0010\u000e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\u000f0\b2\u0006\u0010\u0010\u001a\u00020\u0003H&J$\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\r0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0003H&J\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0015\u0010\u0016J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0018\u001a\u00020\u0003H\u00a6@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0019\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u001a\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001d"}, d2 = {"Lcom/merlottv/app/domain/repository/EpgRepository;", "", "addEpgSource", "", "source", "Lcom/merlottv/app/domain/model/EpgSource;", "(Lcom/merlottv/app/domain/model/EpgSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentProgramme", "Lkotlinx/coroutines/flow/Flow;", "Lcom/merlottv/app/domain/model/Programme;", "channelId", "", "getEpgSources", "", "getGuideForDate", "", "date", "getProgrammesForChannel", "refreshAllEpg", "Lkotlin/Result;", "", "refreshAllEpg-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshEpg", "sourceId", "refreshEpg-gIAlu-s", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeEpgSource", "id", "app_debug"})
public abstract interface EpgRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.EpgSource>> getEpgSources();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.Programme>> getProgrammesForChannel(@org.jetbrains.annotations.NotNull()
    java.lang.String channelId, long date);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.merlottv.app.domain.model.Programme> getCurrentProgramme(@org.jetbrains.annotations.NotNull()
    java.lang.String channelId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.Map<java.lang.String, java.util.List<com.merlottv.app.domain.model.Programme>>> getGuideForDate(long date);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addEpgSource(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.EpgSource source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeEpgSource(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}