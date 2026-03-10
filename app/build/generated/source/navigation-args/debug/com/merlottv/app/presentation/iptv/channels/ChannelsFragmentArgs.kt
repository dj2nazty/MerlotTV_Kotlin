package com.merlottv.app.presentation.iptv.channels

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.merlottv.app.domain.model.Channel
import java.io.Serializable
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class ChannelsFragmentArgs(
  public val channel: Channel? = null,
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
    val result = Bundle()
    if (Parcelable::class.java.isAssignableFrom(Channel::class.java)) {
      result.putParcelable("channel", this.channel as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(Channel::class.java)) {
      result.putSerializable("channel", this.channel as Serializable?)
    }
    return result
  }

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(Channel::class.java)) {
      result.set("channel", this.channel as Parcelable?)
    } else if (Serializable::class.java.isAssignableFrom(Channel::class.java)) {
      result.set("channel", this.channel as Serializable?)
    }
    return result
  }

  public companion object {
    @JvmStatic
    @Suppress("DEPRECATION")
    public fun fromBundle(bundle: Bundle): ChannelsFragmentArgs {
      bundle.setClassLoader(ChannelsFragmentArgs::class.java.classLoader)
      val __channel : Channel?
      if (bundle.containsKey("channel")) {
        if (Parcelable::class.java.isAssignableFrom(Channel::class.java) ||
            Serializable::class.java.isAssignableFrom(Channel::class.java)) {
          __channel = bundle.get("channel") as Channel?
        } else {
          throw UnsupportedOperationException(Channel::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        __channel = null
      }
      return ChannelsFragmentArgs(__channel)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): ChannelsFragmentArgs {
      val __channel : Channel?
      if (savedStateHandle.contains("channel")) {
        if (Parcelable::class.java.isAssignableFrom(Channel::class.java) ||
            Serializable::class.java.isAssignableFrom(Channel::class.java)) {
          __channel = savedStateHandle.get<Channel?>("channel")
        } else {
          throw UnsupportedOperationException(Channel::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
      } else {
        __channel = null
      }
      return ChannelsFragmentArgs(__channel)
    }
  }
}
