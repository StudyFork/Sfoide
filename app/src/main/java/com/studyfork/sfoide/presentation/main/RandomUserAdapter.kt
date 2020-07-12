package com.studyfork.sfoide.presentation.main

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.studyfork.sfoide.R
import com.studyfork.sfoide.base.BaseViewHolder
import com.studyfork.sfoide.databinding.ItemUserBinding
import com.studyfork.sfoide.presentation.model.UserItem

class RandomUserAdapter : RecyclerView.Adapter<BaseViewHolder<ViewDataBinding, UserItem>>() {

    private val items = mutableListOf<UserItem>()

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(data: UserItem)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ViewDataBinding, UserItem> {
        return SampleViewHolder(parent).apply {
            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(
                    items[adapterPosition]
                )
            }
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(
        holder: BaseViewHolder<ViewDataBinding, UserItem>,
        position: Int
    ) {
        holder.bind(items[position])
    }

    fun replaceAll(items: List<UserItem>) {
        this.items.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    class SampleViewHolder(parent: ViewGroup) :
        BaseViewHolder<ItemUserBinding, UserItem>(parent, R.layout.item_user) {

        override fun bind(data: UserItem) {
            binding.userItem = data
        }

        override fun recycled() {}
    }
}