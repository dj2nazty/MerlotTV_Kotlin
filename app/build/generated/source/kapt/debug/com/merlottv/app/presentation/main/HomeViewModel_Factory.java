package com.merlottv.app.presentation.main;

import com.merlottv.app.domain.repository.ChannelRepository;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<ChannelRepository> channelRepoProvider;

  private final Provider<VodRepository> vodRepoProvider;

  public HomeViewModel_Factory(Provider<ChannelRepository> channelRepoProvider,
      Provider<VodRepository> vodRepoProvider) {
    this.channelRepoProvider = channelRepoProvider;
    this.vodRepoProvider = vodRepoProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(channelRepoProvider.get(), vodRepoProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<ChannelRepository> channelRepoProvider,
      Provider<VodRepository> vodRepoProvider) {
    return new HomeViewModel_Factory(channelRepoProvider, vodRepoProvider);
  }

  public static HomeViewModel newInstance(ChannelRepository channelRepo, VodRepository vodRepo) {
    return new HomeViewModel(channelRepo, vodRepo);
  }
}
