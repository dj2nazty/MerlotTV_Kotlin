package com.merlottv.app.data.repository;

import com.merlottv.app.data.local.dao.StremioAddonDao;
import com.merlottv.app.data.local.entity.StremioAddonEntity;
import com.merlottv.app.data.network.api.CinemetaApi;
import com.merlottv.app.data.network.api.StremioAddonApi;
import com.merlottv.app.data.network.dto.*;
import com.merlottv.app.domain.model.*;
import com.merlottv.app.domain.repository.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kotlinx.coroutines.flow.Flow;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\f\u0010\u000b\u001a\u00020\n*\u00020\fH\u0002\u001a\f\u0010\r\u001a\u00020\u000e*\u00020\u000fH\u0002\u001a\u000e\u0010\u0010\u001a\u0004\u0018\u00010\u0011*\u00020\u0012H\u0002\u00a8\u0006\u0013"}, d2 = {"toDomain", "Lcom/merlottv/app/domain/repository/StremioAddon;", "Lcom/merlottv/app/data/local/entity/StremioAddonEntity;", "gson", "Lcom/google/gson/Gson;", "toEntity", "toEpisode", "Lcom/merlottv/app/domain/model/Episode;", "Lcom/merlottv/app/data/network/dto/VideoDto;", "seriesId", "", "toStremioType", "Lcom/merlottv/app/domain/model/VodType;", "toVodItem", "Lcom/merlottv/app/domain/model/VodItem;", "Lcom/merlottv/app/data/network/dto/MetaDto;", "toVodStream", "Lcom/merlottv/app/domain/model/VodStream;", "Lcom/merlottv/app/data/network/dto/StreamDto;", "app_debug"})
public final class VodRepositoryImplKt {
    
    private static final java.lang.String toStremioType(com.merlottv.app.domain.model.VodType $this$toStremioType) {
        return null;
    }
    
    private static final com.merlottv.app.domain.model.VodItem toVodItem(com.merlottv.app.data.network.dto.MetaDto $this$toVodItem) {
        return null;
    }
    
    private static final com.merlottv.app.domain.model.Episode toEpisode(com.merlottv.app.data.network.dto.VideoDto $this$toEpisode, java.lang.String seriesId) {
        return null;
    }
    
    private static final com.merlottv.app.domain.model.VodStream toVodStream(com.merlottv.app.data.network.dto.StreamDto $this$toVodStream) {
        return null;
    }
    
    private static final com.merlottv.app.domain.repository.StremioAddon toDomain(com.merlottv.app.data.local.entity.StremioAddonEntity $this$toDomain, com.google.gson.Gson gson) {
        return null;
    }
    
    private static final com.merlottv.app.data.local.entity.StremioAddonEntity toEntity(com.merlottv.app.domain.repository.StremioAddon $this$toEntity, com.google.gson.Gson gson) {
        return null;
    }
}