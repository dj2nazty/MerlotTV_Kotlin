package com.merlottv.app.presentation.checker

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.merlottv.app.domain.model.CheckResult
import com.merlottv.app.domain.model.StreamStatus
import com.merlottv.app.domain.repository.ChannelRepository
import com.merlottv.app.domain.repository.CheckerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CheckerViewModel @Inject constructor(
    private val channelRepo: ChannelRepository,
    private val checkerRepo: CheckerRepository,
) : ViewModel() {

    private val _allResults  = MutableStateFlow<List<CheckResult>>(emptyList())
    private val _filter      = MutableStateFlow<StreamStatus?>(null)
    private val _isChecking  = MutableStateFlow(false)
    private val _progress    = MutableStateFlow(0 to 0)

    val isChecking: StateFlow<Boolean>      = _isChecking
    val progress: StateFlow<Pair<Int, Int>> = _progress

    val results: StateFlow<List<CheckResult>> = combine(_allResults, _filter) { all, filter ->
        if (filter == null) all else all.filter { it.status == filter }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private var checkJob: Job? = null

    // ─── Actions ─────────────────────────────────────────────────

    fun checkAll() {
        checkJob?.cancel()
        checkJob = viewModelScope.launch {
            _isChecking.value  = true
            _allResults.value  = emptyList()
            val channels = channelRepo.getAllChannels().first()
            _progress.value = 0 to channels.size

            checkerRepo.checkAll(channels).collect { result ->
                _allResults.update { it + result }
                _progress.update { (done, total) -> (done + 1) to total }
            }
            _isChecking.value = false
        }
    }

    fun stopCheck() {
        checkJob?.cancel()
        _isChecking.value = false
    }

    fun filterOnline()  { _filter.value = StreamStatus.ONLINE }
    fun filterOffline() { _filter.value = StreamStatus.OFFLINE }
    fun clearFilter()   { _filter.value = null }

    // ─── Export ──────────────────────────────────────────────────

    fun exportResults(context: Context) {
        viewModelScope.launch {
            val csv = buildString {
                appendLine("Channel,URL,Status,Response(ms),HTTP Code,Error")
                _allResults.value.forEach { r ->
                    appendLine(
                        "\"${r.channel.name}\",\"${r.channel.url}\"," +
                        "${r.status},${r.responseTimeMs},${r.httpCode},\"${r.error ?: ""}\""
                    )
                }
            }
            val date     = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
            val file     = File(context.cacheDir, "checker_$date.csv").also { it.writeText(csv) }
            val uri      = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/csv"
                putExtra(Intent.EXTRA_STREAM, uri)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
            context.startActivity(Intent.createChooser(shareIntent, "Export Results"))
        }
    }
}
