package com.merlottv.app;

import android.app.Application;
import androidx.work.Configuration;
import androidx.work.WorkManager;
import coil.ImageLoaderFactory;
import coil.disk.DiskCache;
import coil.memory.MemoryCache;
import coil.request.CachePolicy;
import dagger.hilt.android.HiltAndroidApp;
import okhttp3.OkHttpClient;
import javax.inject.Inject;

/**
 * Application entry-point.
 *
 * Responsibilities:
 * - Hilt component tree initialisation
 * - Coil global image loader (TV-optimised: large memory cache for channel logos)
 * - WorkManager custom configuration (Hilt-aware)
 */
@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u001e\u0010\u0005\u001a\u00020\u00068\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\f8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u000e\u00a8\u0006\u0015"}, d2 = {"Lcom/merlottv/app/MerlotApp;", "Landroid/app/Application;", "Lcoil/ImageLoaderFactory;", "Landroidx/work/Configuration$Provider;", "()V", "okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "setOkHttpClient", "(Lokhttp3/OkHttpClient;)V", "workConfiguration", "Landroidx/work/Configuration;", "getWorkConfiguration", "()Landroidx/work/Configuration;", "setWorkConfiguration", "(Landroidx/work/Configuration;)V", "workManagerConfiguration", "getWorkManagerConfiguration", "newImageLoader", "Lcoil/ImageLoader;", "app_debug"})
public final class MerlotApp extends android.app.Application implements coil.ImageLoaderFactory, androidx.work.Configuration.Provider {
    @javax.inject.Inject()
    public okhttp3.OkHttpClient okHttpClient;
    @javax.inject.Inject()
    public androidx.work.Configuration workConfiguration;
    
    public MerlotApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient getOkHttpClient() {
        return null;
    }
    
    public final void setOkHttpClient(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.work.Configuration getWorkConfiguration() {
        return null;
    }
    
    public final void setWorkConfiguration(@org.jetbrains.annotations.NotNull()
    androidx.work.Configuration p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public coil.ImageLoader newImageLoader() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.work.Configuration getWorkManagerConfiguration() {
        return null;
    }
}