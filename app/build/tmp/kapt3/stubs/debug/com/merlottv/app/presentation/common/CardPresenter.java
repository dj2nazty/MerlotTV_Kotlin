package com.merlottv.app.presentation.common;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;
import coil.transform.RoundedCornersTransformation;
import com.merlottv.app.R;
import com.merlottv.app.domain.model.Channel;
import com.merlottv.app.domain.model.VodItem;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0011H\u0002J\u001a\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/merlottv/app/presentation/common/CardPresenter;", "Landroidx/leanback/widget/Presenter;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "defaultBackground", "Landroid/graphics/drawable/ColorDrawable;", "bindChannel", "", "card", "Landroidx/leanback/widget/ImageCardView;", "channel", "Lcom/merlottv/app/domain/model/Channel;", "bindSectionItem", "item", "Lcom/merlottv/app/presentation/common/SectionItem;", "bindVodItem", "Lcom/merlottv/app/domain/model/VodItem;", "onBindViewHolder", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "Companion", "app_debug"})
public final class CardPresenter extends androidx.leanback.widget.Presenter {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final android.graphics.drawable.ColorDrawable defaultBackground = null;
    private static final int CARD_WIDTH_CHANNEL = 176;
    private static final int CARD_HEIGHT_CHANNEL = 100;
    private static final int CARD_WIDTH_VOD = 120;
    private static final int CARD_HEIGHT_VOD = 176;
    @org.jetbrains.annotations.NotNull()
    public static final com.merlottv.app.presentation.common.CardPresenter.Companion Companion = null;
    
    public CardPresenter(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.leanback.widget.Presenter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.leanback.widget.Presenter.ViewHolder viewHolder, @org.jetbrains.annotations.Nullable()
    java.lang.Object item) {
    }
    
    @java.lang.Override()
    public void onUnbindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.leanback.widget.Presenter.ViewHolder viewHolder) {
    }
    
    private final void bindChannel(androidx.leanback.widget.ImageCardView card, com.merlottv.app.domain.model.Channel channel) {
    }
    
    private final void bindVodItem(androidx.leanback.widget.ImageCardView card, com.merlottv.app.domain.model.VodItem item) {
    }
    
    private final void bindSectionItem(androidx.leanback.widget.ImageCardView card, com.merlottv.app.presentation.common.SectionItem item) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/merlottv/app/presentation/common/CardPresenter$Companion;", "", "()V", "CARD_HEIGHT_CHANNEL", "", "CARD_HEIGHT_VOD", "CARD_WIDTH_CHANNEL", "CARD_WIDTH_VOD", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}