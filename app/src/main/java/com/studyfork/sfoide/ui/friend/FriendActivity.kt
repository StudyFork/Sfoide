package com.studyfork.sfoide.ui.friend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.studyfork.sfoide.R
import com.studyfork.sfoide.databinding.ActivityFriendBinding

class FriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendBinding

    private val viewModel: FriendViewModel by viewModels()

    private lateinit var friendAdapter: FriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend)
        binding.lifecycleOwner = this

        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        friendAdapter = FriendAdapter(viewModel)
        binding.rvMain.adapter =  friendAdapter
    }

    private fun observeViewModel() {
        viewModel.friendList.observe(this, Observer { friends ->
            friendAdapter.submitList(friends)
        })
    }
}