package com.merlottv.app.di;

import com.merlottv.app.data.local.MerlotDatabase;
import com.merlottv.app.data.local.dao.ProgrammeDao;
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
public final class DatabaseModule_ProvideProgrammeDaoFactory implements Factory<ProgrammeDao> {
  private final Provider<MerlotDatabase> dbProvider;

  public DatabaseModule_ProvideProgrammeDaoFactory(Provider<MerlotDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ProgrammeDao get() {
    return provideProgrammeDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideProgrammeDaoFactory create(
      Provider<MerlotDatabase> dbProvider) {
    return new DatabaseModule_ProvideProgrammeDaoFactory(dbProvider);
  }

  public static ProgrammeDao provideProgrammeDao(MerlotDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideProgrammeDao(db));
  }
}
