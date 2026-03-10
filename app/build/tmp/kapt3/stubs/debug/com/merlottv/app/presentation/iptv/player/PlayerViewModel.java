package com.merlottv.app.presentation.iptv.player;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.media3.common.Player;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.DefaultLoadControl;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import com.merlottv.app.domain.model.Channel;
import com.merlottv.app.domain.repository.AppSettings;
import com.merlottv.app.domain.repository.ChannelRepository;
import com.merlottv.app.domain.repository.SettingsRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import okhttp3.OkHttpClient;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u0017J\u000e\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$J\u0006\u0010%\u001a\u00020\u001fJ\u000e\u0010&\u001a\u00020\u001f2\u0006\u0010\'\u001a\u00020\u000bR\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/merlottv/app/presentation/iptv/player/PlayerViewModel;", "Landroidx/lifecycle/ViewModel;", "channelRepo", "Lcom/merlottv/app/domain/repository/ChannelRepository;", "settingsRepo", "Lcom/merlottv/app/domain/repository/SettingsRepository;", "okHttpClient", "Lokhttp3/OkHttpClient;", "(Lcom/merlottv/app/domain/repository/ChannelRepository;Lcom/merlottv/app/domain/repository/SettingsRepository;Lokhttp3/OkHttpClient;)V", "_currentChannel", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/merlottv/app/domain/model/Channel;", "_playerState", "Lcom/merlottv/app/presentation/iptv/player/PlayerState;", "_settings", "Lcom/merlottv/app/domain/repository/AppSettings;", "allChannels", "", "currentChannel", "Lkotlinx/coroutines/flow/StateFlow;", "getCurrentChannel", "()Lkotlinx/coroutines/flow/StateFlow;", "currentIndex", "", "playerState", "getPlayerState", "buildPlayer", "Landroidx/media3/exoplayer/ExoPlayer;", "context", "Landroid/content/Context;", "nextChannel", "", "onPlaybackStateChanged", "state", "onPlayerError", "message", "", "previousChannel", "setChannel", "channel", "app_debug"})
@androidx.media3.common.util.UnstableApi()
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PlayerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.ChannelRepository channelRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.SettingsRepository settingsRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final okhttp3.OkHttpClient okHttpClient = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.merlottv.app.domain.model.Channel> _currentChannel = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.merlottv.app.domain.model.Channel> currentChannel = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.merlottv.app.presentation.iptv.player.PlayerState> _playerState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.merlottv.app.presentation.iptv.player.PlayerState> playerState = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.merlottv.app.domain.model.Channel> allChannels;
    private int currentIndex = -1;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.merlottv.app.domain.repository.AppSettings> _settings = null;
    
    @javax.inject.Inject()
    public PlayerViewModel(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.ChannelRepository channelRepo, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.SettingsRepository settingsRepo, @org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttpClient) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.merlottv.app.domain.model.Channel> getCurrentChannel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.merlottv.app.presentation.iptv.player.PlayerState> getPlayerState() {
        return null;
    }
    
    public final void setChannel(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.Channel channel) {
    }
    
    public final void nextChannel() {
    }
    
    public final void previousChannel() {
    }
    
    public final void onPlaybackStateChanged(int state) {
    }
    
    public final void onPlayerError(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.media3.exoplayer.ExoPlayer buildPlayer(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}