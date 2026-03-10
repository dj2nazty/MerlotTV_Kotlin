package com.merlottv.app.di

import android.content.Context
import androidx.room.Room
import androidx.work.Configuration
import com.merlottv.app.data.local.MerlotDatabase
import com.merlottv.app.data.local.dao.*
import com.merlottv.app.data.network.api.CinemetaApi
import com.merlottv.app.data.network.api.StremioAddonApi
import com.merlottv.app.data.repository.*
import com.merlottv.app.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

// ─── Qualifiers ────────────────────────────────────────────────────────────

@Qualifier @Retention(AnnotationRetention.BINARY)
annotation class CinemetaRetrofit

@Qualifier @Retention(AnnotationRetention.BINARY)
annotation class AddonRetrofit

// ─── Network Module ─────────────────────────────────────────────────────────

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides @Singleton
    fun provideOkHttp(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }
            )
            .build()

    @Provides @Singleton
    fun provideGson(): com.google.gson.Gson = com.google.gson.GsonBuilder().create()

    @Provides @Singleton @CinemetaRetrofit
    fun provideCinemetaRetrofit(okHttp: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://v3-cinemeta.strem.io/")
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /** Generic retrofit for arbitrary addon base URLs — baseUrl overridden per call */
    @Provides @Singleton @AddonRetrofit
    fun provideAddonRetrofit(okHttp: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://cinemeta-catalogs.strem.io/")   // placeholder; overridden dynamically
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides @Singleton
    fun provideCinemetaApi(@CinemetaRetrofit retrofit: Retrofit): CinemetaApi =
        retrofit.create(CinemetaApi::class.java)

    @Provides @Singleton
    fun provideStremioAddonApi(@AddonRetrofit retrofit: Retrofit): StremioAddonApi =
        retrofit.create(StremioAddonApi::class.java)
}

// ─── Database Module ─────────────────────────────────────────────────────────

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides @Singleton
    fun provideDatabase(@ApplicationContext ctx: Context): MerlotDatabase =
        Room.databaseBuilder(ctx, MerlotDatabase::class.java, "merlot.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides fun provideChannelDao(db: MerlotDatabase): ChannelDao = db.channelDao()
    @Provides fun providePlaylistSourceDao(db: MerlotDatabase): PlaylistSourceDao = db.playlistSourceDao()
    @Provides fun provideProgrammeDao(db: MerlotDatabase): ProgrammeDao = db.programmeDao()
    @Provides fun provideEpgSourceDao(db: MerlotDatabase): EpgSourceDao = db.epgSourceDao()
    @Provides fun provideStremioAddonDao(db: MerlotDatabase): StremioAddonDao = db.stremioAddonDao()
}

// ─── Repository Bindings ─────────────────────────────────────────────────────

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds @Singleton
    abstract fun bindChannelRepository(impl: ChannelRepositoryImpl): ChannelRepository

    @Binds @Singleton
    abstract fun bindEpgRepository(impl: EpgRepositoryImpl): EpgRepository

    @Binds @Singleton
    abstract fun bindVodRepository(impl: VodRepositoryImpl): VodRepository

    @Binds @Singleton
    abstract fun bindCheckerRepository(impl: CheckerRepositoryImpl): CheckerRepository

    @Binds @Singleton
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
}

// ─── WorkManager Module ───────────────────────────────────────────────────────

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {

    @Provides @Singleton
    fun provideWorkManagerConfig(): Configuration =
        Configuration.Builder()
            .setMinimumLoggingLevel(android.util.Log.INFO)
            .build()
}
