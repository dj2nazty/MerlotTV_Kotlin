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
    private val cardPresenter by lazy { CardPresenter(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBrowseFragment()
        setupRowsAdapter()
        observeViewModel()
    }

    private fun setupBrowseFragment() {
        title        = getString(R.string.app_name)
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true
        brandColor = ContextCompat.getColor(requireContext(), R.color.merlot_primary)
        searchAffordanceColor = Color.WHITE

        setOnSearchClickedListener {
            findNavController().navigate(R.id.action_home_to_search)
        }

        setOnItemViewClickedListener { _, item, _, _ ->
            when (item) {
                is Channel   -> PlayerActivity.start(requireContext(), item)
                is VodItem   -> findNavController().navigate(R.id.action_home_to_guide)
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
        adapter     = rowsAdapter
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch { viewModel.channelGroups.collect { groups -> buildChannelRows(groups) } }
                launch { viewModel.vodMovies.collect    { movies  -> buildVodRow(movies)       } }
                launch { viewModel.favourites.collect   { favs    -> buildFavouritesRow(favs)  } }
            }
        }
    }

    private fun buildChannelRows(groups: List<Pair<String, List<Channel>>>) {
        val existingChannelRowCount = (0 until rowsAdapter.size()).count { i ->
            (rowsAdapter.get(i) as? ListRow)?.headerItem?.id?.let { it < 1000L } ?: false
        }
        repeat(existingChannelRowCount) { rowsAdapter.removeItems(0, 1) }

        var insertPos = 0
        groups.take(10).forEach { (groupName, channels) ->
            val itemAdapter = ArrayObjectAdapter(cardPresenter)
            channels.forEach { itemAdapter.add(it) }
            rowsAdapter.add(insertPos++, ListRow(HeaderItem(insertPos.toLong(), groupName), itemAdapter))
        }
        ensureStaticRows()
    }

    private fun buildVodRow(movies: List<VodItem>) {
        val existing    = findRowById(ROW_ID_VOD)
        val itemAdapter = (existing?.adapter as? ArrayObjectAdapter) ?: ArrayObjectAdapter(cardPresenter)
        if (existing == null) {
            movies.forEach { itemAdapter.add(it) }
            rowsAdapter.add(ListRow(HeaderItem(ROW_ID_VOD, getString(R.string.nav_vod)), itemAdapter))
        }
    }

    private fun buildFavouritesRow(favs: List<Channel>) {
        if (favs.isEmpty()) return
        val existing    = findRowById(ROW_ID_FAVS)
        val itemAdapter = (existing?.adapter as? ArrayObjectAdapter) ?: ArrayObjectAdapter(cardPresenter)
        if (existing == null) {
            favs.forEach { itemAdapter.add(it) }
            rowsAdapter.add(ListRow(HeaderItem(ROW_ID_FAVS, getString(R.string.nav_favourites)), itemAdapter))
        }
    }

    private fun ensureStaticRows() {
        listOf(
            ROW_ID_GUIDE    to R.string.nav_guide,
            ROW_ID_CHECKER  to R.string.nav_checker,
            ROW_ID_SETTINGS to R.string.nav_settings,
        ).forEach { (rowId, labelRes) ->
            if (findRowById(rowId) == null) {
                val items = ArrayObjectAdapter(cardPresenter)
                items.add(SectionItem(rowId.toInt(), getString(labelRes)))
                rowsAdapter.add(ListRow(HeaderItem(rowId, getString(labelRes)), items))
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
        private const val ROW_ID_VOD      = 9000L
        private const val ROW_ID_FAVS     = 9001L
        private const val ROW_ID_GUIDE    = 9002L
        private const val ROW_ID_CHECKER  = 9003L
        private const val ROW_ID_SETTINGS = 9004L
    }
}
