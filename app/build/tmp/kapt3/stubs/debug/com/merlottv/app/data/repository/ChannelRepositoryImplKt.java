package com.merlottv.app.data.repository;

import com.merlottv.app.data.local.dao.ChannelDao;
import com.merlottv.app.data.local.dao.PlaylistSourceDao;
import com.merlottv.app.data.local.entity.ChannelEntity;
import com.merlottv.app.data.local.entity.PlaylistSourceEntity;
import com.merlottv.app.data.parser.M3uParser;
import com.merlottv.app.domain.model.*;
import com.merlottv.app.domain.repository.ChannelRepository;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0002\u001a\u001e\u0010\u0005\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0002\u001a\f\u0010\u0005\u001a\u00020\u0004*\u00020\u0003H\u0002\u00a8\u0006\n"}, d2 = {"toDomain", "Lcom/merlottv/app/domain/model/Channel;", "Lcom/merlottv/app/data/local/entity/ChannelEntity;", "Lcom/merlottv/app/domain/model/PlaylistSource;", "Lcom/merlottv/app/data/local/entity/PlaylistSourceEntity;", "toEntity", "sourceId", "", "sortOrder", "", "app_debug"})
public final class ChannelRepositoryImplKt {
    
    private static final com.merlottv.app.domain.model.Channel toDomain(com.merlottv.app.data.local.entity.ChannelEntity $this$toDomain) {
        return null;
    }
    
    private static final com.merlottv.app.data.local.entity.ChannelEntity toEntity(com.merlottv.app.domain.model.Channel $this$toEntity, long sourceId, int sortOrder) {
        return null;
    }
    
    private static final com.merlottv.app.domain.model.PlaylistSource toDomain(com.merlottv.app.data.local.entity.PlaylistSourceEntity $this$toDomain) {
        return null;
    }
    
    private static final com.merlottv.app.data.local.entity.PlaylistSourceEntity toEntity(com.merlottv.app.domain.model.PlaylistSource $this$toEntity) {
        return null;
    }
}