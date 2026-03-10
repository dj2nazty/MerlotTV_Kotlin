package com.merlottv.app;

import androidx.work.Configuration;
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
public final class MerlotApp_MembersInjector implements MembersInjector<MerlotApp> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Configuration> workConfigurationProvider;

  public MerlotApp_MembersInjector(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Configuration> workConfigurationProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.workConfigurationProvider = workConfigurationProvider;
  }

  public static MembersInjector<MerlotApp> create(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Configuration> workConfigurationProvider) {
    return new MerlotApp_MembersInjector(okHttpClientProvider, workConfigurationProvider);
  }

  @Override
  public void injectMembers(MerlotApp instance) {
    injectOkHttpClient(instance, okHttpClientProvider.get());
    injectWorkConfiguration(instance, workConfigurationProvider.get());
  }

  @InjectedFieldSignature("com.merlottv.app.MerlotApp.okHttpClient")
  public static void injectOkHttpClient(MerlotApp instance, OkHttpClient okHttpClient) {
    instance.okHttpClient = okHttpClient;
  }

  @InjectedFieldSignature("com.merlottv.app.MerlotApp.workConfiguration")
  public static void injectWorkConfiguration(MerlotApp instance, Configuration workConfiguration) {
    instance.workConfiguration = workConfiguration;
  }
}
