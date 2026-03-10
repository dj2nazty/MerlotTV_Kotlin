package com.merlottv.app.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.merlottv.app.di.CinemetaRetrofit")
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
public final class NetworkModule_ProvideCinemetaRetrofitFactory implements Factory<Retrofit> {
  private final Provider<OkHttpClient> okHttpProvider;

  public NetworkModule_ProvideCinemetaRetrofitFactory(Provider<OkHttpClient> okHttpProvider) {
    this.okHttpProvider = okHttpProvider;
  }

  @Override
  public Retrofit get() {
    return provideCinemetaRetrofit(okHttpProvider.get());
  }

  public static NetworkModule_ProvideCinemetaRetrofitFactory create(
      Provider<OkHttpClient> okHttpProvider) {
    return new NetworkModule_ProvideCinemetaRetrofitFactory(okHttpProvider);
  }

  public static Retrofit provideCinemetaRetrofit(OkHttpClient okHttp) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideCinemetaRetrofit(okHttp));
  }
}
