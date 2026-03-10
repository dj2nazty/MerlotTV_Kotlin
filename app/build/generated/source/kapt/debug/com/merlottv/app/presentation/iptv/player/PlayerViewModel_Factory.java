package com.merlottv.app.presentation.iptv.player;

import com.merlottv.app.domain.repository.ChannelRepository;
import com.merlottv.app.domain.repository.SettingsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class PlayerViewModel_Factory implements Factory<PlayerViewModel> {
  private final Provider<ChannelRepository> channelRepoProvider;

  private final Provider<SettingsRepository> settingsRepoProvider;

  private final Provider<OkHttpClient> okHttpClientProvider;

  public PlayerViewModel_Factory(Provider<ChannelRepository> channelRepoProvider,
      Provider<SettingsRepository> settingsRepoProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    this.channelRepoProvider = channelRepoProvider;
    this.settingsRepoProvider = settingsRepoProvider;
    this.okHttpClientProvider = okHttpClientProvider;
  }

  @Override
  public PlayerViewModel get() {
    return newInstance(channelRepoProvider.get(), settingsRepoProvider.get(), okHttpClientProvider.get());
  }

  public static PlayerViewModel_Factory create(Provider<ChannelRepository> channelRepoProvider,
      Provider<SettingsRepository> settingsRepoProvider,
      Provider<OkHttpClient> okHttpClientProvider) {
    return new PlayerViewModel_Factory(channelRepoProvider, settingsRepoProvider, okHttpClientProvider);
  }

  public static PlayerViewModel newInstance(ChannelRepository channelRepo,
      SettingsRepository settingsRepo, OkHttpClient okHttpClient) {
    return new PlayerViewModel(channelRepo, settingsRepo, okHttpClient);
  }
}
