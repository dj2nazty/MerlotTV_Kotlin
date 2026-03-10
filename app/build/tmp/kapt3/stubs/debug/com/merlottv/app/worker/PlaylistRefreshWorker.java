package com.merlottv.app.worker;

import android.content.Context;
import androidx.hilt.work.HiltWorker;
import androidx.work.*;
import com.merlottv.app.domain.repository.ChannelRepository;
import com.merlottv.app.domain.repository.EpgRepository;
import com.merlottv.app.domain.repository.SettingsRepository;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import java.util.concurrent.TimeUnit;

/**
 * Periodic WorkManager worker that refreshes M3U playlists in the background.
 * Interval is driven by the user's setting (default: 24 h).
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \f2\u00020\u0001:\u0001\fB#\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\nH\u0096@\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/merlottv/app/worker/PlaylistRefreshWorker;", "Landroidx/work/CoroutineWorker;", "ctx", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "channelRepo", "Lcom/merlottv/app/domain/repository/ChannelRepository;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;Lcom/merlottv/app/domain/repository/ChannelRepository;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
@androidx.hilt.work.HiltWorker()
public final class PlaylistRefreshWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.ChannelRepository channelRepo = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String WORK_NAME = "playlist_refresh";
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.worker.PlaylistRefreshWorker.Companion Companion = null;
    
    @dagger.assisted.AssistedInject()
    public PlaylistRefreshWorker(@dagger.assisted.Assisted()
    @org.jetbrains.annotations.NotNull()
    android.content.Context ctx, @dagger.assisted.Assisted()
    @org.jetbrains.annotations.NotNull()
    androidx.work.WorkerParameters params, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.ChannelRepository channelRepo) {
        super(null, null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/merlottv/app/worker/PlaylistRefreshWorker$Companion;", "", "()V", "WORK_NAME", "", "schedule", "", "context", "Landroid/content/Context;", "intervalHours", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void schedule(@org.jetbrains.annotations.NotNull()
        android.content.Context context, int intervalHours) {
        }
    }
}