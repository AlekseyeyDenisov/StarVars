package ru.dw.starvars.presenter.list.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.dw.starvars.domain.model.PeoplesItemView

class RecyclerDiffUtilCallBack:DiffUtil.ItemCallback<PeoplesItemView>() {
    override fun areItemsTheSame(entities: PeoplesItemView, newEntities: PeoplesItemView): Boolean {
        return entities.name == newEntities.name
    }

    override fun areContentsTheSame(oldEntities: PeoplesItemView, newEntities: PeoplesItemView): Boolean {
        return oldEntities == newEntities
    }
}