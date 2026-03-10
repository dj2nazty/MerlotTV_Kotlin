package com.merlottv.app.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merlottv.app.domain.model.EpgSource
import com.merlottv.app.domain.model.PlaylistSource
import com.merlottv.app.domain.repository.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepo: SettingsRepository,
    private val channelRepo: ChannelRepository,
    private val epgRepo: EpgRepository,
    private val vodRepo: VodRepository,
) : ViewModel() {

    val settings: StateFlow<AppSettings> =
        settingsRepo.getSettings()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppSettings())

    val playlists: StateFlow<List<PlaylistSource>> =
        channelRepo.getSources()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val epgSources: StateFlow<List<EpgSource>> =
        epgRepo.getEpgSources()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val addons: StateFlow<List<com.merlottv.app.domain.repository.StremioAddon>> =
        vodRepo.getInstalledAddons()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    // ─── Playlist actions ─────────────────────────────────────────

    fun addPlaylist(name: String, url: String) {
        viewModelScope.launch {
            val id = channelRepo.addSource(PlaylistSource(name = name, url = url))
            channelRepo.refreshSource(id)
        }
    }

    fun removePlaylist(id: Long) {
        viewModelScope.launch { channelRepo.removeSource(id) }
    }

    fun refreshAllSources() {
        viewModelScope.launch { channelRepo.refreshAllSources() }
    }

    // ─── EPG actions ──────────────────────────────────────────────

    fun addEpgSource(name: String, url: String) {
        viewModelScope.launch {
            val id = epgRepo.addEpgSource(EpgSource(name = name, url = url))
            epgRepo.refreshEpg(id)
        }
    }

    fun removeEpgSource(id: Long) {
        viewModelScope.launch { epgRepo.removeEpgSource(id) }
    }

    // ─── Addon actions ────────────────────────────────────────────

    fun installAddon(manifestUrl: String) {
        viewModelScope.launch { vodRepo.installAddon(manifestUrl) }
    }

    fun removeAddon(addonId: String) {
        viewModelScope.launch { vodRepo.removeAddon(addonId) }
    }

    fun toggleAddon(addonId: String, enabled: Boolean) {
        // Update isEnabled in DB — handled by repo
    }

    // ─── Preference updates ───────────────────────────────────────

    fun updatePreference(key: String, value: String) {
        viewModelScope.launch { settingsRepo.updateSetting(key, value) }
    }

    // ─── Dialogs (trigger via SharedFlow) ─────────────────────────

    private val _dialogEvent = MutableSharedFlow<SettingsDialogEvent>()
    val dialogEvent: SharedFlow<SettingsDialogEvent> = _dialogEvent

    fun showAddPlaylistDialog()   { viewModelScope.launch { _dialogEvent.emit(SettingsDialogEvent.AddPlaylist) } }
    fun showAddEpgDialog()        { viewModelScope.launch { _dialogEvent.emit(SettingsDialogEvent.AddEpg) } }
    fun showInstallAddonDialog()  { viewModelScope.launch { _dialogEvent.emit(SettingsDialogEvent.InstallAddon) } }

    // ─── Cache ────────────────────────────────────────────────────

    fun clearCache() { /* clear coil cache, room programmes etc. */ }
}

sealed class SettingsDialogEvent {
    object AddPlaylist  : SettingsDialogEvent()
    object AddEpg       : SettingsDialogEvent()
    object InstallAddon : SettingsDialogEvent()
}
