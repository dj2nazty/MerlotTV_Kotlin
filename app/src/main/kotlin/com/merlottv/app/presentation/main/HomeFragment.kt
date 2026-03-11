package com.merlottv.app.presentation.main

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.merlottv.app.R
import com.merlottv.app.domain.model.Channel
import com.merlottv.app.domain.model.VodItem
import com.merlottv.app.presentation.common.CardPresenter
import com.merlottv.app.presentation.common.SectionItem
import com.merlottv.app.presentation.iptv.player.PlayerActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BrowseSupportFragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var rowsAdapter: ArrayObjectAdapter

    private val channelPresenter by lazy { CardPresenter(requireContext()) }
    private val vodPresenter     by lazy { CardPresenter(requireContext()) }
    private val sectionPresenter by lazy { CardPresenter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBrowseFragment()
        setupRowsAdapter()
        observeViewModel()
    }

    private fun setupBrowseFragment() {
        title = getString(R.string.app_name)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireContext(), R.color.merlot_primary)
        searchAffordanceColor = Color.WHITE

        setOnSearchClickedListener {
            findNavController().navigate(R.id.action_home_to_search)
        }

        setOnItemViewClickedListener { _, item, _, _ ->
            when (item) {
                is Channel     -> PlayerActivity.start(requireContext(), item)
                is VodItem     -> findNavController().navigate(R.id.action_home_to_guide)
                is SectionItem -> when (item.id) {
                    SectionItem.ID_TV_GUIDE        -> findNavController().navigate(R.id.action_home_to_guide)
                    SectionItem.ID_CHANNEL_CHECKER -> findNavController().navigate(R.id.action_home_to_checker)
                    SectionItem.ID_SETTINGS        -> findNavController().navigate(R.id.action_home_to_settings)
                }
            }
        }
    }

    private fun setupRowsAdapter() {
        rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        adapter = rowsAdapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {

                launch {
                    viewModel.channelGroups.collect { groups ->
                        rebuildChannelRows(groups)
                    }
                }

                launch {
                    viewModel.vodMovies.collect { movies ->
                        if (movies.isNotEmpty()) upsertVodRow(ROW_ID_VOD_MOVIES, "🎬  Movies", movies)
                    }
                }

                launch {
                    viewModel.vodSeries.collect { series ->
                        if (series.isNotEmpty()) upsertVodRow(ROW_ID_VOD_SERIES, "📺  Series", series)
                    }
                }

                launch {
                    viewModel.favourites.collect { favs ->
                        if (favs.isNotEmpty()) upsertChannelRow(ROW_ID_FAVS, "⭐  Favourites", favs)
                    }
                }
            }
        }
    }

    // ── Channel rows ──────────────────────────────────────────────────────────

    private fun rebuildChannelRows(groups: List<Pair<String, List<Channel>>>) {
        // Remove all old channel rows (IDs below 1000)
        val toRemove = (0 until rowsAdapter.size())
            .mapNotNull { rowsAdapter.get(it) as? ListRow }
            .filter { it.headerItem.id < 1000L }
        toRemove.forEach { rowsAdapter.remove(it) }

        // Re-insert at the top
        var pos = 0
        groups.take(10).forEach { (groupName, channels) ->
            val items = ArrayObjectAdapter(channelPresenter)
            channels.forEach { items.add(it) }
            rowsAdapter.add(pos++, ListRow(HeaderItem(pos.toLong(), "$groupName (${channels.size})"), items))
        }

        ensureStaticRows()
    }

    private fun upsertChannelRow(rowId: Long, label: String, channels: List<Channel>) {
        val existing = findRowById(rowId)
        if (existing != null) {
            val items = existing.adapter as ArrayObjectAdapter
            items.clear()
            channels.forEach { items.add(it) }
        } else {
            val items = ArrayObjectAdapter(channelPresenter)
            channels.forEach { items.add(it) }
            rowsAdapter.add(ListRow(HeaderItem(rowId, label), items))
        }
    }

    // ── VOD rows ──────────────────────────────────────────────────────────────

    private fun upsertVodRow(rowId: Long, label: String, vodItems: List<VodItem>) {
        val existing = findRowById(rowId)
        if (existing != null) {
            val items = existing.adapter as ArrayObjectAdapter
            items.clear()
            vodItems.forEach { items.add(it) }
        } else {
            val items = ArrayObjectAdapter(vodPresenter)
            vodItems.forEach { items.add(it) }
            rowsAdapter.add(ListRow(HeaderItem(rowId, label), items))
        }
    }

    // ── Static shortcut rows ──────────────────────────────────────────────────

    private fun ensureStaticRows() {
        listOf(
            ROW_ID_GUIDE    to "📅  TV Guide",
            ROW_ID_CHECKER  to "✅  Channel Checker",
            ROW_ID_SETTINGS to "⚙️  Settings",
        ).forEach { (rowId, label) ->
            if (findRowById(rowId) == null) {
                val items = ArrayObjectAdapter(sectionPresenter)
                items.add(SectionItem(rowId.toInt(), label.substringAfter("  ")))
                rowsAdapter.add(ListRow(HeaderItem(rowId, label), items))
            }
        }
    }

    private fun findRowById(id: Long): ListRow? {
        for (i in 0 until rowsAdapter.size()) {
            val row = rowsAdapter.get(i) as? ListRow
            if (row?.headerItem?.id == id) return row
        }
        return null
    }

    companion object {
        private const val ROW_ID_FAVS       = 9001L
        private const val ROW_ID_VOD_MOVIES = 9010L
        private const val ROW_ID_VOD_SERIES = 9011L
        private const val ROW_ID_GUIDE      = 9002L
        private const val ROW_ID_CHECKER    = 9003L
        private const val ROW_ID_SETTINGS   = 9004L
    }
}
