package com.merlottv.app.data.repository;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.*;
import com.merlottv.app.data.local.dao.EpgSourceDao;
import com.merlottv.app.data.local.dao.ProgrammeDao;
import com.merlottv.app.data.local.entity.EpgSourceEntity;
import com.merlottv.app.data.local.entity.ProgrammeEntity;
import com.merlottv.app.data.parser.XmltvParser;
import com.merlottv.app.domain.model.*;
import com.merlottv.app.domain.repository.*;
import dagger.hilt.android.qualifiers.ApplicationContext;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import java.util.Calendar;
import javax.inject.Inject;
import javax.inject.Singleton;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\b\u001a\u00020\t*\u00020\nH\u0002\u001a\f\u0010\b\u001a\u00020\u000b*\u00020\fH\u0002\u001a\f\u0010\r\u001a\u00020\n*\u00020\tH\u0002\u001a\f\u0010\r\u001a\u00020\f*\u00020\u000bH\u0002\"%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005\u00a8\u0006\u000e"}, d2 = {"dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "Landroid/content/Context;", "getDataStore", "(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", "dataStore$delegate", "Lkotlin/properties/ReadOnlyProperty;", "toDomain", "Lcom/merlottv/app/domain/model/EpgSource;", "Lcom/merlottv/app/data/local/entity/EpgSourceEntity;", "Lcom/merlottv/app/domain/model/Programme;", "Lcom/merlottv/app/data/local/entity/ProgrammeEntity;", "toEntity", "app_debug"})
public final class OtherRepositoriesKt {
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.properties.ReadOnlyProperty dataStore$delegate = null;
    
    private static final androidx.datastore.core.DataStore<androidx.datastore.preferences.core.Preferences> getDataStore(android.content.Context $this$dataStore) {
        return null;
    }
    
    private static final com.merlottv.app.domain.model.EpgSource toDomain(com.merlottv.app.data.local.entity.EpgSourceEntity $this$toDomain) {
        return null;
    }
    
    private static final com.merlottv.app.data.local.entity.EpgSourceEntity toEntity(com.merlottv.app.domain.model.EpgSource $this$toEntity) {
        return null;
    }
    
    private static final com.merlottv.app.domain.model.Programme toDomain(com.merlottv.app.data.local.entity.ProgrammeEntity $this$toDomain) {
        return null;
    }
    
    private static final com.merlottv.app.data.local.entity.ProgrammeEntity toEntity(com.merlottv.app.domain.model.Programme $this$toEntity) {
        return null;
    }
}