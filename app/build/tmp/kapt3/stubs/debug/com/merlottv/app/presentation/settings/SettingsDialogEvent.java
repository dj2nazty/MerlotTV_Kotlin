package com.merlottv.app.presentation.settings;

import androidx.lifecycle.ViewModel;
import com.merlottv.app.domain.model.EpgSource;
import com.merlottv.app.domain.model.PlaylistSource;
import com.merlottv.app.domain.repository.*;
import dagger.hilt.android.lifecycle.HiltViewModel;
import kotlinx.coroutines.flow.*;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lcom/merlottv/app/presentation/settings/SettingsDialogEvent;", "", "()V", "AddEpg", "AddPlaylist", "InstallAddon", "Lcom/merlottv/app/presentation/settings/SettingsDialogEvent$AddEpg;", "Lcom/merlottv/app/presentation/settings/SettingsDialogEvent$AddPlaylist;", "Lcom/merlottv/app/presentation/settings/SettingsDialogEvent$InstallAddon;", "app_debug"})
public abstract class SettingsDialogEvent {
    
    private SettingsDialogEvent() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/merlottv/app/presentation/settings/SettingsDialogEvent$AddEpg;", "Lcom/merlottv/app/presentation/settings/SettingsDialogEvent;", "()V", "app_debug"})
    public static final class AddEpg extends com.merlottv.app.presentation.settings.SettingsDialogEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.merlottv.app.presentation.settings.SettingsDialogEvent.AddEpg INSTANCE = null;
        
        private AddEpg() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/merlottv/app/presentation/settings/SettingsDialogEvent$AddPlaylist;", "Lcom/merlottv/app/presentation/settings/SettingsDialogEvent;", "()V", "app_debug"})
    public static final class AddPlaylist extends com.merlottv.app.presentation.settings.SettingsDialogEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.merlottv.app.presentation.settings.SettingsDialogEvent.AddPlaylist INSTANCE = null;
        
        private AddPlaylist() {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/merlottv/app/presentation/settings/SettingsDialogEvent$InstallAddon;", "Lcom/merlottv/app/presentation/settings/SettingsDialogEvent;", "()V", "app_debug"})
    public static final class InstallAddon extends com.merlottv.app.presentation.settings.SettingsDialogEvent {
        @org.jetbrains.annotations.NotNull()
        public static final com.merlottv.app.presentation.settings.SettingsDialogEvent.InstallAddon INSTANCE = null;
        
        private InstallAddon() {
        }
    }
}