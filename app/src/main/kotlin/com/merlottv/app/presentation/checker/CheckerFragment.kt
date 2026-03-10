package com.merlottv.app.presentation.checker

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.merlottv.app.databinding.FragmentCheckerBinding
import com.merlottv.app.domain.model.CheckResult
import com.merlottv.app.domain.model.StreamStatus
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Channel Checker — tests all (or selected) channels in the background
 * and reports ONLINE / OFFLINE / TIMEOUT / ERROR status with response times.
 *
 * D-pad: Up/Down to move through results; OK to re-check highlighted channel.
 */
@AndroidEntryPoint
class CheckerFragment : Fragment() {

    private var _binding: FragmentCheckerBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CheckerViewModel by viewModels()
    private lateinit var resultsAdapter: CheckResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ) = FragmentCheckerBinding.inflate(inflater, container, false).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupResultsList()
        setupButtons()
        observeViewModel()
    }

    private fun setupToolbar() {
        binding.tvTitle.text = "Channel Checker"
    }

    private fun setupResultsList() {
        resultsAdapter = CheckResultAdapter()
        binding.rvResults.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = resultsAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun setupButtons() {
        binding.btnCheckAll.setOnClickListener   { viewModel.checkAll() }
        binding.btnCheckOnline.setOnClickListener { viewModel.filterOnline() }
        binding.btnCheckOffline.setOnClickListener { viewModel.filterOffline() }
        binding.btnStop.setOnClickListener { viewModel.stopCheck() }
        binding.btnExport.setOnClickListener { viewModel.exportResults(requireContext()) }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.results.collect { results ->
                        resultsAdapter.submitList(results)
                        updateSummary(results)
                    }
                }
                launch {
                    viewModel.isChecking.collect { checking ->
                        binding.progressBar.visibility = if (checking) View.VISIBLE else View.GONE
                        binding.btnCheckAll.isEnabled  = !checking
                        binding.btnStop.visibility     = if (checking) View.VISIBLE else View.GONE
                        binding.tvProgress.visibility  = if (checking) View.VISIBLE else View.GONE
                    }
                }
                launch {
                    viewModel.progress.collect { (done, total) ->
                        binding.tvProgress.text = "Checking $done / $total"
                        binding.progressBar.progress = if (total > 0) (done * 100 / total) else 0
                    }
                }
            }
        }
    }

    private fun updateSummary(results: List<CheckResult>) {
        val online  = results.count { it.status == StreamStatus.ONLINE }
        val offline = results.count { it.status == StreamStatus.OFFLINE || it.status == StreamStatus.TIMEOUT || it.status == StreamStatus.ERROR }
        binding.tvSummary.text = "✅ Online: $online  ❌ Offline: $offline  📊 Total: ${results.size}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
