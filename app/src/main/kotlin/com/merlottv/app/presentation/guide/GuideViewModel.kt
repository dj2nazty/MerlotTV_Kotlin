package com.merlottv.app.presentation.guide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merlottv.app.domain.model.Channel
import com.merlottv.app.domain.repository.ChannelRepository
import com.merlottv.app.domain.repository.EpgRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class GuideViewModel @Inject constructor(
    private val channelRepo: ChannelRepository,
    private val epgRepo: EpgRepository,
) : ViewModel() {

    private val _selectedDate = MutableStateFlow(todayMidnight())
    val selectedDate: StateFlow<Long> = _selectedDate

    fun previousDay() { _selectedDate.update { it - DAY_MS } }
    fun nextDay()     { _selectedDate.update { it + DAY_MS } }
    fun goToToday()   { _selectedDate.value = todayMidnight() }

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val guideRows: StateFlow<List<GuideRow>> = combine(
        channelRepo.getAllChannels(),
        _selectedDate.flatMapLatest { date -> epgRepo.getGuideForDate(date) },
    ) { channels, programmeMap ->
        channels.map { channel ->
            GuideRow(channel, programmeMap[channel.id] ?: emptyList())
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val currentTimeOffsetPx: Flow<Int> = flow {
        while (true) {
            val now     = System.currentTimeMillis()
            val start   = todayMidnight()
            val minutes = ((now - start) / 60_000).toInt()
            emit(minutes * PX_PER_MINUTE)
            kotlinx.coroutines.delay(60_000)
        }
    }

    private val _playChannelEvent = MutableSharedFlow<Channel>()
    val playChannelEvent: SharedFlow<Channel> = _playChannelEvent

    fun playChannel(channel: Channel) {
        viewModelScope.launch { _playChannelEvent.emit(channel) }
    }

    companion object {
        private const val DAY_MS    = 24 * 60 * 60 * 1_000L
        const val PX_PER_MINUTE     = 4

        fun todayMidnight(): Long {
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR_OF_DAY, 0)
            cal.set(Calendar.MINUTE, 0)
            cal.set(Calendar.SECOND, 0)
            cal.set(Calendar.MILLISECOND, 0)
            return cal.timeInMillis
        }
    }
}
