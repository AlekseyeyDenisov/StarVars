package ru.dw.starvars.presenter.list.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.dw.starvars.domain.model.PeoplesItemView

class RecyclerDiffUtilCallBack : DiffUtil.ItemCallback<PeoplesItemView>() {
    override fun areItemsTheSame(oldItem: PeoplesItemView, newItem: PeoplesItemView): Boolean {
        return oldItem.homeWorld == newItem.homeWorld
    }

    override fun areContentsTheSame(
        oldItem: PeoplesItemView,
        newItem: PeoplesItemView
    ): Boolean {
        return oldItem == newItem
    }
}