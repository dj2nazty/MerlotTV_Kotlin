package com.merlottv.app.data.repository;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.*;
import com.merlottv.app.data.local.dao.EpgSourceDao;
import com.merlottv.app.data.local.dao.ProgrammeDao;
import com.merlottv.app.data.local.entity.EpgSourceEntity;
import com.merlottv.app.data.local.entity.ProgrammeEntity;
import com.merlottv.app.data.parser.XmltvParser;
import com.merlottv.app.domain.model.*;
import com.merlottv.app.domain.repository.*;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import java.util.Calendar;
import javax.inject.Inject;
import javax.inject.Singleton;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0096@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\u0012\u001a\u00020\fH\u0002J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0017H\u0082@\u00a2\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001a2\u0006\u0010\u001b\u001a\u00020\u0017H\u0016J\u0014\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00140\u001aH\u0016J(\u0010\u001d\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u001e0\u001a2\u0006\u0010\u0012\u001a\u00020\fH\u0016J$\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u001a2\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\fH\u0016J\u001c\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b#\u0010$J$\u0010%\u001a\b\u0012\u0004\u0012\u00020\"0!2\u0006\u0010&\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\'\u0010(J\u0016\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010(R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006+"}, d2 = {"Lcom/merlottv/app/data/repository/EpgRepositoryImpl;", "Lcom/merlottv/app/domain/repository/EpgRepository;", "epgSourceDao", "Lcom/merlottv/app/data/local/dao/EpgSourceDao;", "programmeDao", "Lcom/merlottv/app/data/local/dao/ProgrammeDao;", "parser", "Lcom/merlottv/app/data/parser/XmltvParser;", "okHttp", "Lokhttp3/OkHttpClient;", "(Lcom/merlottv/app/data/local/dao/EpgSourceDao;Lcom/merlottv/app/data/local/dao/ProgrammeDao;Lcom/merlottv/app/data/parser/XmltvParser;Lokhttp3/OkHttpClient;)V", "addEpgSource", "", "source", "Lcom/merlottv/app/domain/model/EpgSource;", "(Lcom/merlottv/app/domain/model/EpgSource;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "dayBounds", "Lkotlin/Pair;", "date", "downloadAndParse", "", "Lcom/merlottv/app/domain/model/Programme;", "url", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrentProgramme", "Lkotlinx/coroutines/flow/Flow;", "channelId", "getEpgSources", "getGuideForDate", "", "getProgrammesForChannel", "refreshAllEpg", "Lkotlin/Result;", "", "refreshAllEpg-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "refreshEpg", "sourceId", "refreshEpg-gIAlu-s", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeEpgSource", "id", "app_debug"})
public final class EpgRepositoryImpl implements com.merlottv.app.domain.repository.EpgRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.data.local.dao.EpgSourceDao epgSourceDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.data.local.dao.ProgrammeDao programmeDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.data.parser.XmltvParser parser = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient okHttp = null;
    
    @javax.inject.Inject()
    public EpgRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.dao.EpgSourceDao epgSourceDao, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.dao.ProgrammeDao programmeDao, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.parser.XmltvParser parser, @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttp) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.EpgSource>> getEpgSources() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.Programme>> getProgrammesForChannel(@org.jetbrains.annotations.NotNull()
    java.lang.String channelId, long date) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.merlottv.app.domain.model.Programme> getCurrentProgramme(@org.jetbrains.annotations.NotNull()
    java.lang.String channelId) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.Map<java.lang.String, java.util.List<com.merlottv.app.domain.model.Programme>>> getGuideForDate(long date) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addEpgSource(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.EpgSource source, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object removeEpgSource(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object downloadAndParse(java.lang.String url, kotlin.coroutines.Continuation<? super java.util.List<com.merlottv.app.domain.model.Programme>> $completion) {
        return null;
    }
    
    private final kotlin.Pair<java.lang.Long, java.lang.Long> dayBounds(long date) {
        return null;
    }
}