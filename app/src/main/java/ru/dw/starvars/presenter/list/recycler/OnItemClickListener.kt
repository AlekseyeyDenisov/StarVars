package ru.dw.starvars.presenter.list.recycler

import ru.dw.starvars.domain.model.PeoplesItemView

interface OnItemClickListener {
    fun onItemClick(peoplesItemView: PeoplesItemView)
    fun onItemClickLoadMore(peoplesItemView: PeoplesItemView)
}