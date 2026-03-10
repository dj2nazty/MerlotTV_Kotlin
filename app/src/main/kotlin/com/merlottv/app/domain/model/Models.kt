package com.merlottv.app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Channel(
    val id: String,
    val name: String,
    val url: String,
    val logoUrl: String = "",
    val group: String = "Uncategorised",
    val tvgName: String = "",
    val isRadio: Boolean = false,
    val isFavourite: Boolean = false,
    val userAgent: String = "",
    val referrer: String = "",
) : Parcelable {
    companion object
}

data class ChannelGroup(
    val name: String,
    val channels: List<Channel>,
)

@Parcelize
data class Programme(
    val channelId: String,
    val title: String,
    val description: String = "",
    val startTime: Long,
    val endTime: Long,
    val category: String = "",
    val iconUrl: String = "",
    val rating: String = "",
) : Parcelable {
    val durationMinutes: Int get() = ((endTime - startTime) / 60_000).toInt()
    val isLive: Boolean get() {
        val now = System.currentTimeMillis()
        return now in startTime..endTime
    }
}

enum class VodType { MOVIE, SERIES }

@Parcelize
data class VodItem(
    val id: String,
    val type: VodType,
    val name: String,
    val posterUrl: String = "",
    val backgroundUrl: String = "",
    val year: String = "",
    val description: String = "",
    val genres: List<String> = emptyList(),
    val imdbRating: String = "",
    val cast: List<String> = emptyList(),
    val director: List<String> = emptyList(),
    val runtime: String = "",
) : Parcelable {
    companion object
}

@Parcelize
data class VodStream(
    val url: String,
    val title: String = "",
    val description: String = "",
    val addonName: String = "",
    val behaviorHints: BehaviorHints = BehaviorHints(),
) : Parcelable

@Parcelize
data class BehaviorHints(
    val notWebReady: Boolean = false,
    val bingeGroup: String? = null,
) : Parcelable

@Parcelize
data class Episode(
    val id: String,
    val seriesId: String,
    val season: Int,
    val episode: Int,
    val title: String,
    val overview: String = "",
    val thumbUrl: String = "",
    val firstAired: String = "",
) : Parcelable

enum class StreamStatus { PENDING, ONLINE, OFFLINE, TIMEOUT, ERROR }

data class CheckResult(
    val channel: Channel,
    val status: StreamStatus,
    val responseTimeMs: Long = 0,
    val httpCode: Int = 0,
    val error: String? = null,
)

data class PlaylistSource(
    val id: Long = 0,
    val name: String,
    val url: String,
    val lastRefreshed: Long = 0,
    val channelCount: Int = 0,
    val isActive: Boolean = true,
)

data class EpgSource(
    val id: Long = 0,
    val name: String,
    val url: String,
    val lastRefreshed: Long = 0,
)
