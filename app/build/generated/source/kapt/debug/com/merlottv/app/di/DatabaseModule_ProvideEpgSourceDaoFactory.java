package com.merlottv.app.di;

import com.merlottv.app.data.local.MerlotDatabase;
import com.merlottv.app.data.local.dao.EpgSourceDao;
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
public final class DatabaseModule_ProvideEpgSourceDaoFactory implements Factory<EpgSourceDao> {
  private final Provider<MerlotDatabase> dbProvider;

  public DatabaseModule_ProvideEpgSourceDaoFactory(Provider<MerlotDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public EpgSourceDao get() {
    return provideEpgSourceDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideEpgSourceDaoFactory create(
      Provider<MerlotDatabase> dbProvider) {
    return new DatabaseModule_ProvideEpgSourceDaoFactory(dbProvider);
  }

  public static EpgSourceDao provideEpgSourceDao(MerlotDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideEpgSourceDao(db));
  }
}
