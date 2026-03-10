package com.merlottv.app.presentation.common

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavDirections
import com.merlottv.app.R
import com.merlottv.app.domain.model.VodItem
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Int
import kotlin.Suppress

public class SearchFragmentDirections private constructor() {
  private data class ActionSearchToVodDetail(
    public val vodItem: VodItem,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_search_to_vod_detail

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
    public fun actionSearchToVodDetail(vodItem: VodItem): NavDirections =
        ActionSearchToVodDetail(vodItem)
  }
}
