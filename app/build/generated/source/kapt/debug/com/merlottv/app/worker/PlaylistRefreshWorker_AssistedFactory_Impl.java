package com.merlottv.app.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class PlaylistRefreshWorker_AssistedFactory_Impl implements PlaylistRefreshWorker_AssistedFactory {
  private final PlaylistRefreshWorker_Factory delegateFactory;

  PlaylistRefreshWorker_AssistedFactory_Impl(PlaylistRefreshWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public PlaylistRefreshWorker create(Context arg0, WorkerParameters arg1) {
    return delegateFactory.get(arg0, arg1);
  }

  public static Provider<PlaylistRefreshWorker_AssistedFactory> create(
      PlaylistRefreshWorker_Factory delegateFactory) {
    return InstanceFactory.create(new PlaylistRefreshWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<PlaylistRefreshWorker_AssistedFactory> createFactoryProvider(
      PlaylistRefreshWorker_Factory delegateFactory) {
    return InstanceFactory.create(new PlaylistRefreshWorker_AssistedFactory_Impl(delegateFactory));
  }
}
