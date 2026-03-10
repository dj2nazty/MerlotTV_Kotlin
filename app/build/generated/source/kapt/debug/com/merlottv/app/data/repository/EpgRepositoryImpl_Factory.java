package com.merlottv.app.data.repository;

import com.merlottv.app.data.local.dao.EpgSourceDao;
import com.merlottv.app.data.local.dao.ProgrammeDao;
import com.merlottv.app.data.parser.XmltvParser;
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
public final class EpgRepositoryImpl_Factory implements Factory<EpgRepositoryImpl> {
  private final Provider<EpgSourceDao> epgSourceDaoProvider;

  private final Provider<ProgrammeDao> programmeDaoProvider;

  private final Provider<XmltvParser> parserProvider;

  private final Provider<OkHttpClient> okHttpProvider;

  public EpgRepositoryImpl_Factory(Provider<EpgSourceDao> epgSourceDaoProvider,
      Provider<ProgrammeDao> programmeDaoProvider, Provider<XmltvParser> parserProvider,
      Provider<OkHttpClient> okHttpProvider) {
    this.epgSourceDaoProvider = epgSourceDaoProvider;
    this.programmeDaoProvider = programmeDaoProvider;
    this.parserProvider = parserProvider;
    this.okHttpProvider = okHttpProvider;
  }

  @Override
  public EpgRepositoryImpl get() {
    return newInstance(epgSourceDaoProvider.get(), programmeDaoProvider.get(), parserProvider.get(), okHttpProvider.get());
  }

  public static EpgRepositoryImpl_Factory create(Provider<EpgSourceDao> epgSourceDaoProvider,
      Provider<ProgrammeDao> programmeDaoProvider, Provider<XmltvParser> parserProvider,
      Provider<OkHttpClient> okHttpProvider) {
    return new EpgRepositoryImpl_Factory(epgSourceDaoProvider, programmeDaoProvider, parserProvider, okHttpProvider);
  }

  public static EpgRepositoryImpl newInstance(EpgSourceDao epgSourceDao, ProgrammeDao programmeDao,
      XmltvParser parser, OkHttpClient okHttp) {
    return new EpgRepositoryImpl(epgSourceDao, programmeDao, parser, okHttp);
  }
}
