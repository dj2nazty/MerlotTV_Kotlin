package com.merlottv.app.presentation.iptv.channels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.merlottv.app.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Stub fragment for the Live TV channels list.
 * The actual UI is rendered by the WebView in MainActivity.
 */
@AndroidEntryPoint
class ChannelsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_channels, container, false)
}
