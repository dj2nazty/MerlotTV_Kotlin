package com.merlottv.app.presentation.iptv.player

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.okhttp.OkHttpDataSource
import androidx.media3.exoplayer.DefaultLoadControl
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import com.merlottv.app.domain.model.Channel
import com.merlottv.app.domain.repository.AppSettings
import com.merlottv.app.domain.repository.ChannelRepository
import com.merlottv.app.domain.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import javax.inject.Inject

sealed class PlayerState {
    object Idle      : PlayerState()
    object Buffering : PlayerState()
    object Ready     : PlayerState()
    object Ended     : PlayerState()
    data class Error(val message: String) : PlayerState()
}

@UnstableApi
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val channelRepo: ChannelRepository,
    private val settingsRepo: SettingsRepository,
    private val okHttpClient: OkHttpClient,
) : ViewModel() {

    private val _currentChannel = MutableStateFlow<Channel?>(null)
    val currentChannel: StateFlow<Channel?> = _currentChannel

    private val _playerState = MutableStateFlow<PlayerState>(PlayerState.Idle)
    val playerState: StateFlow<PlayerState> = _playerState

    private var allChannels: List<Channel> = emptyList()
    private var currentIndex: Int = -1

    // Cache settings as a StateFlow
    private val _settings = MutableStateFlow<AppSettings?>(null)

    init {
        viewModelScope.launch {
            channelRepo.getAllChannels().collect { allChannels = it }
        }
        viewModelScope.launch {
            settingsRepo.getSettings().collect { _settings.value = it }
        }
    }

    fun setChannel(channel: Channel) {
        _currentChannel.value = channel
        currentIndex = allChannels.indexOfFirst { it.id == channel.id }
        _playerState.value = PlayerState.Idle
    }

    fun nextChannel() {
        if (allChannels.isEmpty()) return
        currentIndex = (currentIndex + 1) % allChannels.size
        setChannel(allChannels[currentIndex])
    }

    fun previousChannel() {
        if (allChannels.isEmpty()) return
        currentIndex = if (currentIndex <= 0) allChannels.lastIndex else currentIndex - 1
        setChannel(allChannels[currentIndex])
    }

    fun onPlaybackStateChanged(state: Int) {
        _playerState.value = when (state) {
            Player.STATE_IDLE      -> PlayerState.Idle
            Player.STATE_BUFFERING -> PlayerState.Buffering
            Player.STATE_READY     -> PlayerState.Ready
            Player.STATE_ENDED     -> PlayerState.Ended
            else                   -> PlayerState.Idle
        }
    }

    fun onPlayerError(message: String) {
        _playerState.value = PlayerState.Error(message)
    }

    fun buildPlayer(context: Context): ExoPlayer {
        val settings = _settings.value

        val dataSourceFactory = OkHttpDataSource.Factory(okHttpClient).apply {
            val ua = _currentChannel.value?.userAgent?.takeIf { it.isNotEmpty() }
                ?: settings?.defaultUserAgent
                ?: "MerlotTV/2.0"
            setDefaultRequestProperties(mapOf("User-Agent" to ua))
            _currentChannel.value?.referrer?.takeIf { it.isNotEmpty() }?.let { ref ->
                setDefaultRequestProperties(mapOf("Referer" to ref))
            }
        }

        val loadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                2_000,
                (settings?.bufferSizeSeconds ?: 30) * 1_000,
                1_000,
                2_000,
            )
            .build()

        return ExoPlayer.Builder(context)
            .setMediaSourceFactory(DefaultMediaSourceFactory(dataSourceFactory))
            .setLoadControl(loadControl)
            .build()
    }
}
