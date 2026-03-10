package com.merlottv.app.presentation.settings;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SeekBarPreference;
import androidx.preference.SwitchPreference;
import com.merlottv.app.R;
import com.merlottv.app.domain.repository.AppSettings;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0001H\u0002J\b\u0010\u000f\u001a\u00020\nH\u0002J\u001c\u0010\u0010\u001a\u00020\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0019"}, d2 = {"Lcom/merlottv/app/presentation/settings/SettingsFragment;", "Landroidx/preference/PreferenceFragmentCompat;", "()V", "viewModel", "Lcom/merlottv/app/presentation/settings/SettingsViewModel;", "getViewModel", "()Lcom/merlottv/app/presentation/settings/SettingsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "bindToUi", "", "settings", "Lcom/merlottv/app/domain/repository/AppSettings;", "navigateTo", "fragment", "observeSettings", "onCreatePreferences", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "", "onPreferenceTreeClick", "", "preference", "Landroidx/preference/Preference;", "app_debug"})
public final class SettingsFragment extends androidx.preference.PreferenceFragmentCompat {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public SettingsFragment() {
        super();
    }
    
    private final com.merlottv.app.presentation.settings.SettingsViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreatePreferences(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState, @org.jetbrains.annotations.Nullable()
    java.lang.String rootKey) {
    }
    
    private final void observeSettings() {
    }
    
    private final void bindToUi(com.merlottv.app.domain.repository.AppSettings settings) {
    }
    
    @java.lang.Override()
    public boolean onPreferenceTreeClick(@org.jetbrains.annotations.NotNull()
    androidx.preference.Preference preference) {
        return false;
    }
    
    private final void navigateTo(androidx.preference.PreferenceFragmentCompat fragment) {
    }
}