package com.merlottv.app.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.merlottv.app.domain.repository.EpgRepository;
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
public final class EpgRefreshWorker_Factory {
  private final Provider<EpgRepository> epgRepoProvider;

  public EpgRefreshWorker_Factory(Provider<EpgRepository> epgRepoProvider) {
    this.epgRepoProvider = epgRepoProvider;
  }

  public EpgRefreshWorker get(Context ctx, WorkerParameters params) {
    return newInstance(ctx, params, epgRepoProvider.get());
  }

  public static EpgRefreshWorker_Factory create(Provider<EpgRepository> epgRepoProvider) {
    return new EpgRefreshWorker_Factory(epgRepoProvider);
  }

  public static EpgRefreshWorker newInstance(Context ctx, WorkerParameters params,
      EpgRepository epgRepo) {
    return new EpgRefreshWorker(ctx, params, epgRepo);
  }
}
