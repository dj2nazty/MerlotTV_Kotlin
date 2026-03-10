package com.merlottv.app.presentation.checker;

import com.merlottv.app.domain.repository.ChannelRepository;
import com.merlottv.app.domain.repository.CheckerRepository;
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
public final class CheckerViewModel_Factory implements Factory<CheckerViewModel> {
  private final Provider<ChannelRepository> channelRepoProvider;

  private final Provider<CheckerRepository> checkerRepoProvider;

  public CheckerViewModel_Factory(Provider<ChannelRepository> channelRepoProvider,
      Provider<CheckerRepository> checkerRepoProvider) {
    this.channelRepoProvider = channelRepoProvider;
    this.checkerRepoProvider = checkerRepoProvider;
  }

  @Override
  public CheckerViewModel get() {
    return newInstance(channelRepoProvider.get(), checkerRepoProvider.get());
  }

  public static CheckerViewModel_Factory create(Provider<ChannelRepository> channelRepoProvider,
      Provider<CheckerRepository> checkerRepoProvider) {
    return new CheckerViewModel_Factory(channelRepoProvider, checkerRepoProvider);
  }

  public static CheckerViewModel newInstance(ChannelRepository channelRepo,
      CheckerRepository checkerRepo) {
    return new CheckerViewModel(channelRepo, checkerRepo);
  }
}
