package com.merlottv.app.presentation.main

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.merlottv.app.R
import com.merlottv.app.domain.model.Channel
import com.merlottv.app.domain.model.VodItem
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeToIptvChannels(
    public val channel: Channel? = null,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_home_to_iptv_channels

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(Channel::class.java)) {
          result.putParcelable("channel", this.channel as Parcelable?)
        } else if (Serializable::class.java.isAssignableFrom(Channel::class.java)) {
          result.putSerializable("channel", this.channel as Serializable?)
        }
        return result
      }
  }

  private data class ActionHomeToVodDetail(
    public val vodItem: VodItem,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_home_to_vod_detail

    public override val arguments: Bundle
      @Suppress("CAST_NEVER_SUCCEEDS")
      get() {
        val result = Bundle()
        if (Parcelable::class.java.isAssignableFrom(VodItem::class.java)) {
          result.putParcelable("vodItem", this.vodItem as Parcelable)
        } else if (Serializable::class.java.isAssignableFrom(VodItem::class.java)) {
          result.putSerializable("vodItem", this.vodItem as Serializable)
        } else {
          throw UnsupportedOperationException(VodItem::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        return result
      }
  }

  public companion object {
    public fun actionHomeToIptvChannels(channel: Channel? = null): NavDirections =
        ActionHomeToIptvChannels(channel)

    public fun actionHomeToGuide(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_guide)

    public fun actionHomeToVodBrowse(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_vod_browse)

    public fun actionHomeToVodDetail(vodItem: VodItem): NavDirections =
        ActionHomeToVodDetail(vodItem)

    public fun actionHomeToChecker(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_checker)

    public fun actionHomeToSettings(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_settings)

    public fun actionHomeToSearch(): NavDirections =
        ActionOnlyNavDirections(R.id.action_home_to_search)
  }
}
