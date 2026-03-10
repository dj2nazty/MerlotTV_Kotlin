package com.merlottv.app.presentation.checker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merlottv.app.R
import com.merlottv.app.databinding.ItemCheckResultBinding
import com.merlottv.app.domain.model.CheckResult
import com.merlottv.app.domain.model.StreamStatus

class CheckResultAdapter : ListAdapter<CheckResult, CheckResultAdapter.VH>(DIFF) {

    inner class VH(private val binding: ItemCheckResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: CheckResult) {
            binding.tvChannelName.text = result.channel.name
            binding.tvChannelGroup.text = result.channel.group
            binding.tvUrl.text = result.channel.url
            binding.tvResponseTime.text = "${result.responseTimeMs}ms"

            val (statusText, colorRes) = when (result.status) {
                StreamStatus.ONLINE  -> "● ONLINE"  to R.color.status_online
                StreamStatus.OFFLINE -> "● OFFLINE" to R.color.status_offline
                StreamStatus.TIMEOUT -> "● TIMEOUT" to R.color.status_timeout
                StreamStatus.ERROR   -> "● ERROR"   to R.color.status_offline
                StreamStatus.PENDING -> "○ PENDING" to R.color.status_pending
            }
            binding.tvStatus.text = statusText
            binding.tvStatus.setTextColor(ContextCompat.getColor(binding.root.context, colorRes))

            binding.tvError.text = result.error ?: ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemCheckResultBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<CheckResult>() {
            override fun areItemsTheSame(a: CheckResult, b: CheckResult) =
                a.channel.id == b.channel.id
            override fun areContentsTheSame(a: CheckResult, b: CheckResult) =
                a == b
        }
    }
}
