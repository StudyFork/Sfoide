package com.studyfork.sfoide.ui.friend

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyfork.sfoide.R
import com.studyfork.sfoide.ui.model.Friend
import com.studyfork.sfoide.databinding.ItemFriendBinding

class FriendAdapter(
    val viewModel: FriendViewModel
) : ListAdapter<Friend, FriendAdapter.FriendViewHolder>(object : DiffUtil.ItemCallback<Friend?>() {
    override fun areItemsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Friend, newItem: Friend): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val binding: ItemFriendBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_friend,
            parent,
            false
        )

        val holder = FriendViewHolder(binding)

        binding.root.setOnClickListener {
            viewModel.navigateFriendDetail(getItem(holder.adapterPosition))
        }

        return holder
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class FriendViewHolder(private val binding: ItemFriendBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(friend: Friend) {
            binding.friend = friend
            binding.executePendingBindings()
        }
    }
}