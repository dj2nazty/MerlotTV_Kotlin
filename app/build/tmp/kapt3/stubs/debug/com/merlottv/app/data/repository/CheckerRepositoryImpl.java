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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007H\u0096@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\fH\u0096@\u00a2\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\nH\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lcom/merlottv/app/data/repository/CheckerRepositoryImpl;", "Lcom/merlottv/app/domain/repository/CheckerRepository;", "okHttp", "Lokhttp3/OkHttpClient;", "(Lokhttp3/OkHttpClient;)V", "lastResults", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/merlottv/app/domain/model/CheckResult;", "checkAll", "Lkotlinx/coroutines/flow/Flow;", "channels", "Lcom/merlottv/app/domain/model/Channel;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkChannel", "channel", "(Lcom/merlottv/app/domain/model/Channel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastResults", "app_debug"})
public final class CheckerRepositoryImpl implements com.merlottv.app.domain.repository.CheckerRepository {
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient okHttp = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.merlottv.app.domain.model.CheckResult>> lastResults = null;
    
    @javax.inject.Inject()
    public CheckerRepositoryImpl(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttp) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object checkChannel(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.Channel channel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.domain.model.CheckResult> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object checkAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.merlottv.app.domain.model.Channel> channels, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.merlottv.app.domain.model.CheckResult>> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.CheckResult>> getLastResults() {
        return null;
    }
}