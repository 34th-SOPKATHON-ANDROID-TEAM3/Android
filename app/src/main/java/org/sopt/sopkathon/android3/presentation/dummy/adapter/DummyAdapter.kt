package org.sopt.sopkathon.android3.presentation.dummy.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import org.sopt.sopkathon.android3.data.model.response.ResponseDummyDto
import org.sopt.sopkathon.android3.databinding.ItemDummyBinding
import org.sopt.sopkathon.android3.presentation.dummy.viewholder.DummyViewHolder
import org.sopt.sopkathon.android3.util.view.ItemDiffCallback

class DummyAdapter(
    private val onClick: () -> Unit,
) : ListAdapter<ResponseDummyDto, DummyViewHolder>(DiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
        return DummyViewHolder(
            ItemDummyBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick
        )
    }

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        private val DiffUtil = ItemDiffCallback<ResponseDummyDto>(
            onItemsTheSame = { old, new -> old.id == new.id },
            onContentsTheSame = { old, new -> old == new }
        )
    }
}