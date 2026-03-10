package com.merlottv.app.di;

import android.content.Context;
import androidx.room.Room;
import androidx.work.Configuration;
import com.merlottv.app.data.local.MerlotDatabase;
import com.merlottv.app.data.local.dao.*;
import com.merlottv.app.data.network.api.CinemetaApi;
import com.merlottv.app.data.network.api.StremioAddonApi;
import com.merlottv.app.data.repository.*;
import com.merlottv.app.domain.repository.*;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit;
import javax.inject.Qualifier;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0012"}, d2 = {"Lcom/merlottv/app/di/DatabaseModule;", "", "()V", "provideChannelDao", "Lcom/merlottv/app/data/local/dao/ChannelDao;", "db", "Lcom/merlottv/app/data/local/MerlotDatabase;", "provideDatabase", "ctx", "Landroid/content/Context;", "provideEpgSourceDao", "Lcom/merlottv/app/data/local/dao/EpgSourceDao;", "providePlaylistSourceDao", "Lcom/merlottv/app/data/local/dao/PlaylistSourceDao;", "provideProgrammeDao", "Lcom/merlottv/app/data/local/dao/ProgrammeDao;", "provideStremioAddonDao", "Lcom/merlottv/app/data/local/dao/StremioAddonDao;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class DatabaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.di.DatabaseModule INSTANCE = null;
    
    private DatabaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.data.local.MerlotDatabase provideDatabase(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context ctx) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.data.local.dao.ChannelDao provideChannelDao(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.MerlotDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.data.local.dao.PlaylistSourceDao providePlaylistSourceDao(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.MerlotDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.data.local.dao.ProgrammeDao provideProgrammeDao(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.MerlotDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.data.local.dao.EpgSourceDao provideEpgSourceDao(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.MerlotDatabase db) {
        return null;
    }
    
    @dagger.Provides()
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.data.local.dao.StremioAddonDao provideStremioAddonDao(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.local.MerlotDatabase db) {
        return null;
    }
}