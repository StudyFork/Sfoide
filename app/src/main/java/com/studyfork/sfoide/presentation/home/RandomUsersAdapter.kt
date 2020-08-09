package com.studyfork.sfoide.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyfork.sfoide.data.model.RandomUser
import com.studyfork.sfoide.databinding.ItemUserBinding

class RandomUsersAdapter :
    ListAdapter<RandomUser, RandomUsersAdapter.RandomUserViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomUserViewHolder {
        return RandomUserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RandomUserViewHolder, position: Int) {
        holder.setItem(getItem(position))
    }

    class RandomUserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setItem(user: RandomUser) {
            binding.user = user
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<RandomUser>() {
        override fun areItemsTheSame(
            oldItem: RandomUser,
            newItem: RandomUser
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: RandomUser,
            newItem: RandomUser
        ): Boolean {
            return oldItem == newItem
        }
    }
}