package com.merlottv.app.domain.repository;

import com.merlottv.app.domain.model.*;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b*\b\u0086\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0011J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0005H\u00c6\u0003J\t\u0010%\u001a\u00020\u0005H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\'\u001a\u00020\bH\u00c6\u0003J\t\u0010(\u001a\u00020\bH\u00c6\u0003J\t\u0010)\u001a\u00020\u0005H\u00c6\u0003J\t\u0010*\u001a\u00020\bH\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\bH\u00c6\u0003J\u0081\u0001\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010.\u001a\u00020\b2\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00100\u001a\u00020\u0005H\u00d6\u0001J\t\u00101\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0010\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\r\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0015\u00a8\u00062"}, d2 = {"Lcom/merlottv/app/domain/repository/AppSettings;", "", "defaultUserAgent", "", "epgRefreshIntervalHours", "", "m3uRefreshIntervalHours", "preferExternalPlayer", "", "hardwareDecoding", "bufferSizeSeconds", "subtitlesEnabled", "defaultAudioLanguage", "parenralControlEnabled", "parentalPin", "themeMode", "epgDaysAhead", "(Ljava/lang/String;IIZZIZLjava/lang/String;ZLjava/lang/String;Ljava/lang/String;I)V", "getBufferSizeSeconds", "()I", "getDefaultAudioLanguage", "()Ljava/lang/String;", "getDefaultUserAgent", "getEpgDaysAhead", "getEpgRefreshIntervalHours", "getHardwareDecoding", "()Z", "getM3uRefreshIntervalHours", "getParenralControlEnabled", "getParentalPin", "getPreferExternalPlayer", "getSubtitlesEnabled", "getThemeMode", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class AppSettings {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String defaultUserAgent = null;
    private final int epgRefreshIntervalHours = 0;
    private final int m3uRefreshIntervalHours = 0;
    private final boolean preferExternalPlayer = false;
    private final boolean hardwareDecoding = false;
    private final int bufferSizeSeconds = 0;
    private final boolean subtitlesEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String defaultAudioLanguage = null;
    private final boolean parenralControlEnabled = false;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String parentalPin = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String themeMode = null;
    private final int epgDaysAhead = 0;
    
    public AppSettings(@org.jetbrains.annotations.NotNull()
    java.lang.String defaultUserAgent, int epgRefreshIntervalHours, int m3uRefreshIntervalHours, boolean preferExternalPlayer, boolean hardwareDecoding, int bufferSizeSeconds, boolean subtitlesEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultAudioLanguage, boolean parenralControlEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String parentalPin, @org.jetbrains.annotations.NotNull()
    java.lang.String themeMode, int epgDaysAhead) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDefaultUserAgent() {
        return null;
    }
    
    public final int getEpgRefreshIntervalHours() {
        return 0;
    }
    
    public final int getM3uRefreshIntervalHours() {
        return 0;
    }
    
    public final boolean getPreferExternalPlayer() {
        return false;
    }
    
    public final boolean getHardwareDecoding() {
        return false;
    }
    
    public final int getBufferSizeSeconds() {
        return 0;
    }
    
    public final boolean getSubtitlesEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDefaultAudioLanguage() {
        return null;
    }
    
    public final boolean getParenralControlEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getParentalPin() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getThemeMode() {
        return null;
    }
    
    public final int getEpgDaysAhead() {
        return 0;
    }
    
    public AppSettings() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    public final int component12() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final boolean component4() {
        return false;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component8() {
        return null;
    }
    
    public final boolean component9() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.merlottv.app.domain.repository.AppSettings copy(@org.jetbrains.annotations.NotNull()
    java.lang.String defaultUserAgent, int epgRefreshIntervalHours, int m3uRefreshIntervalHours, boolean preferExternalPlayer, boolean hardwareDecoding, int bufferSizeSeconds, boolean subtitlesEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultAudioLanguage, boolean parenralControlEnabled, @org.jetbrains.annotations.NotNull()
    java.lang.String parentalPin, @org.jetbrains.annotations.NotNull()
    java.lang.String themeMode, int epgDaysAhead) {
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