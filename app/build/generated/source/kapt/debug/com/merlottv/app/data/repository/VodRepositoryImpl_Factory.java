package com.merlottv.app.data.repository;

import com.google.gson.Gson;
import com.merlottv.app.data.local.dao.StremioAddonDao;
import com.merlottv.app.data.network.api.CinemetaApi;
import com.merlottv.app.data.network.api.StremioAddonApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class VodRepositoryImpl_Factory implements Factory<VodRepositoryImpl> {
  private final Provider<CinemetaApi> cinemetaApiProvider;

  private final Provider<StremioAddonApi> addonApiProvider;

  private final Provider<StremioAddonDao> addonDaoProvider;

  private final Provider<Gson> gsonProvider;

  public VodRepositoryImpl_Factory(Provider<CinemetaApi> cinemetaApiProvider,
      Provider<StremioAddonApi> addonApiProvider, Provider<StremioAddonDao> addonDaoProvider,
      Provider<Gson> gsonProvider) {
    this.cinemetaApiProvider = cinemetaApiProvider;
    this.addonApiProvider = addonApiProvider;
    this.addonDaoProvider = addonDaoProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public VodRepositoryImpl get() {
    return newInstance(cinemetaApiProvider.get(), addonApiProvider.get(), addonDaoProvider.get(), gsonProvider.get());
  }

  public static VodRepositoryImpl_Factory create(Provider<CinemetaApi> cinemetaApiProvider,
      Provider<StremioAddonApi> addonApiProvider, Provider<StremioAddonDao> addonDaoProvider,
      Provider<Gson> gsonProvider) {
    return new VodRepositoryImpl_Factory(cinemetaApiProvider, addonApiProvider, addonDaoProvider, gsonProvider);
  }

  public static VodRepositoryImpl newInstance(CinemetaApi cinemetaApi, StremioAddonApi addonApi,
      StremioAddonDao addonDao, Gson gson) {
    return new VodRepositoryImpl(cinemetaApi, addonApi, addonDao, gson);
  }
}
