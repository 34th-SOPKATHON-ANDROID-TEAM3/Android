package org.sopt.sopkathon.android3.presentation.calendar.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import org.sopt.sopkathon.android3.data.model.response.ResponseGetCollectedStonesDto
import org.sopt.sopkathon.android3.databinding.ItemRockBinding

class CalendarViewHolder(
    private val binding: ItemRockBinding,
    private val onClick: (ResponseGetCollectedStonesDto) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(data: ResponseGetCollectedStonesDto) {
        binding.run {
            ivRock.load(data.stoneImage)
        }
        binding.root.setOnClickListener {
            onClick(data)
        }
    }
}