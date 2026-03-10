package com.merlottv.app.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

// ─────────────────────────────────────────────
// CHANNELS
// ─────────────────────────────────────────────

@Entity(
    tableName = "channels",
    indices = [
        Index("sourceId"),
        Index("group"),
        Index("tvgId"),
        Index("isFavourite"),
    ]
)
data class ChannelEntity(
    @PrimaryKey val id: String,
    val sourceId: Long,
    val name: String,
    val url: String,
    val logoUrl: String = "",
    val group: String = "Uncategorised",
    val tvgId: String = "",
    val tvgName: String = "",
    val isRadio: Boolean = false,
    val isFavourite: Boolean = false,
    val userAgent: String = "",
    val referrer: String = "",
    val sortOrder: Int = 0,
)

// ─────────────────────────────────────────────
// PLAYLIST SOURCES
// ─────────────────────────────────────────────

@Entity(tableName = "playlist_sources")
data class PlaylistSourceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val url: String,
    val lastRefreshed: Long = 0L,
    val channelCount: Int = 0,
    val isActive: Boolean = true,
)

// ─────────────────────────────────────────────
// EPG PROGRAMMES
// ─────────────────────────────────────────────

@Entity(
    tableName = "programmes",
    indices = [
        Index("channelId"),
        Index("startTime"),
        Index("endTime"),
    ]
)
data class ProgrammeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val channelId: String,
    val title: String,
    val description: String = "",
    val startTime: Long,
    val endTime: Long,
    val category: String = "",
    val iconUrl: String = "",
    val rating: String = "",
)

// ─────────────────────────────────────────────
// EPG SOURCES
// ─────────────────────────────────────────────

@Entity(tableName = "epg_sources")
data class EpgSourceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val url: String,
    val lastRefreshed: Long = 0L,
)

// ─────────────────────────────────────────────
// STREMIO ADDONS
// ─────────────────────────────────────────────

@Entity(tableName = "stremio_addons")
data class StremioAddonEntity(
    @PrimaryKey val id: String,
    val name: String,
    val version: String,
    val manifestUrl: String,
    val typesJson: String,     // JSON array string
    val catalogsJson: String,  // JSON array string
    val isEnabled: Boolean = true,
    val installedAt: Long = System.currentTimeMillis(),
)
