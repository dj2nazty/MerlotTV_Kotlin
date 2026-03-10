package com.merlottv.app.presentation.guide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.merlottv.app.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class GuideFragment : Fragment() {

    private val viewModel: GuideViewModel by viewModels()
    private lateinit var guideAdapter: GuideAdapter
    private val dateFormatter = SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault())

    // Views
    private var tvDate: TextView?      = null
    private var btnPrevDay: Button?    = null
    private var btnNextDay: Button?    = null
    private var btnToday: Button?      = null
    private var rvGuide: RecyclerView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(R.layout.fragment_guide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvDate      = view.findViewById(R.id.tvDate)
        btnPrevDay  = view.findViewById(R.id.btnPrevDay)
        btnNextDay  = view.findViewById(R.id.btnNextDay)
        btnToday    = view.findViewById(R.id.btnToday)
        rvGuide     = view.findViewById(R.id.rvGuide)
        progressBar = view.findViewById(R.id.progressBar)

        setupDateNavigation()
        setupGuideRecyclerView()
        observeViewModel()
    }

    private fun setupDateNavigation() {
        btnPrevDay?.setOnClickListener { viewModel.previousDay() }
        btnNextDay?.setOnClickListener { viewModel.nextDay() }
        btnToday?.setOnClickListener   { viewModel.goToToday() }
    }

    private fun setupGuideRecyclerView() {
        guideAdapter = GuideAdapter(
            onChannelClick   = { channel -> viewModel.playChannel(channel) },
            onProgrammeClick = { programme -> showProgrammeDetail(programme) },
        )
        rvGuide?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter       = guideAdapter
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.selectedDate.collect { date ->
                        tvDate?.text = dateFormatter.format(Date(date))
                    }
                }
                launch {
                    viewModel.guideRows.collect { rows ->
                        guideAdapter.submitList(rows)
                        progressBar?.visibility = View.GONE
                    }
                }
                launch {
                    viewModel.isLoading.collect { loading ->
                        progressBar?.visibility = if (loading) View.VISIBLE else View.GONE
                    }
                }
            }
        }
    }

    private fun showProgrammeDetail(programme: com.merlottv.app.domain.model.Programme) {
        ProgrammeDetailDialog.show(parentFragmentManager, programme)
    }
}
