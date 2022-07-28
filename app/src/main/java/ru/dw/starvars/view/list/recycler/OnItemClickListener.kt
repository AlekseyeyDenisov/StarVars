package ru.dw.starvars.view.list.recycler

import ru.dw.starvars.domain.model.CharacterItemView

interface OnItemClickListener {
    fun onItemClick(characterItemView: CharacterItemView)
    fun onItemClickLoadMore(characterItemView: CharacterItemView)
}