package com.merlottv.app.presentation.guide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.merlottv.app.domain.model.Channel
import com.merlottv.app.domain.model.Programme

data class GuideRow(
    val channel: Channel,
    val programmes: List<Programme>,
)

class GuideAdapter(
    private val onChannelClick: (Channel) -> Unit,
    private val onProgrammeClick: (Programme) -> Unit,
) : ListAdapter<GuideRow, GuideAdapter.ViewHolder>(DIFF) {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvChannelName: TextView = view.findViewById(android.R.id.text1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = getItem(position)
        holder.tvChannelName.text = row.channel.name
        holder.itemView.setOnClickListener { onChannelClick(row.channel) }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<GuideRow>() {
            override fun areItemsTheSame(a: GuideRow, b: GuideRow) = a.channel.id == b.channel.id
            override fun areContentsTheSame(a: GuideRow, b: GuideRow) = a == b
        }
    }
}
