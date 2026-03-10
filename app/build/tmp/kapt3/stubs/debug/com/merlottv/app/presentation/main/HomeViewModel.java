package com.merlottv.app.presentation.main;

import androidx.lifecycle.ViewModel;
import com.merlottv.app.domain.model.*;
import com.merlottv.app.domain.repository.ChannelRepository;
import com.merlottv.app.domain.repository.VodRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\u0017H\u0002R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R/\u0010\u000b\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\t0\r0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/merlottv/app/presentation/main/HomeViewModel;", "Landroidx/lifecycle/ViewModel;", "channelRepo", "Lcom/merlottv/app/domain/repository/ChannelRepository;", "vodRepo", "Lcom/merlottv/app/domain/repository/VodRepository;", "(Lcom/merlottv/app/domain/repository/ChannelRepository;Lcom/merlottv/app/domain/repository/VodRepository;)V", "_vodMovies", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/merlottv/app/domain/model/VodItem;", "channelGroups", "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlin/Pair;", "", "Lcom/merlottv/app/domain/model/Channel;", "getChannelGroups", "()Lkotlinx/coroutines/flow/StateFlow;", "favourites", "getFavourites", "vodMovies", "getVodMovies", "loadVodCatalogue", "", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class HomeViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.ChannelRepository channelRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.VodRepository vodRepo = null;
    
    /**
     * Channel groups for sidebar rows (max 10 shown)
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<kotlin.Pair<java.lang.String, java.util.List<com.merlottv.app.domain.model.Channel>>>> channelGroups = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.Channel>> favourites = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.merlottv.app.domain.model.VodItem>> _vodMovies = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.VodItem>> vodMovies = null;
    
    @javax.inject.Inject()
    public HomeViewModel(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.ChannelRepository channelRepo, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.VodRepository vodRepo) {
        super();
    }
    
    /**
     * Channel groups for sidebar rows (max 10 shown)
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<kotlin.Pair<java.lang.String, java.util.List<com.merlottv.app.domain.model.Channel>>>> getChannelGroups() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.Channel>> getFavourites() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.VodItem>> getVodMovies() {
        return null;
    }
    
    private final void loadVodCatalogue() {
    }
}