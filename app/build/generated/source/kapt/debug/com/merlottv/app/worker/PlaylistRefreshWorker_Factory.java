package com.merlottv.app.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.merlottv.app.domain.repository.ChannelRepository;
import dagger.internal.DaggerGenerated;
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
public final class PlaylistRefreshWorker_Factory {
  private final Provider<ChannelRepository> channelRepoProvider;

  public PlaylistRefreshWorker_Factory(Provider<ChannelRepository> channelRepoProvider) {
    this.channelRepoProvider = channelRepoProvider;
  }

  public PlaylistRefreshWorker get(Context ctx, WorkerParameters params) {
    return newInstance(ctx, params, channelRepoProvider.get());
  }

  public static PlaylistRefreshWorker_Factory create(
      Provider<ChannelRepository> channelRepoProvider) {
    return new PlaylistRefreshWorker_Factory(channelRepoProvider);
  }

  public static PlaylistRefreshWorker newInstance(Context ctx, WorkerParameters params,
      ChannelRepository channelRepo) {
    return new PlaylistRefreshWorker(ctx, params, channelRepo);
  }
}
