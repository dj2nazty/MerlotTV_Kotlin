package com.merlottv.app

import android.app.Application
import android.webkit.WebView
import androidx.work.Configuration
import androidx.work.WorkManager
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import javax.inject.Inject

/**
 * Application entry-point.
 *
 * Responsibilities:
 *  - Hilt component tree initialisation
 *  - Coil global image loader (TV-optimised: large memory cache for channel logos)
 *  - WorkManager custom configuration (Hilt-aware)
 */
@HiltAndroidApp
class MerlotApp : Application(), ImageLoaderFactory, Configuration.Provider {

    @Inject lateinit var okHttpClient: OkHttpClient
    @Inject lateinit var workConfiguration: Configuration

    override fun onCreate() {
        super.onCreate()
        // Enable WebView debugging in debug builds
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }

    override fun newImageLoader(): ImageLoader =
        ImageLoader.Builder(this)
            .okHttpClient(okHttpClient)
            .memoryCache {
                MemoryCache.Builder(this)
                    .maxSizePercent(0.25)   // 25 % of available RAM for logos
                    .build()
            }
            .diskCache {
                DiskCache.Builder()
                    .directory(cacheDir.resolve("coil_cache"))
                    .maxSizePercent(0.02)
                    .build()
            }
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .crossfade(true)
            .build()

    override val workManagerConfiguration: Configuration
        get() = workConfiguration
}
