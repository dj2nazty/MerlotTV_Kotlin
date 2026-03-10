package com.merlottv.app.presentation.guide;

import android.app.Dialog;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.merlottv.app.domain.model.Programme;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016\u00a8\u0006\b"}, d2 = {"Lcom/merlottv/app/presentation/guide/ProgrammeDetailDialog;", "Landroidx/fragment/app/DialogFragment;", "()V", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_debug"})
public final class ProgrammeDetailDialog extends androidx.fragment.app.DialogFragment {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "ProgrammeDetail";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_TITLE = "title";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_DESC = "desc";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_START = "start";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ARG_END = "end";
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.presentation.guide.ProgrammeDetailDialog.Companion Companion = null;
    
    public ProgrammeDetailDialog() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.app.Dialog onCreateDialog(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/merlottv/app/presentation/guide/ProgrammeDetailDialog$Companion;", "", "()V", "ARG_DESC", "", "ARG_END", "ARG_START", "ARG_TITLE", "TAG", "show", "", "fm", "Landroidx/fragment/app/FragmentManager;", "programme", "Lcom/merlottv/app/domain/model/Programme;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void show(@org.jetbrains.annotations.NotNull()
        androidx.fragment.app.FragmentManager fm, @org.jetbrains.annotations.NotNull()
        com.merlottv.app.domain.model.Programme programme) {
        }
    }
}