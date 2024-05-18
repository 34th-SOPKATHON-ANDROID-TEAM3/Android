package org.sopt.sopkathon.android3.presentation.calendar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.sopkathon.android3.data.model.response.ResponseGetCollectedStonesDto
import org.sopt.sopkathon.android3.databinding.ItemRockBinding
import org.sopt.sopkathon.android3.presentation.calendar.viewholder.CalendarViewHolder
import org.sopt.sopkathon.android3.util.view.ItemDiffCallback

class CalendarAdapter(
    private val onClick: (ResponseGetCollectedStonesDto) -> Unit,
) : ListAdapter<ResponseGetCollectedStonesDto, CalendarViewHolder>(DiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            ItemRockBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick
        )
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<ResponseGetCollectedStonesDto>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}