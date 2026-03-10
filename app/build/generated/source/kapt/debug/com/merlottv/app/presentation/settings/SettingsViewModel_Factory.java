package com.merlottv.app.presentation.settings;

import com.merlottv.app.domain.repository.ChannelRepository;
import com.merlottv.app.domain.repository.EpgRepository;
import com.merlottv.app.domain.repository.SettingsRepository;
import com.merlottv.app.domain.repository.VodRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<SettingsRepository> settingsRepoProvider;

  private final Provider<ChannelRepository> channelRepoProvider;

  private final Provider<EpgRepository> epgRepoProvider;

  private final Provider<VodRepository> vodRepoProvider;

  public SettingsViewModel_Factory(Provider<SettingsRepository> settingsRepoProvider,
      Provider<ChannelRepository> channelRepoProvider, Provider<EpgRepository> epgRepoProvider,
      Provider<VodRepository> vodRepoProvider) {
    this.settingsRepoProvider = settingsRepoProvider;
    this.channelRepoProvider = channelRepoProvider;
    this.epgRepoProvider = epgRepoProvider;
    this.vodRepoProvider = vodRepoProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(settingsRepoProvider.get(), channelRepoProvider.get(), epgRepoProvider.get(), vodRepoProvider.get());
  }

  public static SettingsViewModel_Factory create(Provider<SettingsRepository> settingsRepoProvider,
      Provider<ChannelRepository> channelRepoProvider, Provider<EpgRepository> epgRepoProvider,
      Provider<VodRepository> vodRepoProvider) {
    return new SettingsViewModel_Factory(settingsRepoProvider, channelRepoProvider, epgRepoProvider, vodRepoProvider);
  }

  public static SettingsViewModel newInstance(SettingsRepository settingsRepo,
      ChannelRepository channelRepo, EpgRepository epgRepo, VodRepository vodRepo) {
    return new SettingsViewModel(settingsRepo, channelRepo, epgRepo, vodRepo);
  }
}
