package com.merlottv.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.merlottv.app.data.local.dao.*
import com.merlottv.app.data.local.entity.*

@Database(
    entities = [
        ChannelEntity::class,
        PlaylistSourceEntity::class,
        ProgrammeEntity::class,
        EpgSourceEntity::class,
        StremioAddonEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class MerlotDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
    abstract fun playlistSourceDao(): PlaylistSourceDao
    abstract fun programmeDao(): ProgrammeDao
    abstract fun epgSourceDao(): EpgSourceDao
    abstract fun stremioAddonDao(): StremioAddonDao
}
