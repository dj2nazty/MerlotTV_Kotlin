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

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0005\b\t\n\u000b\f\u00a8\u0006\r"}, d2 = {"Lcom/merlottv/app/presentation/iptv/player/PlayerState;", "", "()V", "Buffering", "Ended", "Error", "Idle", "Ready", "Lcom/merlottv/app/presentation/iptv/player/PlayerState$Buffering;", "Lcom/merlottv/app/presentation/iptv/player/PlayerState$Ended;", "Lcom/merlottv/app/presentation/iptv/player/PlayerState$Error;", "Lcom/merlottv/app/presentation/iptv/player/PlayerState$Idle;", "Lcom/merlottv/app/presentation/iptv/player/PlayerState$Ready;", "app_debug"})
public abstract class PlayerState {
    
    private PlayerState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/merlottv/app/presentation/iptv/player/PlayerState$Buffering;", "Lcom/merlottv/app/presentation/iptv/player/PlayerState;", "()V", "app_debug"})
    public static final class Buffering extends com.merlottv.app.presentation.iptv.player.PlayerState {
        @org.jetbrains.annotations.NotNull()
        public static final com.merlottv.app.presentation.iptv.player.PlayerState.Buffering INSTANCE = null;
        
        private Buffering() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/merlottv/app/presentation/iptv/player/PlayerState$Ended;", "Lcom/merlottv/app/presentation/iptv/player/PlayerState;", "()V", "app_debug"})
    public static final class Ended extends com.merlottv.app.presentation.iptv.player.PlayerState {
        @org.jetbrains.annotations.NotNull()
        public static final com.merlottv.app.presentation.iptv.player.PlayerState.Ended INSTANCE = null;
        
        private Ended() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/merlottv/app/presentation/iptv/player/PlayerState$Error;", "Lcom/merlottv/app/presentation/iptv/player/PlayerState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
    public static final class Error extends com.merlottv.app.presentation.iptv.player.PlayerState {
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String message = null;
        
        public Error(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getMessage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.merlottv.app.presentation.iptv.player.PlayerState.Error copy(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/merlottv/app/presentation/iptv/player/PlayerState$Idle;", "Lcom/merlottv/app/presentation/iptv/player/PlayerState;", "()V", "app_debug"})
    public static final class Idle extends com.merlottv.app.presentation.iptv.player.PlayerState {
        @org.jetbrains.annotations.NotNull()
        public static final com.merlottv.app.presentation.iptv.player.PlayerState.Idle INSTANCE = null;
        
        private Idle() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/merlottv/app/presentation/iptv/player/PlayerState$Ready;", "Lcom/merlottv/app/presentation/iptv/player/PlayerState;", "()V", "app_debug"})
    public static final class Ready extends com.merlottv.app.presentation.iptv.player.PlayerState {
        @org.jetbrains.annotations.NotNull()
        public static final com.merlottv.app.presentation.iptv.player.PlayerState.Ready INSTANCE = null;
        
        private Ready() {
        }
    }
}