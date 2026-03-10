package com.merlottv.app.di;

import com.merlottv.app.data.network.api.CinemetaApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
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
public final class NetworkModule_ProvideCinemetaApiFactory implements Factory<CinemetaApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideCinemetaApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public CinemetaApi get() {
    return provideCinemetaApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideCinemetaApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideCinemetaApiFactory(retrofitProvider);
  }

  public static CinemetaApi provideCinemetaApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideCinemetaApi(retrofit));
  }
}
