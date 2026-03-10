package com.merlottv.app.presentation.main;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.*;
import androidx.lifecycle.Lifecycle;
import com.merlottv.app.R;
import com.merlottv.app.domain.model.Channel;
import com.merlottv.app.domain.model.VodItem;
import com.merlottv.app.presentation.common.CardPresenter;
import com.merlottv.app.presentation.common.SectionItem;
import com.merlottv.app.presentation.iptv.player.PlayerActivity;
import dagger.hilt.android.AndroidEntryPoint;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 )2\u00020\u0001:\u0001)B\u0005\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0010\u001a\u00020\u00112\u001e\u0010\u0012\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00130\u00140\u0013H\u0002J\u0016\u0010\u0017\u001a\u00020\u00112\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013H\u0002J\u0016\u0010\u0019\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0011H\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u0011H\u0002J\u001a\u0010\"\u001a\u00020\u00112\u0006\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010\'\u001a\u00020\u0011H\u0002J\b\u0010(\u001a\u00020\u0011H\u0002R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\b\u001a\u0004\b\r\u0010\u000e\u00a8\u0006*"}, d2 = {"Lcom/merlottv/app/presentation/main/HomeFragment;", "Landroidx/leanback/app/BrowseSupportFragment;", "()V", "cardPresenter", "Lcom/merlottv/app/presentation/common/CardPresenter;", "getCardPresenter", "()Lcom/merlottv/app/presentation/common/CardPresenter;", "cardPresenter$delegate", "Lkotlin/Lazy;", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "viewModel", "Lcom/merlottv/app/presentation/main/HomeViewModel;", "getViewModel", "()Lcom/merlottv/app/presentation/main/HomeViewModel;", "viewModel$delegate", "buildChannelRows", "", "groups", "", "Lkotlin/Pair;", "", "Lcom/merlottv/app/domain/model/Channel;", "buildFavouritesRow", "favs", "buildVodRow", "movies", "Lcom/merlottv/app/domain/model/VodItem;", "ensureStaticRows", "findRowById", "Landroidx/leanback/widget/ListRow;", "id", "", "observeViewModel", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setupBrowseFragment", "setupRowsAdapter", "Companion", "app_debug"})
public final class HomeFragment extends androidx.leanback.app.BrowseSupportFragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    private androidx.leanback.widget.ArrayObjectAdapter rowsAdapter;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy cardPresenter$delegate = null;
    private static final long ROW_ID_VOD = 9000L;
    private static final long ROW_ID_FAVS = 9001L;
    private static final long ROW_ID_GUIDE = 9002L;
    private static final long ROW_ID_CHECKER = 9003L;
    private static final long ROW_ID_SETTINGS = 9004L;
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.presentation.main.HomeFragment.Companion Companion = null;
    
    public HomeFragment() {
        super();
    }
    
    private final com.merlottv.app.presentation.main.HomeViewModel getViewModel() {
        return null;
    }
    
    private final com.merlottv.app.presentation.common.CardPresenter getCardPresenter() {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupBrowseFragment() {
    }
    
    private final void setupRowsAdapter() {
    }
    
    private final void observeViewModel() {
    }
    
    private final void buildChannelRows(java.util.List<? extends kotlin.Pair<java.lang.String, ? extends java.util.List<com.merlottv.app.domain.model.Channel>>> groups) {
    }
    
    private final void buildVodRow(java.util.List<com.merlottv.app.domain.model.VodItem> movies) {
    }
    
    private final void buildFavouritesRow(java.util.List<com.merlottv.app.domain.model.Channel> favs) {
    }
    
    private final void ensureStaticRows() {
    }
    
    private final androidx.leanback.widget.ListRow findRowById(long id) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/merlottv/app/presentation/main/HomeFragment$Companion;", "", "()V", "ROW_ID_CHECKER", "", "ROW_ID_FAVS", "ROW_ID_GUIDE", "ROW_ID_SETTINGS", "ROW_ID_VOD", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}