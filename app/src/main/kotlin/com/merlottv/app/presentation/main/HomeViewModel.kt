package com.merlottv.app.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merlottv.app.domain.model.*
import com.merlottv.app.domain.repository.ChannelRepository
import com.merlottv.app.domain.repository.VodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val channelRepo: ChannelRepository,
    private val vodRepo: VodRepository,
) : ViewModel() {

    /** Channel groups for sidebar rows (max 10 shown) */
    val channelGroups: StateFlow<List<Pair<String, List<Channel>>>> =
        channelRepo.getChannelGroups()
            .map { groups -> groups.map { it.name to it.channels } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val favourites: StateFlow<List<Channel>> =
        channelRepo.getFavouriteChannels()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val _vodMovies = MutableStateFlow<List<VodItem>>(emptyList())
    val vodMovies: StateFlow<List<VodItem>> = _vodMovies

    init {
        loadVodCatalogue()
    }

    private fun loadVodCatalogue() {
        viewModelScope.launch {
            vodRepo.getCatalogue(VodType.MOVIE, page = 1)
                .onSuccess { _vodMovies.value = it.take(20) }
        }
    }
}
