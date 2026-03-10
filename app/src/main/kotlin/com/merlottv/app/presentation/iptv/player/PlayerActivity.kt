package com.merlottv.app.presentation.iptv.player

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.merlottv.app.databinding.ActivityPlayerBinding
import com.merlottv.app.domain.model.Channel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@UnstableApi
@AndroidEntryPoint
class PlayerActivity : FragmentActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private val viewModel: PlayerViewModel by viewModels()
    private var player: ExoPlayer? = null
    private var hideControlsJob: kotlinx.coroutines.Job? = null

    // Retry tracking — avoids infinite crash loops
    private var retryCount = 0
    private val maxRetries = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        @Suppress("DEPRECATION")
        val channel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_CHANNEL, Channel::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_CHANNEL)
        } ?: run { finish(); return }

        viewModel.setChannel(channel)
        observeState()
    }

    private fun initPlayer(channel: Channel) {
        // Always fully release before creating a new instance to free GPU/codec memory
        releasePlayer()

        player = viewModel.buildPlayer(this)
        binding.playerView.player = player

        val mediaItem = MediaItem.Builder()
            .setUri(channel.url)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(channel.name)
                    .setArtworkUri(
                        if (channel.logoUrl.isNotEmpty()) android.net.Uri.parse(channel.logoUrl)
                        else null
                    )
                    .build()
            )
            .build()

        player?.run {
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
            addListener(playerListener)
        }

        binding.tvChannelName.text = channel.name
        binding.playerView.useController = false
        retryCount = 0 // reset retries for new channel
        showControls()
    }

    private fun releasePlayer() {
        hideControlsJob?.cancel()
        player?.run {
            removeListener(playerListener)
            stop()
            clearMediaItems()
            release()
        }
        player = null
        // Detach from view so the SurfaceView is freed immediately
        binding.playerView.player = null
    }

    private fun retryPlayback() {
        if (retryCount >= maxRetries) {
            binding.tvError.visibility = View.VISIBLE
            binding.tvError.text = "Playback failed after $maxRetries retries.\nPress ← → to switch channel."
            return
        }
        retryCount++
        binding.tvError.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            delay(1_500L * retryCount) // back-off: 1.5s, 3s, 4.5s
            viewModel.currentChannel.value?.let { channel ->
                releasePlayer()
                initPlayer(channel)
            }
        }
    }

    private fun showControls(autoHideMs: Long = 4_000) {
        binding.controlsOverlay.visibility = View.VISIBLE
        hideControlsJob?.cancel()
        if (autoHideMs > 0) {
            hideControlsJob = lifecycleScope.launch {
                delay(autoHideMs)
                hideControls()
            }
        }
    }

    private fun hideControls() {
        binding.controlsOverlay.visibility = View.GONE
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_CENTER,
            KeyEvent.KEYCODE_ENTER -> {
                if (binding.controlsOverlay.visibility == View.VISIBLE) hideControls()
                else showControls()
                true
            }
            KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE,
            KeyEvent.KEYCODE_MEDIA_PLAY,
            KeyEvent.KEYCODE_MEDIA_PAUSE -> {
                player?.let { if (it.isPlaying) it.pause() else it.play() }
                showControls()
                true
            }
            KeyEvent.KEYCODE_DPAD_RIGHT -> { viewModel.nextChannel(); true }
            KeyEvent.KEYCODE_DPAD_LEFT  -> { viewModel.previousChannel(); true }
            KeyEvent.KEYCODE_BACK -> { finish(); true }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.currentChannel.collect { channel ->
                        channel ?: return@collect
                        retryCount = 0
                        releasePlayer()
                        initPlayer(channel)
                    }
                }
                launch {
                    viewModel.playerState.collect { state ->
                        binding.progressBar.visibility =
                            if (state == PlayerState.Buffering) View.VISIBLE else View.GONE
                        binding.tvError.visibility =
                            if (state is PlayerState.Error) View.VISIBLE else View.GONE
                        if (state is PlayerState.Error) {
                            binding.tvError.text = state.message
                        }
                    }
                }
            }
        }
    }

    private val playerListener = object : Player.Listener {

        override fun onPlaybackStateChanged(state: Int) {
            viewModel.onPlaybackStateChanged(state)
            // Clear progress spinner once buffering finishes
            if (state == Player.STATE_READY) {
                retryCount = 0
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            viewModel.onPlayerError(error.message ?: "Unknown error")

            when (error.errorCode) {
                // Codec / decoder ran out of memory — release everything and retry
                PlaybackException.ERROR_CODE_DECODER_INIT_FAILED,
                PlaybackException.ERROR_CODE_DECODING_FAILED,
                PlaybackException.ERROR_CODE_DECODING_FORMAT_EXCEEDS_CAPABILITIES,
                PlaybackException.ERROR_CODE_DECODING_FORMAT_NOT_SUPPORTED -> {
                    retryPlayback()
                }
                // Network hiccup — simple retry after short delay
                PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_FAILED,
                PlaybackException.ERROR_CODE_IO_NETWORK_CONNECTION_TIMEOUT,
                PlaybackException.ERROR_CODE_IO_BAD_HTTP_STATUS,
                PlaybackException.ERROR_CODE_IO_FILE_NOT_FOUND -> {
                    retryPlayback()
                }
                // Anything else — show error but still attempt once
                else -> {
                    if (retryCount == 0) retryPlayback()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (player == null) {
            viewModel.currentChannel.value?.let { initPlayer(it) }
        }
    }

    override fun onStop() {
        super.onStop()
        // Free codec memory when app goes to background — critical on low-RAM devices
        releasePlayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    companion object {
        const val EXTRA_CHANNEL = "extra_channel"

        fun start(context: Context, channel: Channel) {
            context.startActivity(
                Intent(context, PlayerActivity::class.java).putExtra(EXTRA_CHANNEL, channel)
            )
        }
    }
}
