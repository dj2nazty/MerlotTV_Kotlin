package com.merlottv.app.presentation.settings

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SeekBarPreference
import androidx.preference.SwitchPreference
import com.merlottv.app.R
import com.merlottv.app.domain.repository.AppSettings
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        observeSettings()
    }

    private fun observeSettings() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.settings.collect { settings -> bindToUi(settings) }
            }
        }
    }

    private fun bindToUi(settings: AppSettings) {
        (findPreference("user_agent") as? EditTextPreference)?.text          = settings.defaultUserAgent
        (findPreference("hw_decoding") as? SwitchPreference)?.isChecked      = settings.hardwareDecoding
        (findPreference("buffer_seconds") as? SeekBarPreference)?.value      = settings.bufferSizeSeconds
        (findPreference("subtitles") as? SwitchPreference)?.isChecked        = settings.subtitlesEnabled
        (findPreference("theme") as? ListPreference)?.value                  = settings.themeMode
        (findPreference("parental_control") as? SwitchPreference)?.isChecked = settings.parenralControlEnabled
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean {
        return when (preference.key) {
            "pref_clear_cache" -> { viewModel.clearCache(); true }
            "pref_refresh_all" -> { viewModel.refreshAllSources(); true }
            "pref_playlists"   -> { navigateTo(PlaylistSettingsFragment()); true }
            "pref_epg"         -> { navigateTo(EpgSettingsFragment()); true }
            "pref_addons"      -> { navigateTo(AddonSettingsFragment()); true }
            else -> super.onPreferenceTreeClick(preference)
        }
    }

    private fun navigateTo(fragment: PreferenceFragmentCompat) {
        parentFragmentManager.beginTransaction()
            .replace(id, fragment)
            .addToBackStack(null)
            .commit()
    }
}

@AndroidEntryPoint
class PlaylistSettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val screen = preferenceManager.createPreferenceScreen(requireContext())
        preferenceScreen = screen
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.playlists.collect { sources ->
                screen.removeAll()
                sources.forEach { source ->
                    Preference(requireContext()).apply {
                        key     = "playlist_${source.id}"
                        title   = source.name
                        summary = "${source.channelCount} channels • ${source.lastRefreshed.toDateString()}"
                        screen.addPreference(this)
                    }
                }
                Preference(requireContext()).apply {
                    key   = "add_playlist"
                    title = "+ Add M3U Source"
                    setOnPreferenceClickListener { viewModel.showAddPlaylistDialog(); true }
                    screen.addPreference(this)
                }
            }
        }
    }
}

@AndroidEntryPoint
class EpgSettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val screen = preferenceManager.createPreferenceScreen(requireContext())
        preferenceScreen = screen
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.epgSources.collect { sources ->
                screen.removeAll()
                sources.forEach { source ->
                    Preference(requireContext()).apply {
                        title   = source.name
                        summary = source.url
                        screen.addPreference(this)
                    }
                }
                Preference(requireContext()).apply {
                    title = "+ Add EPG Source (XMLTV URL)"
                    setOnPreferenceClickListener { viewModel.showAddEpgDialog(); true }
                    screen.addPreference(this)
                }
            }
        }
    }
}

@AndroidEntryPoint
class AddonSettingsFragment : PreferenceFragmentCompat() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        val screen = preferenceManager.createPreferenceScreen(requireContext())
        preferenceScreen = screen
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.addons.collect { addons ->
                screen.removeAll()
                addons.forEach { addon ->
                    SwitchPreference(requireContext()).apply {
                        title     = addon.name
                        summary   = "v${addon.version} • ${addon.types.joinToString()}"
                        isChecked = addon.isEnabled
                        setOnPreferenceChangeListener { _, v ->
                            viewModel.toggleAddon(addon.id, v as Boolean); true
                        }
                        screen.addPreference(this)
                    }
                }
                Preference(requireContext()).apply {
                    title = "+ Install Addon"
                    setOnPreferenceClickListener { viewModel.showInstallAddonDialog(); true }
                    screen.addPreference(this)
                }
            }
        }
    }
}

private fun Long.toDateString(): String {
    if (this == 0L) return "Never"
    return java.text.SimpleDateFormat("dd MMM HH:mm", java.util.Locale.getDefault())
        .format(java.util.Date(this))
}
