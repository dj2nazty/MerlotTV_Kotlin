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
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\fH\'J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0012H\'\u00a8\u0006\u0013"}, d2 = {"Lcom/merlottv/app/di/RepositoryModule;", "", "()V", "bindChannelRepository", "Lcom/merlottv/app/domain/repository/ChannelRepository;", "impl", "Lcom/merlottv/app/data/repository/ChannelRepositoryImpl;", "bindCheckerRepository", "Lcom/merlottv/app/domain/repository/CheckerRepository;", "Lcom/merlottv/app/data/repository/CheckerRepositoryImpl;", "bindEpgRepository", "Lcom/merlottv/app/domain/repository/EpgRepository;", "Lcom/merlottv/app/data/repository/EpgRepositoryImpl;", "bindSettingsRepository", "Lcom/merlottv/app/domain/repository/SettingsRepository;", "Lcom/merlottv/app/data/repository/SettingsRepositoryImpl;", "bindVodRepository", "Lcom/merlottv/app/domain/repository/VodRepository;", "Lcom/merlottv/app/data/repository/VodRepositoryImpl;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public abstract class RepositoryModule {
    
    public RepositoryModule() {
        super();
    }
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.domain.repository.ChannelRepository bindChannelRepository(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.repository.ChannelRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.domain.repository.EpgRepository bindEpgRepository(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.repository.EpgRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.domain.repository.VodRepository bindVodRepository(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.repository.VodRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.domain.repository.CheckerRepository bindCheckerRepository(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.repository.CheckerRepositoryImpl impl);
    
    @dagger.Binds()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.domain.repository.SettingsRepository bindSettingsRepository(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.data.repository.SettingsRepositoryImpl impl);
}