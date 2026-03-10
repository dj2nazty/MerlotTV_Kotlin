package com.merlottv.app.presentation.guide;

import androidx.lifecycle.ViewModel;
import com.merlottv.app.domain.model.Channel;
import com.merlottv.app.domain.repository.ChannelRepository;
import com.merlottv.app.domain.repository.EpgRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import java.util.Calendar;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 \'2\u00020\u0001:\u0001\'B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010!\u001a\u00020\"J\u0006\u0010#\u001a\u00020\"J\u000e\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\fJ\u0006\u0010&\u001a\u00020\"R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019\u00a8\u0006("}, d2 = {"Lcom/merlottv/app/presentation/guide/GuideViewModel;", "Landroidx/lifecycle/ViewModel;", "channelRepo", "Lcom/merlottv/app/domain/repository/ChannelRepository;", "epgRepo", "Lcom/merlottv/app/domain/repository/EpgRepository;", "(Lcom/merlottv/app/domain/repository/ChannelRepository;Lcom/merlottv/app/domain/repository/EpgRepository;)V", "_isLoading", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_playChannelEvent", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/merlottv/app/domain/model/Channel;", "_selectedDate", "", "currentTimeOffsetPx", "Lkotlinx/coroutines/flow/Flow;", "", "getCurrentTimeOffsetPx", "()Lkotlinx/coroutines/flow/Flow;", "guideRows", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/merlottv/app/presentation/guide/GuideRow;", "getGuideRows", "()Lkotlinx/coroutines/flow/StateFlow;", "isLoading", "playChannelEvent", "Lkotlinx/coroutines/flow/SharedFlow;", "getPlayChannelEvent", "()Lkotlinx/coroutines/flow/SharedFlow;", "selectedDate", "getSelectedDate", "goToToday", "", "nextDay", "playChannel", "channel", "previousDay", "Companion", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class GuideViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.ChannelRepository channelRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.EpgRepository epgRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Long> _selectedDate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Long> selectedDate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.presentation.guide.GuideRow>> guideRows = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> currentTimeOffsetPx = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.merlottv.app.domain.model.Channel> _playChannelEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.merlottv.app.domain.model.Channel> playChannelEvent = null;
    private static final long DAY_MS = 86400000L;
    public static final int PX_PER_MINUTE = 4;
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.presentation.guide.GuideViewModel.Companion Companion = null;
    
    @javax.inject.Inject()
    public GuideViewModel(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.ChannelRepository channelRepo, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.EpgRepository epgRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Long> getSelectedDate() {
        return null;
    }
    
    public final void previousDay() {
    }
    
    public final void nextDay() {
    }
    
    public final void goToToday() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.presentation.guide.GuideRow>> getGuideRows() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getCurrentTimeOffsetPx() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.merlottv.app.domain.model.Channel> getPlayChannelEvent() {
        return null;
    }
    
    public final void playChannel(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.Channel channel) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/merlottv/app/presentation/guide/GuideViewModel$Companion;", "", "()V", "DAY_MS", "", "PX_PER_MINUTE", "", "todayMidnight", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final long todayMidnight() {
            return 0L;
        }
    }
}