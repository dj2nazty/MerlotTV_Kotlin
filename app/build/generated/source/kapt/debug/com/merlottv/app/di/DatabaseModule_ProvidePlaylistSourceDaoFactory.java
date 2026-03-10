package com.merlottv.app.di;

import com.merlottv.app.data.local.MerlotDatabase;
import com.merlottv.app.data.local.dao.PlaylistSourceDao;
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
public final class DatabaseModule_ProvidePlaylistSourceDaoFactory implements Factory<PlaylistSourceDao> {
  private final Provider<MerlotDatabase> dbProvider;

  public DatabaseModule_ProvidePlaylistSourceDaoFactory(Provider<MerlotDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public PlaylistSourceDao get() {
    return providePlaylistSourceDao(dbProvider.get());
  }

  public static DatabaseModule_ProvidePlaylistSourceDaoFactory create(
      Provider<MerlotDatabase> dbProvider) {
    return new DatabaseModule_ProvidePlaylistSourceDaoFactory(dbProvider);
  }

  public static PlaylistSourceDao providePlaylistSourceDao(MerlotDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.providePlaylistSourceDao(db));
  }
}
