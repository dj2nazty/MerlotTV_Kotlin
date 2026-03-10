package com.merlottv.app.domain.repository;

import com.merlottv.app.domain.model.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a6@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0007H\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00060\u0003H&\u00a8\u0006\r"}, d2 = {"Lcom/merlottv/app/domain/repository/CheckerRepository;", "", "checkAll", "Lkotlinx/coroutines/flow/Flow;", "Lcom/merlottv/app/domain/model/CheckResult;", "channels", "", "Lcom/merlottv/app/domain/model/Channel;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkChannel", "channel", "(Lcom/merlottv/app/domain/model/Channel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastResults", "app_debug"})
public abstract interface CheckerRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkChannel(@org.jetbrains.annotations.NotNull()
    com.merlottv.app.domain.model.Channel channel, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.merlottv.app.domain.model.CheckResult> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object checkAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.merlottv.app.domain.model.Channel> channels, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.merlottv.app.domain.model.CheckResult>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.merlottv.app.domain.model.CheckResult>> getLastResults();
}