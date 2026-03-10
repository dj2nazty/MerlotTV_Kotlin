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
        player = viewModel.buildPlayer(this)
        binding.playerView.player = player

        val mediaItem = MediaItem.Builder()
            .setUri(channel.url)
            .setMediaMetadata(
                MediaMetadata.Builder()
                    .setTitle(channel.name)
                    .setArtworkUri(
                        if (channel.logoUrl.isNotEmpty()) android.net.Uri.parse(channel.logoUrl) else null
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
        showControls()
    }

    private fun releasePlayer() {
        player?.removeListener(playerListener)
        player?.release()
        player = null
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
            KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
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
            KeyEvent.KEYCODE_BACK       -> { finish(); true }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.currentChannel.collect { channel ->
                        channel ?: return@collect
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
                        if (state is PlayerState.Error) binding.tvError.text = state.message
                    }
                }
            }
        }
    }

    private val playerListener = object : Player.Listener {
        override fun onPlaybackStateChanged(state: Int) { viewModel.onPlaybackStateChanged(state) }
        override fun onPlayerError(error: androidx.media3.common.PlaybackException) {
            viewModel.onPlayerError(error.message ?: "Unknown error")
        }
    }

    override fun onStart()   { super.onStart();   if (player == null) viewModel.currentChannel.value?.let { initPlayer(it) } }
    override fun onStop()    { super.onStop();    releasePlayer() }
    override fun onDestroy() { super.onDestroy(); releasePlayer() }

    companion object {
        const val EXTRA_CHANNEL = "extra_channel"

        fun start(context: Context, channel: Channel) {
            context.startActivity(
                Intent(context, PlayerActivity::class.java).putExtra(EXTRA_CHANNEL, channel)
            )
        }
    }
}
