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
public final class NetworkModule_ProvideAddonRetrofitFactory implements Factory<Retrofit> {
  private final Provider<OkHttpClient> okHttpProvider;

  public NetworkModule_ProvideAddonRetrofitFactory(Provider<OkHttpClient> okHttpProvider) {
    this.okHttpProvider = okHttpProvider;
  }

  @Override
  public Retrofit get() {
    return provideAddonRetrofit(okHttpProvider.get());
  }

  public static NetworkModule_ProvideAddonRetrofitFactory create(
      Provider<OkHttpClient> okHttpProvider) {
    return new NetworkModule_ProvideAddonRetrofitFactory(okHttpProvider);
  }

  public static Retrofit provideAddonRetrofit(OkHttpClient okHttp) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideAddonRetrofit(okHttp));
  }
}
