package com.merlottv.app.di;

import com.merlottv.app.data.network.api.StremioAddonApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("com.merlottv.app.di.AddonRetrofit")
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
public final class NetworkModule_ProvideStremioAddonApiFactory implements Factory<StremioAddonApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideStremioAddonApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public StremioAddonApi get() {
    return provideStremioAddonApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideStremioAddonApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideStremioAddonApiFactory(retrofitProvider);
  }

  public static StremioAddonApi provideStremioAddonApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideStremioAddonApi(retrofit));
  }
}
