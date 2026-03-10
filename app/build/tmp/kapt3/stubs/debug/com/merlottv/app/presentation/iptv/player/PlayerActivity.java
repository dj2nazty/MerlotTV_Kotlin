package com.merlottv.app.presentation.iptv.player;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.Player;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.exoplayer.ExoPlayer;
import com.merlottv.app.databinding.ActivityPlayerBinding;
import com.merlottv.app.domain.model.Channel;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\b\u0007\u0018\u0000 *2\u00020\u0001:\u0001*B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0015H\u0002J\u0012\u0010\u001a\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0015H\u0014J\u001a\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010#\u001a\u00020\u0015H\u0014J\b\u0010$\u001a\u00020\u0015H\u0014J\b\u0010%\u001a\u00020\u0015H\u0002J\b\u0010&\u001a\u00020\u0015H\u0002J\u0012\u0010\'\u001a\u00020\u00152\b\b\u0002\u0010(\u001a\u00020)H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006+"}, d2 = {"Lcom/merlottv/app/presentation/iptv/player/PlayerActivity;", "Landroidx/fragment/app/FragmentActivity;", "()V", "binding", "Lcom/merlottv/app/databinding/ActivityPlayerBinding;", "hideControlsJob", "Lkotlinx/coroutines/Job;", "maxRetries", "", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "playerListener", "Landroidx/media3/common/Player$Listener;", "retryCount", "viewModel", "Lcom/merlottv/app/presentation/iptv/player/PlayerViewModel;", "getViewModel", "()Lcom/merlottv/app/presentation/iptv/player/PlayerViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "hideControls", "", "initPlayer", "channel", "Lcom/merlottv/app/domain/model/Channel;", "observeState", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onKeyDown", "", "keyCode", "event", "Landroid/view/KeyEvent;", "onStart", "onStop", "releasePlayer", "retryPlayback", "showControls", "autoHideMs", "", "Companion", "app_debug"})
@androidx.media3.common.util.UnstableApi()
public final class PlayerActivity extends androidx.fragment.app.FragmentActivity {
    private com.merlottv.app.databinding.ActivityPlayerBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private androidx.media3.exoplayer.ExoPlayer player;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job hideControlsJob;
    private int retryCount = 0;
    private final int maxRetries = 3;
    @org.jetbrains.annotations.NotNull()
    private final androidx.media3.common.Player.Listener playerListener = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_CHANNEL = "extra_channel";
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.presentation.iptv.player.PlayerActivity.Companion Companion = null;
    
    public PlayerActivity() {
        super();
    }
    
    private final com.merlottv.app.presentation.iptv.player.PlayerViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initPlayer(com.merlottv.app.domain.model.Channel channel) {
    }
    
    private final void releasePlayer() {
    }
    
    private final void retryPlayback() {
    }
    
    private final void showControls(long autoHideMs) {
    }
    
    private final void hideControls() {
    }
    
    @java.lang.Override()
    public boolean onKeyDown(int keyCode, @org.jetbrains.annotations.Nullable()
    android.view.KeyEvent event) {
        return false;
    }
    
    private final void observeState() {
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/merlottv/app/presentation/iptv/player/PlayerActivity$Companion;", "", "()V", "EXTRA_CHANNEL", "", "start", "", "context", "Landroid/content/Context;", "channel", "Lcom/merlottv/app/domain/model/Channel;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void start(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        com.merlottv.app.domain.model.Channel channel) {
        }
    }
}