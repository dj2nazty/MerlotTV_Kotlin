package com.merlottv.app.presentation.common

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import android.widget.ImageView
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
        val card = viewHolder.view as ImageCardView
        when (item) {
            is Channel     -> bindChannel(card, item)
            is VodItem     -> bindVodItem(card, item)
            is SectionItem -> bindSectionItem(card, item)
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        val card = viewHolder.view as ImageCardView
        card.badgeImage = null
        card.mainImage = defaultBackground
    }

    private fun bindChannel(card: ImageCardView, channel: Channel) {
        card.titleText = channel.name
        card.contentText = channel.group
        // Set dimensions FIRST so Coil loads into the correct size
        card.setMainImageDimensions(CARD_WIDTH_CHANNEL, CARD_HEIGHT_CHANNEL)
        card.mainImageView?.scaleType = ImageView.ScaleType.FIT_CENTER
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
        card.titleText = item.name
        // year is a String in the model, so just check isNotEmpty
        val meta = buildList {
            if (item.year.isNotEmpty()) add(item.year)
            if (item.imdbRating.isNotEmpty() && item.imdbRating != "0") add("★ ${item.imdbRating}")
        }.joinToString("  ")
        card.contentText = meta

        // Large portrait poster — matches HTML app style
        card.setMainImageDimensions(CARD_WIDTH_VOD, CARD_HEIGHT_VOD)
        card.mainImageView?.scaleType = ImageView.ScaleType.CENTER_CROP
        if (item.posterUrl.isNotEmpty()) {
            card.mainImageView?.load(item.posterUrl) {
                crossfade(true)
                placeholder(defaultBackground)
                error(defaultBackground)
                transformations(RoundedCornersTransformation(8f))
            }
        } else {
            card.mainImage = defaultBackground
        }
    }

    private fun bindSectionItem(card: ImageCardView, item: SectionItem) {
        card.titleText = item.label
        card.contentText = ""
        card.setMainImageDimensions(CARD_WIDTH_SECTION, CARD_HEIGHT_SECTION)

        val tileColor = when (item.id) {
            SectionItem.ID_TV_GUIDE        -> 0xFF1A6B3A.toInt()
            SectionItem.ID_CHANNEL_CHECKER -> 0xFF1A4A6B.toInt()
            SectionItem.ID_SETTINGS        -> 0xFF3A2A6B.toInt()
            else                           -> 0xFF722F37.toInt()
        }
        card.mainImage = GradientDrawable().apply {
            setColor(tileColor)
            cornerRadius = 12f
        }
    }

    companion object {
        const val CARD_WIDTH_CHANNEL  = 200
        const val CARD_HEIGHT_CHANNEL = 112
        const val CARD_WIDTH_VOD      = 160
        const val CARD_HEIGHT_VOD     = 240
        const val CARD_WIDTH_SECTION  = 200
        const val CARD_HEIGHT_SECTION = 112
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
