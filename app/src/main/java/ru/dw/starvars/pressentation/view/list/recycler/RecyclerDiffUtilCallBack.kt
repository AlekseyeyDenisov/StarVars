package ru.dw.starvars.pressentation.view.list.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.dw.starvars.domain.model.CharacterItemView

class RecyclerDiffUtilCallBack : DiffUtil.ItemCallback<CharacterItemView>() {
    override fun areItemsTheSame(oldItem: CharacterItemView, newItem: CharacterItemView): Boolean {
        return oldItem.homeWorld == newItem.homeWorld
    }

    override fun areContentsTheSame(
        oldItem: CharacterItemView,
        newItem: CharacterItemView
    ): Boolean {
        return oldItem == newItem
    }
}