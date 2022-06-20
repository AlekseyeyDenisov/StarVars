package ru.dw.starvars.presenter.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.android.synthetic.main.item_recycler.view.*
import ru.dw.starvars.databinding.ItemLoadMoreRecyclerBinding
import ru.dw.starvars.databinding.ItemRecyclerBinding
import ru.dw.starvars.domain.model.PeoplesItemView
import ru.dw.starvars.domain.model.PeoplesItemView.Companion.VIEW_TAPE_CHARACTER
import ru.dw.starvars.domain.model.PeoplesItemView.Companion.VIEW_TAPE_LOAD_MORE

class AdapterRecyclerListPeoples(
    private val onItemClickListener: OnItemClickListener
) : ListAdapter<PeoplesItemView, AdapterRecyclerListPeoples.ViewHolderPeopleItem>(
    RecyclerDiffUtilCallBack()
) {

    override fun getItemViewType(position: Int): Int {
        val data = getItem(position)
        return data.viewTape
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPeopleItem {
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
        return ViewHolderPeopleItem(layout)
    }

    override fun onBindViewHolder(holder: ViewHolderPeopleItem, position: Int) {
        val item = getItem(position)
        if (item.viewTape == VIEW_TAPE_CHARACTER) holder.bindPeople(item)
        else holder.binLoadMore(item)
    }

    inner class ViewHolderPeopleItem(private val viewBinding: ViewBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bindPeople(peoplesItemView: PeoplesItemView) {
            viewBinding.root.apply {
                nameCharacter.text = peoplesItemView.name
                setOnClickListener {
                    onItemClickListener.onItemClick(peoplesItemView)
                }
            }
        }

        fun binLoadMore(peoplesItemView: PeoplesItemView) {
            viewBinding.root.apply {
                setOnClickListener {
                    onItemClickListener.onItemClickLoadMore(peoplesItemView)
                }
            }

        }

    }
}