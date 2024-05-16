package org.sopt.sopkathon.android3.presentation.dummy.viewholder

import androidx.recyclerview.widget.RecyclerView
import org.sopt.sopkathon.android3.data.model.response.ResponseDummyDto
import org.sopt.sopkathon.android3.databinding.ItemDummyBinding

class DummyViewHolder(
    private val binding: ItemDummyBinding,
    private val onClick: () -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            onClick
        }
    }
    fun onBind(data: ResponseDummyDto){
        binding.tvDummy.text = data.id
    }
}