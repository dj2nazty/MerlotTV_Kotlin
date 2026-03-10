package com.merlottv.app.presentation.settings;

import androidx.lifecycle.ViewModel;
import com.merlottv.app.domain.model.EpgSource;
import com.merlottv.app.domain.model.PlaylistSource;
import com.merlottv.app.domain.repository.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0016\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$J\u0016\u0010&\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$J\u0006\u0010\'\u001a\u00020\"J\u000e\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020$J\u0006\u0010*\u001a\u00020\"J\u000e\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020$J\u000e\u0010-\u001a\u00020\"2\u0006\u0010.\u001a\u00020/J\u000e\u00100\u001a\u00020\"2\u0006\u0010.\u001a\u00020/J\u0006\u00101\u001a\u00020\"J\u0006\u00102\u001a\u00020\"J\u0006\u00103\u001a\u00020\"J\u0016\u00104\u001a\u00020\"2\u0006\u0010,\u001a\u00020$2\u0006\u00105\u001a\u000206J\u0016\u00107\u001a\u00020\"2\u0006\u00108\u001a\u00020$2\u0006\u00109\u001a\u00020$R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u001d\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006:"}, d2 = {"Lcom/merlottv/app/presentation/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "settingsRepo", "Lcom/merlottv/app/domain/repository/SettingsRepository;", "channelRepo", "Lcom/merlottv/app/domain/repository/ChannelRepository;", "epgRepo", "Lcom/merlottv/app/domain/repository/EpgRepository;", "vodRepo", "Lcom/merlottv/app/domain/repository/VodRepository;", "(Lcom/merlottv/app/domain/repository/SettingsRepository;Lcom/merlottv/app/domain/repository/ChannelRepository;Lcom/merlottv/app/domain/repository/EpgRepository;Lcom/merlottv/app/domain/repository/VodRepository;)V", "_dialogEvent", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lcom/merlottv/app/presentation/settings/SettingsDialogEvent;", "addons", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/merlottv/app/domain/repository/StremioAddon;", "getAddons", "()Lkotlinx/coroutines/flow/StateFlow;", "dialogEvent", "Lkotlinx/coroutines/flow/SharedFlow;", "getDialogEvent", "()Lkotlinx/coroutines/flow/SharedFlow;", "epgSources", "Lcom/merlottv/app/domain/model/EpgSource;", "getEpgSources", "playlists", "Lcom/merlottv/app/domain/model/PlaylistSource;", "getPlaylists", "settings", "Lcom/merlottv/app/domain/repository/AppSettings;", "getSettings", "addEpgSource", "", "name", "", "url", "addPlaylist", "clearCache", "installAddon", "manifestUrl", "refreshAllSources", "removeAddon", "addonId", "removeEpgSource", "id", "", "removePlaylist", "showAddEpgDialog", "showAddPlaylistDialog", "showInstallAddonDialog", "toggleAddon", "enabled", "", "updatePreference", "key", "value", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.SettingsRepository settingsRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.ChannelRepository channelRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.EpgRepository epgRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.repository.VodRepository vodRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.merlottv.app.domain.repository.AppSettings> settings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.PlaylistSource>> playlists = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.EpgSource>> epgSources = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.repository.StremioAddon>> addons = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<com.merlottv.app.presentation.settings.SettingsDialogEvent> _dialogEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<com.merlottv.app.presentation.settings.SettingsDialogEvent> dialogEvent = null;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.SettingsRepository settingsRepo, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.ChannelRepository channelRepo, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.EpgRepository epgRepo, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.repository.VodRepository vodRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.merlottv.app.domain.repository.AppSettings> getSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.PlaylistSource>> getPlaylists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.model.EpgSource>> getEpgSources() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.merlottv.app.domain.repository.StremioAddon>> getAddons() {
        return null;
    }
    
    public final void addPlaylist(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void removePlaylist(long id) {
    }
    
    public final void refreshAllSources() {
    }
    
    public final void addEpgSource(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void removeEpgSource(long id) {
    }
    
    public final void installAddon(@org.jetbrains.annotations.NotNull()
    java.lang.String manifestUrl) {
    }
    
    public final void removeAddon(@org.jetbrains.annotations.NotNull()
    java.lang.String addonId) {
    }
    
    public final void toggleAddon(@org.jetbrains.annotations.NotNull()
    java.lang.String addonId, boolean enabled) {
    }
    
    public final void updatePreference(@org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.NotNull()
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<com.merlottv.app.presentation.settings.SettingsDialogEvent> getDialogEvent() {
        return null;
    }
    
    public final void showAddPlaylistDialog() {
    }
    
    public final void showAddEpgDialog() {
    }
    
    public final void showInstallAddonDialog() {
    }
    
    public final void clearCache() {
    }
}