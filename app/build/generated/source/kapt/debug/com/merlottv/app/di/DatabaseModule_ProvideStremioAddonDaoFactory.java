package com.merlottv.app.di;

import com.merlottv.app.data.local.MerlotDatabase;
import com.merlottv.app.data.local.dao.StremioAddonDao;
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
public final class DatabaseModule_ProvideStremioAddonDaoFactory implements Factory<StremioAddonDao> {
  private final Provider<MerlotDatabase> dbProvider;

  public DatabaseModule_ProvideStremioAddonDaoFactory(Provider<MerlotDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public StremioAddonDao get() {
    return provideStremioAddonDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideStremioAddonDaoFactory create(
      Provider<MerlotDatabase> dbProvider) {
    return new DatabaseModule_ProvideStremioAddonDaoFactory(dbProvider);
  }

  public static StremioAddonDao provideStremioAddonDao(MerlotDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideStremioAddonDao(db));
  }
}
