package com.merlottv.app.presentation.checker;

import android.content.Context;
import android.content.Intent;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModel;
import com.merlottv.app.domain.model.CheckResult;
import com.merlottv.app.domain.model.StreamStatus;
import com.merlottv.app.domain.repository.ChannelRepository;
import com.merlottv.app.domain.repository.CheckerRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u001cJ\u000e\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u001cJ\u0006\u0010\"\u001a\u00020\u001cJ\u0006\u0010#\u001a\u00020\u001cR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00100\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0016R#\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110\u00100\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0016R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016\u00a8\u0006$"}, d2 = {"Lcom/merlottv/app/presentation/checker/CheckerViewModel;", "Landroidx/lifecycle/ViewModel;", "channelRepo", "Lcom/merlottv/app/domain/repository/ChannelRepository;", "checkerRepo", "Lcom/merlottv/app/domain/repository/CheckerRepository;", "(Lcom/merlottv/app/domain/repository/ChannelRepository;Lcom/merlottv/app/domain/repository/CheckerRepository;)V", "_allResults", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/merlottv/app/domain/model/CheckResult;", "_filter", "Lcom/merlottv/app/domain/model/StreamStatus;", "_isChecking", "", "_progress", "Lkotlin/Pair;", "", "checkJob", "Lkotlinx/coroutines/Job;", "isChecking", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "progress", "getProgress", "results", "getResults", "checkAll", "", "clearFilter", "exportResults", "context", "Landroid/content/Context;", "filterOffline", "filterOnline", "stopCheck", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CheckerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.ChannelRepository channelRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.CheckerRepository checkerRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.merlottv.app.domain.model.CheckResult>> _allResults = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.merlottv.app.domain.model.StreamStatus> _filter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isChecking = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<kotlin.Pair<java.lang.Integer, java.lang.Integer>> _progress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isChecking = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<kotlin.Pair<java.lang.Integer, java.lang.Integer>> progress = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.CheckResult>> results = null;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job checkJob;
    
    @javax.inject.Inject()
    public CheckerViewModel(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.ChannelRepository channelRepo, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.CheckerRepository checkerRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isChecking() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<kotlin.Pair<java.lang.Integer, java.lang.Integer>> getProgress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.CheckResult>> getResults() {
        return null;
    }
    
    public final void checkAll() {
    }
    
    public final void stopCheck() {
    }
    
    public final void filterOnline() {
    }
    
    public final void filterOffline() {
    }
    
    public final void clearFilter() {
    }
    
    public final void exportResults(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}