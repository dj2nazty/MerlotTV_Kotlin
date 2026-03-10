package com.merlottv.app.presentation.vod.detail;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavArgs;
import com.merlottv.app.domain.model.VodItem;
import java.io.Serializable;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;
import kotlin.Suppress;
import kotlin.jvm.JvmStatic;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0012J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0016"}, d2 = {"Lcom/merlottv/app/presentation/vod/detail/VodDetailFragmentArgs;", "Landroidx/navigation/NavArgs;", "vodItem", "Lcom/merlottv/app/domain/model/VodItem;", "(Lcom/merlottv/app/domain/model/VodItem;)V", "getVodItem", "()Lcom/merlottv/app/domain/model/VodItem;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toBundle", "Landroid/os/Bundle;", "toSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "toString", "", "Companion", "app_debug"})
public final class VodDetailFragmentArgs implements androidx.navigation.NavArgs {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.model.VodItem vodItem = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.presentation.vod.detail.VodDetailFragmentArgs.Companion Companion = null;
    
    public VodDetailFragmentArgs(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.VodItem vodItem) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.domain.model.VodItem getVodItem() {
        return null;
    }
    
    @kotlin.Suppress(names = {"CAST_NEVER_SUCCEEDS"})
    @org.jetbrains.annotations.NotNull()
    public final android.os.Bundle toBundle() {
        return null;
    }
    
    @kotlin.Suppress(names = {"CAST_NEVER_SUCCEEDS"})
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.SavedStateHandle toSavedStateHandle() {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    @kotlin.Suppress(names = {"DEPRECATION"})
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.presentation.vod.detail.VodDetailFragmentArgs fromBundle(@org.jetbrains.annotations.NotNull()
    android.os.Bundle bundle) {
        return null;
    }
    
    @kotlin.jvm.JvmStatic()
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.presentation.vod.detail.VodDetailFragmentArgs fromSavedStateHandle(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.domain.model.VodItem component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.presentation.vod.detail.VodDetailFragmentArgs copy(@org.jetbrains.annotations.NotNull()
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH\u0007\u00a8\u0006\n"}, d2 = {"Lcom/merlottv/app/presentation/vod/detail/VodDetailFragmentArgs$Companion;", "", "()V", "fromBundle", "Lcom/merlottv/app/presentation/vod/detail/VodDetailFragmentArgs;", "bundle", "Landroid/os/Bundle;", "fromSavedStateHandle", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @kotlin.jvm.JvmStatic()
        @kotlin.Suppress(names = {"DEPRECATION"})
        @org.jetbrains.annotations.NotNull()
        public final com.merlottv.app.presentation.vod.detail.VodDetailFragmentArgs fromBundle(@org.jetbrains.annotations.NotNull()
        android.os.Bundle bundle) {
            return null;
        }
        
        @kotlin.jvm.JvmStatic()
        @org.jetbrains.annotations.NotNull()
        public final com.merlottv.app.presentation.vod.detail.VodDetailFragmentArgs fromSavedStateHandle(@org.jetbrains.annotations.NotNull()
        androidx.lifecycle.SavedStateHandle savedStateHandle) {
            return null;
        }
    }
}