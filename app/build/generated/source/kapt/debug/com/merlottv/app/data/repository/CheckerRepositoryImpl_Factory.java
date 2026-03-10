package com.merlottv.app.data.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

@ScopeMetadata("javax.inject.Singleton")
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
public final class CheckerRepositoryImpl_Factory implements Factory<CheckerRepositoryImpl> {
  private final Provider<OkHttpClient> okHttpProvider;

  public CheckerRepositoryImpl_Factory(Provider<OkHttpClient> okHttpProvider) {
    this.okHttpProvider = okHttpProvider;
  }

  @Override
  public CheckerRepositoryImpl get() {
    return newInstance(okHttpProvider.get());
  }

  public static CheckerRepositoryImpl_Factory create(Provider<OkHttpClient> okHttpProvider) {
    return new CheckerRepositoryImpl_Factory(okHttpProvider);
  }

  public static CheckerRepositoryImpl newInstance(OkHttpClient okHttp) {
    return new CheckerRepositoryImpl(okHttp);
  }
}
