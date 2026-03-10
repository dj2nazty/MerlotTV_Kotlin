package com.merlottv.app.presentation.guide;

import com.merlottv.app.domain.repository.ChannelRepository;
import com.merlottv.app.domain.repository.EpgRepository;
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
public final class GuideViewModel_Factory implements Factory<GuideViewModel> {
  private final Provider<ChannelRepository> channelRepoProvider;

  private final Provider<EpgRepository> epgRepoProvider;

  public GuideViewModel_Factory(Provider<ChannelRepository> channelRepoProvider,
      Provider<EpgRepository> epgRepoProvider) {
    this.channelRepoProvider = channelRepoProvider;
    this.epgRepoProvider = epgRepoProvider;
  }

  @Override
  public GuideViewModel get() {
    return newInstance(channelRepoProvider.get(), epgRepoProvider.get());
  }

  public static GuideViewModel_Factory create(Provider<ChannelRepository> channelRepoProvider,
      Provider<EpgRepository> epgRepoProvider) {
    return new GuideViewModel_Factory(channelRepoProvider, epgRepoProvider);
  }

  public static GuideViewModel newInstance(ChannelRepository channelRepo, EpgRepository epgRepo) {
    return new GuideViewModel(channelRepo, epgRepo);
  }
}
