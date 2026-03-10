package com.merlottv.app.di;

import androidx.work.Configuration;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class WorkerModule_ProvideWorkManagerConfigFactory implements Factory<Configuration> {
  @Override
  public Configuration get() {
    return provideWorkManagerConfig();
  }

  public static WorkerModule_ProvideWorkManagerConfigFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static Configuration provideWorkManagerConfig() {
    return Preconditions.checkNotNullFromProvides(WorkerModule.INSTANCE.provideWorkManagerConfig());
  }

  private static final class InstanceHolder {
    private static final WorkerModule_ProvideWorkManagerConfigFactory INSTANCE = new WorkerModule_ProvideWorkManagerConfigFactory();
  }
}
