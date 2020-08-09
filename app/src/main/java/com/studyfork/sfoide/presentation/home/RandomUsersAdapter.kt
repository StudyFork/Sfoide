package com.studyfork.sfoide.presentation.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.studyfork.sfoide.data.model.RandomUser
import com.studyfork.sfoide.databinding.ItemUserBinding
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject


class RandomUsersAdapter :
    ListAdapter<RandomUser, RandomUsersAdapter.RandomUserViewHolder>(DiffCallback()) {

    private val _itemClicks = PublishSubject.create<RandomUser>()
    val itemClicks: Observable<RandomUser> = _itemClicks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomUserViewHolder {
        return RandomUserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            binding.root.setOnClickListener {
                _itemClicks.onNext(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: RandomUserViewHolder, position: Int) {
        holder.setItem(getItem(position))
    }

    class RandomUserViewHolder(val binding: ItemUserBinding) :
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