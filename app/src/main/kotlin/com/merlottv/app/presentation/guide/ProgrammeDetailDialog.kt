package com.merlottv.app.presentation.guide

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.merlottv.app.domain.model.Programme
import java.text.SimpleDateFormat
import java.util.*

class ProgrammeDetailDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title   = arguments?.getString(ARG_TITLE)   ?: ""
        val desc    = arguments?.getString(ARG_DESC)    ?: ""
        val start   = arguments?.getLong(ARG_START)     ?: 0L
        val end     = arguments?.getLong(ARG_END)       ?: 0L
        val fmt     = SimpleDateFormat("HH:mm", Locale.getDefault())
        val timeStr = "${fmt.format(Date(start))} – ${fmt.format(Date(end))}"

        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage("$timeStr\n\n$desc")
            .setPositiveButton("Close", null)
            .create()
    }

    companion object {
        private const val TAG       = "ProgrammeDetail"
        private const val ARG_TITLE = "title"
        private const val ARG_DESC  = "desc"
        private const val ARG_START = "start"
        private const val ARG_END   = "end"

        fun show(fm: FragmentManager, programme: Programme) {
            ProgrammeDetailDialog().apply {
                arguments = Bundle().apply {
                    putString(ARG_TITLE, programme.title)
                    putString(ARG_DESC,  programme.description)
                    putLong(ARG_START,   programme.startTime)
                    putLong(ARG_END,     programme.endTime)
                }
            }.show(fm, TAG)
        }
    }
}
