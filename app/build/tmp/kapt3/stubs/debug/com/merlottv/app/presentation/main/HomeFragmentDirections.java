package com.merlottv.app.presentation.main;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.NavDirections;
import com.merlottv.app.R;
import com.merlottv.app.domain.model.Channel;
import com.merlottv.app.domain.model.VodItem;
import java.io.Serializable;
import java.lang.UnsupportedOperationException;
import kotlin.Int;
import kotlin.Suppress;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00052\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0006"}, d2 = {"Lcom/merlottv/app/presentation/main/HomeFragmentDirections;", "", "()V", "ActionHomeToIptvChannels", "ActionHomeToVodDetail", "Companion", "app_debug"})
public final class HomeFragmentDirections {
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.presentation.main.HomeFragmentDirections.Companion Companion = null;
    
    private HomeFragmentDirections() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0004J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\u0015\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/merlottv/app/presentation/main/HomeFragmentDirections$ActionHomeToIptvChannels;", "Landroidx/navigation/NavDirections;", "channel", "Lcom/merlottv/app/domain/model/Channel;", "(Lcom/merlottv/app/domain/model/Channel;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getChannel", "()Lcom/merlottv/app/domain/model/Channel;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
    static final class ActionHomeToIptvChannels implements androidx.navigation.NavDirections {
        @org.jetbrains.annotations.Nullable()
        private final com.merlottv.app.domain.model.Channel channel = null;
        private final int actionId = 0;
        
        public ActionHomeToIptvChannels(@org.jetbrains.annotations.Nullable()
        com.merlottv.app.domain.model.Channel channel) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.merlottv.app.domain.model.Channel getChannel() {
            return null;
        }
        
        @java.lang.Override()
        public int getActionId() {
            return 0;
        }
        
        @java.lang.Override()
        @kotlin.Suppress(names = {"CAST_NEVER_SUCCEEDS"})
        @org.jetbrains.annotations.NotNull()
        public android.os.Bundle getArguments() {
            return null;
        }
        
        public ActionHomeToIptvChannels() {
            super();
        }
        
        @org.jetbrains.annotations.Nullable()
        public final com.merlottv.app.domain.model.Channel component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.merlottv.app.presentation.main.HomeFragmentDirections.ActionHomeToIptvChannels copy(@org.jetbrains.annotations.Nullable()
        com.merlottv.app.domain.model.Channel channel) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u00d6\u0003J\t\u0010\u0015\u001a\u00020\u0006H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/merlottv/app/presentation/main/HomeFragmentDirections$ActionHomeToVodDetail;", "Landroidx/navigation/NavDirections;", "vodItem", "Lcom/merlottv/app/domain/model/VodItem;", "(Lcom/merlottv/app/domain/model/VodItem;)V", "actionId", "", "getActionId", "()I", "arguments", "Landroid/os/Bundle;", "getArguments", "()Landroid/os/Bundle;", "getVodItem", "()Lcom/merlottv/app/domain/model/VodItem;", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_debug"})
    static final class ActionHomeToVodDetail implements androidx.navigation.NavDirections {
        @org.jetbrains.annotations.NotNull()
        private final com.merlottv.app.domain.model.VodItem vodItem = null;
        private final int actionId = 0;
        
        public ActionHomeToVodDetail(@org.jetbrains.annotations.NotNull()
        com.merlottv.app.domain.model.VodItem vodItem) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.merlottv.app.domain.model.VodItem getVodItem() {
            return null;
        }
        
        @java.lang.Override()
        public int getActionId() {
            return 0;
        }
        
        @java.lang.Override()
        @kotlin.Suppress(names = {"CAST_NEVER_SUCCEEDS"})
        @org.jetbrains.annotations.NotNull()
        public android.os.Bundle getArguments() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.merlottv.app.domain.model.VodItem component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.merlottv.app.presentation.main.HomeFragmentDirections.ActionHomeToVodDetail copy(@org.jetbrains.annotations.NotNull()
        com.merlottv.app.domain.model.VodItem vodItem) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0012\u0010\u0006\u001a\u00020\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0006\u0010\t\u001a\u00020\u0004J\u0006\u0010\n\u001a\u00020\u0004J\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/merlottv/app/presentation/main/HomeFragmentDirections$Companion;", "", "()V", "actionHomeToChecker", "Landroidx/navigation/NavDirections;", "actionHomeToGuide", "actionHomeToIptvChannels", "channel", "Lcom/merlottv/app/domain/model/Channel;", "actionHomeToSearch", "actionHomeToSettings", "actionHomeToVodBrowse", "actionHomeToVodDetail", "vodItem", "Lcom/merlottv/app/domain/model/VodItem;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections actionHomeToIptvChannels(@org.jetbrains.annotations.Nullable()
        com.merlottv.app.domain.model.Channel channel) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections actionHomeToGuide() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections actionHomeToVodBrowse() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections actionHomeToVodDetail(@org.jetbrains.annotations.NotNull()
        com.merlottv.app.domain.model.VodItem vodItem) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections actionHomeToChecker() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections actionHomeToSettings() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.navigation.NavDirections actionHomeToSearch() {
            return null;
        }
    }
}