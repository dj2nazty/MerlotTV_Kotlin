package com.merlottv.app.service;

import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

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
public final class PlaybackService_MembersInjector implements MembersInjector<PlaybackService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  public PlaybackService_MembersInjector(Provider<OkHttpClient> okHttpClientProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
  }

  public static MembersInjector<PlaybackService> create(
      Provider<OkHttpClient> okHttpClientProvider) {
    return new PlaybackService_MembersInjector(okHttpClientProvider);
  }

  @Override
  public void injectMembers(PlaybackService instance) {
    injectOkHttpClient(instance, okHttpClientProvider.get());
  }

  @InjectedFieldSignature("com.merlottv.app.service.PlaybackService.okHttpClient")
  public static void injectOkHttpClient(PlaybackService instance, OkHttpClient okHttpClient) {
    instance.okHttpClient = okHttpClient;
  }
}
