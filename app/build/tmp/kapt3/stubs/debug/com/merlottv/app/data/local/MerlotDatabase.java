package com.merlottv.app.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.merlottv.app.data.local.dao.*;
import com.merlottv.app.data.local.entity.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\r"}, d2 = {"Lcom/merlottv/app/data/local/MerlotDatabase;", "Landroidx/room/RoomDatabase;", "()V", "channelDao", "Lcom/merlottv/app/data/local/dao/ChannelDao;", "epgSourceDao", "Lcom/merlottv/app/data/local/dao/EpgSourceDao;", "playlistSourceDao", "Lcom/merlottv/app/data/local/dao/PlaylistSourceDao;", "programmeDao", "Lcom/merlottv/app/data/local/dao/ProgrammeDao;", "stremioAddonDao", "Lcom/merlottv/app/data/local/dao/StremioAddonDao;", "app_debug"})
@androidx.room.Database(entities = {com.merlottv.app.data.local.entity.ChannelEntity.class, com.merlottv.app.data.local.entity.PlaylistSourceEntity.class, com.merlottv.app.data.local.entity.ProgrammeEntity.class, com.merlottv.app.data.local.entity.EpgSourceEntity.class, com.merlottv.app.data.local.entity.StremioAddonEntity.class}, version = 1, exportSchema = true)
public abstract class MerlotDatabase extends androidx.room.RoomDatabase {
    
    public MerlotDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.data.local.dao.ChannelDao channelDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.data.local.dao.PlaylistSourceDao playlistSourceDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.data.local.dao.ProgrammeDao programmeDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.data.local.dao.EpgSourceDao epgSourceDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.merlottv.app.data.local.dao.StremioAddonDao stremioAddonDao();
}