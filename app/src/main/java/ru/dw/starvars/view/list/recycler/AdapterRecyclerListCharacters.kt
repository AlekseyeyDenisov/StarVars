package ru.dw.starvars.view.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ru.dw.starvars.databinding.ItemLoadMoreRecyclerBinding
import ru.dw.starvars.databinding.ItemRecyclerBinding
import ru.dw.starvars.domain.model.CharacterItemView
import ru.dw.starvars.utils.VIEW_TAPE_CHARACTER
import ru.dw.starvars.utils.VIEW_TAPE_LOAD_MORE

class AdapterRecyclerListCharacters(
    private val onItemClickListener: OnItemClickListener
) : ListAdapter<CharacterItemView, AdapterRecyclerListCharacters.ViewHolderChapterItem>(
    RecyclerDiffUtilCallBack()
) {

    override fun getItemViewType(position: Int): Int {
        val data = getItem(position)
        return data.viewTape
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderChapterItem {
        val layout = when (viewType) {
            VIEW_TAPE_CHARACTER -> {
                ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            VIEW_TAPE_LOAD_MORE -> {
                ItemLoadMoreRecyclerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            }
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        return ViewHolderChapterItem(layout)
    }

    override fun onBindViewHolder(holder: ViewHolderChapterItem, position: Int) {
        val item = getItem(position)
        if (item.viewTape == VIEW_TAPE_CHARACTER) holder.bindChapter(item)
        else holder.binLoadMore(item)
    }

    inner class ViewHolderChapterItem(viewBinding: ViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bindChapter(characterItemView: CharacterItemView) {
            ItemRecyclerBinding.bind(itemView).apply {
                nameCharacter.text = characterItemView.name
                root.setOnClickListener {
                    onItemClickListener.onItemClick(characterItemView)
                }
            }
        }

        fun binLoadMore(characterItemView: CharacterItemView) {
            ItemLoadMoreRecyclerBinding.bind(itemView).root.apply {
                setOnClickListener {
                    onItemClickListener.onItemClickLoadMore(characterItemView)
                }
            }

        }

    }
}