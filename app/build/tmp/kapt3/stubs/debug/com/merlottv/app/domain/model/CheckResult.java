package com.merlottv.app.domain.model;

import android.os.Parcelable;
import kotlinx.parcelize.Parcelize;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\tH\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J=\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010 \u001a\u00020\tH\u00d6\u0001J\t\u0010!\u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016\u00a8\u0006\""}, d2 = {"Lcom/merlottv/app/domain/model/CheckResult;", "", "channel", "Lcom/merlottv/app/domain/model/Channel;", "status", "Lcom/merlottv/app/domain/model/StreamStatus;", "responseTimeMs", "", "httpCode", "", "error", "", "(Lcom/merlottv/app/domain/model/Channel;Lcom/merlottv/app/domain/model/StreamStatus;JILjava/lang/String;)V", "getChannel", "()Lcom/merlottv/app/domain/model/Channel;", "getError", "()Ljava/lang/String;", "getHttpCode", "()I", "getResponseTimeMs", "()J", "getStatus", "()Lcom/merlottv/app/domain/model/StreamStatus;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
public final class CheckResult {
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.model.Channel channel = null;
    @org.jetbrains.annotations.NotNull()
    private final com.merlottv.app.domain.model.StreamStatus status = null;
    private final long responseTimeMs = 0L;
    private final int httpCode = 0;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String error = null;
    
    public CheckResult(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.Channel channel, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.StreamStatus status, long responseTimeMs, int httpCode, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.domain.model.Channel getChannel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.domain.model.StreamStatus getStatus() {
        return null;
    }
    
    public final long getResponseTimeMs() {
        return 0L;
    }
    
    public final int getHttpCode() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.domain.model.Channel component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.domain.model.StreamStatus component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.domain.model.CheckResult copy(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.Channel channel, @org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.StreamStatus status, long responseTimeMs, int httpCode, @org.jetbrains.annotations.Nullable()
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}