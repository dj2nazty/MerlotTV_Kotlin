package com.merlottv.app;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.Configuration;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.gson.Gson;
import com.merlottv.app.data.local.MerlotDatabase;
import com.merlottv.app.data.local.dao.ChannelDao;
import com.merlottv.app.data.local.dao.EpgSourceDao;
import com.merlottv.app.data.local.dao.PlaylistSourceDao;
import com.merlottv.app.data.local.dao.ProgrammeDao;
import com.merlottv.app.data.local.dao.StremioAddonDao;
import com.merlottv.app.data.network.api.CinemetaApi;
import com.merlottv.app.data.network.api.StremioAddonApi;
import com.merlottv.app.data.parser.M3uParser;
import com.merlottv.app.data.parser.XmltvParser;
import com.merlottv.app.data.repository.ChannelRepositoryImpl;
import com.merlottv.app.data.repository.CheckerRepositoryImpl;
import com.merlottv.app.data.repository.EpgRepositoryImpl;
import com.merlottv.app.data.repository.SettingsRepositoryImpl;
import com.merlottv.app.data.repository.VodRepositoryImpl;
import com.merlottv.app.di.DatabaseModule_ProvideChannelDaoFactory;
import com.merlottv.app.di.DatabaseModule_ProvideDatabaseFactory;
import com.merlottv.app.di.DatabaseModule_ProvideEpgSourceDaoFactory;
import com.merlottv.app.di.DatabaseModule_ProvidePlaylistSourceDaoFactory;
import com.merlottv.app.di.DatabaseModule_ProvideProgrammeDaoFactory;
import com.merlottv.app.di.DatabaseModule_ProvideStremioAddonDaoFactory;
import com.merlottv.app.di.NetworkModule_ProvideAddonRetrofitFactory;
import com.merlottv.app.di.NetworkModule_ProvideCinemetaApiFactory;
import com.merlottv.app.di.NetworkModule_ProvideCinemetaRetrofitFactory;
import com.merlottv.app.di.NetworkModule_ProvideGsonFactory;
import com.merlottv.app.di.NetworkModule_ProvideOkHttpFactory;
import com.merlottv.app.di.NetworkModule_ProvideStremioAddonApiFactory;
import com.merlottv.app.di.WorkerModule_ProvideWorkManagerConfigFactory;
import com.merlottv.app.presentation.checker.CheckerFragment;
import com.merlottv.app.presentation.checker.CheckerViewModel;
import com.merlottv.app.presentation.checker.CheckerViewModel_HiltModules;
import com.merlottv.app.presentation.guide.GuideFragment;
import com.merlottv.app.presentation.guide.GuideViewModel;
import com.merlottv.app.presentation.guide.GuideViewModel_HiltModules;
import com.merlottv.app.presentation.iptv.player.PlayerActivity;
import com.merlottv.app.presentation.iptv.player.PlayerViewModel;
import com.merlottv.app.presentation.iptv.player.PlayerViewModel_HiltModules;
import com.merlottv.app.presentation.main.HomeFragment;
import com.merlottv.app.presentation.main.HomeViewModel;
import com.merlottv.app.presentation.main.HomeViewModel_HiltModules;
import com.merlottv.app.presentation.main.MainActivity;
import com.merlottv.app.presentation.settings.AddonSettingsFragment;
import com.merlottv.app.presentation.settings.EpgSettingsFragment;
import com.merlottv.app.presentation.settings.PlaylistSettingsFragment;
import com.merlottv.app.presentation.settings.SettingsFragment;
import com.merlottv.app.presentation.settings.SettingsViewModel;
import com.merlottv.app.presentation.settings.SettingsViewModel_HiltModules;
import com.merlottv.app.service.PlaybackService;
import com.merlottv.app.service.PlaybackService_MembersInjector;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerMerlotApp_HiltComponents_SingletonC {
  private DaggerMerlotApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public MerlotApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements MerlotApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public MerlotApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements MerlotApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public MerlotApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements MerlotApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public MerlotApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements MerlotApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MerlotApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements MerlotApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public MerlotApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements MerlotApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public MerlotApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements MerlotApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public MerlotApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends MerlotApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends MerlotApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public void injectCheckerFragment(CheckerFragment checkerFragment) {
    }

    @Override
    public void injectGuideFragment(GuideFragment guideFragment) {
    }

    @Override
    public void injectHomeFragment(HomeFragment homeFragment) {
    }

    @Override
    public void injectAddonSettingsFragment(AddonSettingsFragment addonSettingsFragment) {
    }

    @Override
    public void injectEpgSettingsFragment(EpgSettingsFragment epgSettingsFragment) {
    }

    @Override
    public void injectPlaylistSettingsFragment(PlaylistSettingsFragment playlistSettingsFragment) {
    }

    @Override
    public void injectSettingsFragment(SettingsFragment settingsFragment) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends MerlotApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends MerlotApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectPlayerActivity(PlayerActivity playerActivity) {
    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>of(LazyClassKeyProvider.com_merlottv_app_presentation_checker_CheckerViewModel, CheckerViewModel_HiltModules.KeyModule.provide(), LazyClassKeyProvider.com_merlottv_app_presentation_guide_GuideViewModel, GuideViewModel_HiltModules.KeyModule.provide(), LazyClassKeyProvider.com_merlottv_app_presentation_main_HomeViewModel, HomeViewModel_HiltModules.KeyModule.provide(), LazyClassKeyProvider.com_merlottv_app_presentation_iptv_player_PlayerViewModel, PlayerViewModel_HiltModules.KeyModule.provide(), LazyClassKeyProvider.com_merlottv_app_presentation_settings_SettingsViewModel, SettingsViewModel_HiltModules.KeyModule.provide()));
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_merlottv_app_presentation_guide_GuideViewModel = "com.merlottv.app.presentation.guide.GuideViewModel";

      static String com_merlottv_app_presentation_checker_CheckerViewModel = "com.merlottv.app.presentation.checker.CheckerViewModel";

      static String com_merlottv_app_presentation_main_HomeViewModel = "com.merlottv.app.presentation.main.HomeViewModel";

      static String com_merlottv_app_presentation_settings_SettingsViewModel = "com.merlottv.app.presentation.settings.SettingsViewModel";

      static String com_merlottv_app_presentation_iptv_player_PlayerViewModel = "com.merlottv.app.presentation.iptv.player.PlayerViewModel";

      @KeepFieldType
      GuideViewModel com_merlottv_app_presentation_guide_GuideViewModel2;

      @KeepFieldType
      CheckerViewModel com_merlottv_app_presentation_checker_CheckerViewModel2;

      @KeepFieldType
      HomeViewModel com_merlottv_app_presentation_main_HomeViewModel2;

      @KeepFieldType
      SettingsViewModel com_merlottv_app_presentation_settings_SettingsViewModel2;

      @KeepFieldType
      PlayerViewModel com_merlottv_app_presentation_iptv_player_PlayerViewModel2;
    }
  }

  private static final class ViewModelCImpl extends MerlotApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<CheckerViewModel> checkerViewModelProvider;

    private Provider<GuideViewModel> guideViewModelProvider;

    private Provider<HomeViewModel> homeViewModelProvider;

    private Provider<PlayerViewModel> playerViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.checkerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.guideViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.homeViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.playerViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>of(LazyClassKeyProvider.com_merlottv_app_presentation_checker_CheckerViewModel, ((Provider) checkerViewModelProvider), LazyClassKeyProvider.com_merlottv_app_presentation_guide_GuideViewModel, ((Provider) guideViewModelProvider), LazyClassKeyProvider.com_merlottv_app_presentation_main_HomeViewModel, ((Provider) homeViewModelProvider), LazyClassKeyProvider.com_merlottv_app_presentation_iptv_player_PlayerViewModel, ((Provider) playerViewModelProvider), LazyClassKeyProvider.com_merlottv_app_presentation_settings_SettingsViewModel, ((Provider) settingsViewModelProvider)));
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_merlottv_app_presentation_iptv_player_PlayerViewModel = "com.merlottv.app.presentation.iptv.player.PlayerViewModel";

      static String com_merlottv_app_presentation_guide_GuideViewModel = "com.merlottv.app.presentation.guide.GuideViewModel";

      static String com_merlottv_app_presentation_settings_SettingsViewModel = "com.merlottv.app.presentation.settings.SettingsViewModel";

      static String com_merlottv_app_presentation_checker_CheckerViewModel = "com.merlottv.app.presentation.checker.CheckerViewModel";

      static String com_merlottv_app_presentation_main_HomeViewModel = "com.merlottv.app.presentation.main.HomeViewModel";

      @KeepFieldType
      PlayerViewModel com_merlottv_app_presentation_iptv_player_PlayerViewModel2;

      @KeepFieldType
      GuideViewModel com_merlottv_app_presentation_guide_GuideViewModel2;

      @KeepFieldType
      SettingsViewModel com_merlottv_app_presentation_settings_SettingsViewModel2;

      @KeepFieldType
      CheckerViewModel com_merlottv_app_presentation_checker_CheckerViewModel2;

      @KeepFieldType
      HomeViewModel com_merlottv_app_presentation_main_HomeViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.merlottv.app.presentation.checker.CheckerViewModel 
          return (T) new CheckerViewModel(singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.checkerRepositoryImplProvider.get());

          case 1: // com.merlottv.app.presentation.guide.GuideViewModel 
          return (T) new GuideViewModel(singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.epgRepositoryImplProvider.get());

          case 2: // com.merlottv.app.presentation.main.HomeViewModel 
          return (T) new HomeViewModel(singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.vodRepositoryImplProvider.get());

          case 3: // com.merlottv.app.presentation.iptv.player.PlayerViewModel 
          return (T) new PlayerViewModel(singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.settingsRepositoryImplProvider.get(), singletonCImpl.provideOkHttpProvider.get());

          case 4: // com.merlottv.app.presentation.settings.SettingsViewModel 
          return (T) new SettingsViewModel(singletonCImpl.settingsRepositoryImplProvider.get(), singletonCImpl.channelRepositoryImplProvider.get(), singletonCImpl.epgRepositoryImplProvider.get(), singletonCImpl.vodRepositoryImplProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends MerlotApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends MerlotApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }

    @Override
    public void injectPlaybackService(PlaybackService playbackService) {
      injectPlaybackService2(playbackService);
    }

    @CanIgnoreReturnValue
    private PlaybackService injectPlaybackService2(PlaybackService instance) {
      PlaybackService_MembersInjector.injectOkHttpClient(instance, singletonCImpl.provideOkHttpProvider.get());
      return instance;
    }
  }

  private static final class SingletonCImpl extends MerlotApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<OkHttpClient> provideOkHttpProvider;

    private Provider<Configuration> provideWorkManagerConfigProvider;

    private Provider<MerlotDatabase> provideDatabaseProvider;

    private Provider<M3uParser> m3uParserProvider;

    private Provider<ChannelRepositoryImpl> channelRepositoryImplProvider;

    private Provider<CheckerRepositoryImpl> checkerRepositoryImplProvider;

    private Provider<XmltvParser> xmltvParserProvider;

    private Provider<EpgRepositoryImpl> epgRepositoryImplProvider;

    private Provider<Retrofit> provideCinemetaRetrofitProvider;

    private Provider<CinemetaApi> provideCinemetaApiProvider;

    private Provider<Retrofit> provideAddonRetrofitProvider;

    private Provider<StremioAddonApi> provideStremioAddonApiProvider;

    private Provider<Gson> provideGsonProvider;

    private Provider<VodRepositoryImpl> vodRepositoryImplProvider;

    private Provider<SettingsRepositoryImpl> settingsRepositoryImplProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private ChannelDao channelDao() {
      return DatabaseModule_ProvideChannelDaoFactory.provideChannelDao(provideDatabaseProvider.get());
    }

    private PlaylistSourceDao playlistSourceDao() {
      return DatabaseModule_ProvidePlaylistSourceDaoFactory.providePlaylistSourceDao(provideDatabaseProvider.get());
    }

    private EpgSourceDao epgSourceDao() {
      return DatabaseModule_ProvideEpgSourceDaoFactory.provideEpgSourceDao(provideDatabaseProvider.get());
    }

    private ProgrammeDao programmeDao() {
      return DatabaseModule_ProvideProgrammeDaoFactory.provideProgrammeDao(provideDatabaseProvider.get());
    }

    private StremioAddonDao stremioAddonDao() {
      return DatabaseModule_ProvideStremioAddonDaoFactory.provideStremioAddonDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.provideOkHttpProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 0));
      this.provideWorkManagerConfigProvider = DoubleCheck.provider(new SwitchingProvider<Configuration>(singletonCImpl, 1));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<MerlotDatabase>(singletonCImpl, 3));
      this.m3uParserProvider = DoubleCheck.provider(new SwitchingProvider<M3uParser>(singletonCImpl, 4));
      this.channelRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<ChannelRepositoryImpl>(singletonCImpl, 2));
      this.checkerRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<CheckerRepositoryImpl>(singletonCImpl, 5));
      this.xmltvParserProvider = DoubleCheck.provider(new SwitchingProvider<XmltvParser>(singletonCImpl, 7));
      this.epgRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<EpgRepositoryImpl>(singletonCImpl, 6));
      this.provideCinemetaRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 10));
      this.provideCinemetaApiProvider = DoubleCheck.provider(new SwitchingProvider<CinemetaApi>(singletonCImpl, 9));
      this.provideAddonRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 12));
      this.provideStremioAddonApiProvider = DoubleCheck.provider(new SwitchingProvider<StremioAddonApi>(singletonCImpl, 11));
      this.provideGsonProvider = DoubleCheck.provider(new SwitchingProvider<Gson>(singletonCImpl, 13));
      this.vodRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<VodRepositoryImpl>(singletonCImpl, 8));
      this.settingsRepositoryImplProvider = DoubleCheck.provider(new SwitchingProvider<SettingsRepositoryImpl>(singletonCImpl, 14));
    }

    @Override
    public void injectMerlotApp(MerlotApp merlotApp) {
      injectMerlotApp2(merlotApp);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    @CanIgnoreReturnValue
    private MerlotApp injectMerlotApp2(MerlotApp instance) {
      MerlotApp_MembersInjector.injectOkHttpClient(instance, provideOkHttpProvider.get());
      MerlotApp_MembersInjector.injectWorkConfiguration(instance, provideWorkManagerConfigProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpFactory.provideOkHttp();

          case 1: // androidx.work.Configuration 
          return (T) WorkerModule_ProvideWorkManagerConfigFactory.provideWorkManagerConfig();

          case 2: // com.merlottv.app.data.repository.ChannelRepositoryImpl 
          return (T) new ChannelRepositoryImpl(singletonCImpl.channelDao(), singletonCImpl.playlistSourceDao(), singletonCImpl.m3uParserProvider.get(), singletonCImpl.provideOkHttpProvider.get());

          case 3: // com.merlottv.app.data.local.MerlotDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 4: // com.merlottv.app.data.parser.M3uParser 
          return (T) new M3uParser();

          case 5: // com.merlottv.app.data.repository.CheckerRepositoryImpl 
          return (T) new CheckerRepositoryImpl(singletonCImpl.provideOkHttpProvider.get());

          case 6: // com.merlottv.app.data.repository.EpgRepositoryImpl 
          return (T) new EpgRepositoryImpl(singletonCImpl.epgSourceDao(), singletonCImpl.programmeDao(), singletonCImpl.xmltvParserProvider.get(), singletonCImpl.provideOkHttpProvider.get());

          case 7: // com.merlottv.app.data.parser.XmltvParser 
          return (T) new XmltvParser();

          case 8: // com.merlottv.app.data.repository.VodRepositoryImpl 
          return (T) new VodRepositoryImpl(singletonCImpl.provideCinemetaApiProvider.get(), singletonCImpl.provideStremioAddonApiProvider.get(), singletonCImpl.stremioAddonDao(), singletonCImpl.provideGsonProvider.get());

          case 9: // com.merlottv.app.data.network.api.CinemetaApi 
          return (T) NetworkModule_ProvideCinemetaApiFactory.provideCinemetaApi(singletonCImpl.provideCinemetaRetrofitProvider.get());

          case 10: // @com.merlottv.app.di.CinemetaRetrofit retrofit2.Retrofit 
          return (T) NetworkModule_ProvideCinemetaRetrofitFactory.provideCinemetaRetrofit(singletonCImpl.provideOkHttpProvider.get());

          case 11: // com.merlottv.app.data.network.api.StremioAddonApi 
          return (T) NetworkModule_ProvideStremioAddonApiFactory.provideStremioAddonApi(singletonCImpl.provideAddonRetrofitProvider.get());

          case 12: // @com.merlottv.app.di.AddonRetrofit retrofit2.Retrofit 
          return (T) NetworkModule_ProvideAddonRetrofitFactory.provideAddonRetrofit(singletonCImpl.provideOkHttpProvider.get());

          case 13: // com.google.gson.Gson 
          return (T) NetworkModule_ProvideGsonFactory.provideGson();

          case 14: // com.merlottv.app.data.repository.SettingsRepositoryImpl 
          return (T) new SettingsRepositoryImpl(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
