package com.merlottv.app.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.merlottv.app.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Stub fragment for the search screen.
 * The actual UI is rendered by the WebView in MainActivity.
 */
@AndroidEntryPoint
class SearchFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_search, container, false)
}
