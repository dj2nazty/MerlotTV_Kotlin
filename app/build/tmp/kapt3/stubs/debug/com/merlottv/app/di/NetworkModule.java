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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\t\u001a\u00020\u0004H\u0007J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\b\u0010\u000b\u001a\u00020\fH\u0007J\b\u0010\r\u001a\u00020\u0006H\u0007J\u0012\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\t\u001a\u00020\u0004H\u0007\u00a8\u0006\u0010"}, d2 = {"Lcom/merlottv/app/di/NetworkModule;", "", "()V", "provideAddonRetrofit", "Lretrofit2/Retrofit;", "okHttp", "Lokhttp3/OkHttpClient;", "provideCinemetaApi", "Lcom/merlottv/app/data/network/api/CinemetaApi;", "retrofit", "provideCinemetaRetrofit", "provideGson", "Lcom/google/gson/Gson;", "provideOkHttp", "provideStremioAddonApi", "Lcom/merlottv/app/data/network/api/StremioAddonApi;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class NetworkModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.di.NetworkModule INSTANCE = null;
    
    private NetworkModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final okhttp3.OkHttpClient provideOkHttp() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.google.gson.Gson provideGson() {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @CinemetaRetrofit()
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideCinemetaRetrofit(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttp) {
        return null;
    }
    
    /**
     * Generic retrofit for arbitrary addon base URLs — baseUrl overridden per call
     */
    @dagger.Provides()
    @javax.inject.Singleton()
    @AddonRetrofit()
    @org.jetbrains.annotations.NotNull()
    public final retrofit2.Retrofit provideAddonRetrofit(@org.jetbrains.annotations.NotNull()
    okhttp3.OkHttpClient okHttp) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.data.network.api.CinemetaApi provideCinemetaApi(@CinemetaRetrofit()
    @org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.data.network.api.StremioAddonApi provideStremioAddonApi(@AddonRetrofit()
    @org.jetbrains.annotations.NotNull()
    retrofit2.Retrofit retrofit) {
        return null;
    }
}