package com.merlottv.app.di;

import com.merlottv.app.data.local.MerlotDatabase;
import com.merlottv.app.data.local.dao.ChannelDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class DatabaseModule_ProvideChannelDaoFactory implements Factory<ChannelDao> {
  private final Provider<MerlotDatabase> dbProvider;

  public DatabaseModule_ProvideChannelDaoFactory(Provider<MerlotDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ChannelDao get() {
    return provideChannelDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideChannelDaoFactory create(
      Provider<MerlotDatabase> dbProvider) {
    return new DatabaseModule_ProvideChannelDaoFactory(dbProvider);
  }

  public static ChannelDao provideChannelDao(MerlotDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideChannelDao(db));
  }
}
