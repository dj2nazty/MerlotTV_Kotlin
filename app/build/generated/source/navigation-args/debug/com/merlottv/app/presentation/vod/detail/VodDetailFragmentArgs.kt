package com.merlottv.app.presentation.vod.detail

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import com.merlottv.app.domain.model.VodItem
import java.io.Serializable
import java.lang.IllegalArgumentException
import java.lang.UnsupportedOperationException
import kotlin.Suppress
import kotlin.jvm.JvmStatic

public data class VodDetailFragmentArgs(
  public val vodItem: VodItem,
) : NavArgs {
  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toBundle(): Bundle {
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

  @Suppress("CAST_NEVER_SUCCEEDS")
  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    if (Parcelable::class.java.isAssignableFrom(VodItem::class.java)) {
      result.set("vodItem", this.vodItem as Parcelable)
    } else if (Serializable::class.java.isAssignableFrom(VodItem::class.java)) {
      result.set("vodItem", this.vodItem as Serializable)
    } else {
      throw UnsupportedOperationException(VodItem::class.java.name +
          " must implement Parcelable or Serializable or must be an Enum.")
    }
    return result
  }

  public companion object {
    @JvmStatic
    @Suppress("DEPRECATION")
    public fun fromBundle(bundle: Bundle): VodDetailFragmentArgs {
      bundle.setClassLoader(VodDetailFragmentArgs::class.java.classLoader)
      val __vodItem : VodItem?
      if (bundle.containsKey("vodItem")) {
        if (Parcelable::class.java.isAssignableFrom(VodItem::class.java) ||
            Serializable::class.java.isAssignableFrom(VodItem::class.java)) {
          __vodItem = bundle.get("vodItem") as VodItem?
        } else {
          throw UnsupportedOperationException(VodItem::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__vodItem == null) {
          throw IllegalArgumentException("Argument \"vodItem\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"vodItem\" is missing and does not have an android:defaultValue")
      }
      return VodDetailFragmentArgs(__vodItem)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): VodDetailFragmentArgs {
      val __vodItem : VodItem?
      if (savedStateHandle.contains("vodItem")) {
        if (Parcelable::class.java.isAssignableFrom(VodItem::class.java) ||
            Serializable::class.java.isAssignableFrom(VodItem::class.java)) {
          __vodItem = savedStateHandle.get<VodItem?>("vodItem")
        } else {
          throw UnsupportedOperationException(VodItem::class.java.name +
              " must implement Parcelable or Serializable or must be an Enum.")
        }
        if (__vodItem == null) {
          throw IllegalArgumentException("Argument \"vodItem\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"vodItem\" is missing and does not have an android:defaultValue")
      }
      return VodDetailFragmentArgs(__vodItem)
    }
  }
}
