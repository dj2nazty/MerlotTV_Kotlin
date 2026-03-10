package com.merlottv.app.presentation.common

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.merlottv.app.R
import com.merlottv.app.domain.model.Channel
import com.merlottv.app.domain.model.VodItem

class CardPresenter(private val context: Context) : Presenter() {

    private val defaultBackground = ColorDrawable(
        ContextCompat.getColor(context, R.color.background_card)
    )

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val cardView = ImageCardView(context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            setBackgroundColor(ContextCompat.getColor(context, R.color.background_card))
        }
        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val cardView = viewHolder.view as ImageCardView
        when (item) {
            is Channel     -> bindChannel(cardView, item)
            is VodItem     -> bindVodItem(cardView, item)
            is SectionItem -> bindSectionItem(cardView, item)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val cardView = viewHolder.view as ImageCardView
        cardView.badgeImage = null
        cardView.mainImage  = defaultBackground
    }

    private fun bindChannel(card: ImageCardView, channel: Channel) {
        card.titleText   = channel.name
        card.contentText = channel.group
        card.setMainImageDimensions(CARD_WIDTH_CHANNEL, CARD_HEIGHT_CHANNEL)
        if (channel.logoUrl.isNotEmpty()) {
            card.mainImageView?.load(channel.logoUrl) {
                crossfade(true)
                placeholder(defaultBackground)
                error(defaultBackground)
                transformations(RoundedCornersTransformation(8f))
            }
        } else {
            card.mainImage = defaultBackground
        }
    }

    private fun bindVodItem(card: ImageCardView, item: VodItem) {
        card.titleText   = item.name
        card.contentText = "${item.year}  ★${item.imdbRating}"
        card.setMainImageDimensions(CARD_WIDTH_VOD, CARD_HEIGHT_VOD)
        if (item.posterUrl.isNotEmpty()) {
            card.mainImageView?.load(item.posterUrl) {
                crossfade(true)
                placeholder(defaultBackground)
                error(defaultBackground)
            }
        } else {
            card.mainImage = defaultBackground
        }
    }

    private fun bindSectionItem(card: ImageCardView, item: SectionItem) {
        card.titleText   = item.label
        card.contentText = ""
        card.setMainImageDimensions(CARD_WIDTH_CHANNEL, CARD_HEIGHT_CHANNEL)
        card.mainImage = defaultBackground
    }

    companion object {
        private const val CARD_WIDTH_CHANNEL  = 176
        private const val CARD_HEIGHT_CHANNEL = 100
        private const val CARD_WIDTH_VOD      = 120
        private const val CARD_HEIGHT_VOD     = 176
    }
}

data class SectionItem(
    val id: Int,
    val label: String,
) {
    companion object {
        const val ID_TV_GUIDE        = 9002
        const val ID_CHANNEL_CHECKER = 9003
        const val ID_SETTINGS        = 9004
    }
}
