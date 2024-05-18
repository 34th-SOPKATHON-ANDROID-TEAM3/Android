package org.sopt.sopkathon.android3.presentation.calendar.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.sopkathon.android3.data.model.response.ResponseDummyDto
import org.sopt.sopkathon.android3.databinding.ItemDummyBinding
import org.sopt.sopkathon.android3.databinding.ItemRockBinding

class CalendarViewHolder(
    private val binding: ItemRockBinding,
    private val onClick: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            onClick
        }
    }
    fun onBind(data: ResponseDummyDto){
        binding.run {
            ivRock.load("url")
        }
    }
}