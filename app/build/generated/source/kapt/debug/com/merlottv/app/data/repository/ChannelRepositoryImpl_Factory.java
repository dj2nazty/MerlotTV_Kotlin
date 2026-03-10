package com.merlottv.app.data.repository;

import com.merlottv.app.data.local.dao.ChannelDao;
import com.merlottv.app.data.local.dao.PlaylistSourceDao;
import com.merlottv.app.data.parser.M3uParser;
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
public final class ChannelRepositoryImpl_Factory implements Factory<ChannelRepositoryImpl> {
  private final Provider<ChannelDao> channelDaoProvider;

  private final Provider<PlaylistSourceDao> sourceDaoProvider;

  private final Provider<M3uParser> parserProvider;

  private final Provider<OkHttpClient> okHttpProvider;

  public ChannelRepositoryImpl_Factory(Provider<ChannelDao> channelDaoProvider,
      Provider<PlaylistSourceDao> sourceDaoProvider, Provider<M3uParser> parserProvider,
      Provider<OkHttpClient> okHttpProvider) {
    this.channelDaoProvider = channelDaoProvider;
    this.sourceDaoProvider = sourceDaoProvider;
    this.parserProvider = parserProvider;
    this.okHttpProvider = okHttpProvider;
  }

  @Override
  public ChannelRepositoryImpl get() {
    return newInstance(channelDaoProvider.get(), sourceDaoProvider.get(), parserProvider.get(), okHttpProvider.get());
  }

  public static ChannelRepositoryImpl_Factory create(Provider<ChannelDao> channelDaoProvider,
      Provider<PlaylistSourceDao> sourceDaoProvider, Provider<M3uParser> parserProvider,
      Provider<OkHttpClient> okHttpProvider) {
    return new ChannelRepositoryImpl_Factory(channelDaoProvider, sourceDaoProvider, parserProvider, okHttpProvider);
  }

  public static ChannelRepositoryImpl newInstance(ChannelDao channelDao,
      PlaylistSourceDao sourceDao, M3uParser parser, OkHttpClient okHttp) {
    return new ChannelRepositoryImpl(channelDao, sourceDao, parser, okHttp);
  }
}
