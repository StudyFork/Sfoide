package com.studyfork.sfoide.ui.friend

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.studyfork.sfoide.R
import com.studyfork.sfoide.data.datasource.RemoteFriendDataSourceImpl
import com.studyfork.sfoide.ui.model.Friend
import com.studyfork.sfoide.data.remote.RetrofitService
import com.studyfork.sfoide.databinding.ActivityFriendBinding
import com.studyfork.sfoide.ui.friend.detail.FriendDetailActivity
import com.studyfork.sfoide.ui.utils.EndlessRecyclerViewScrollListener
import com.studyfork.sfoide.ui.utils.EventObserver

class FriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendBinding

    private var lastBackPressed: Long = 0L

    private val viewModel: FriendViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FriendViewModel(RemoteFriendDataSourceImpl(RetrofitService.friendApi)) as T
            }
        }
    }

    private lateinit var friendAdapter: FriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend)
        binding.lifecycleOwner = this
        binding.vm = viewModel

        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        friendAdapter = FriendAdapter(viewModel)
        binding.rvMain.adapter = friendAdapter
        binding.rvMain.addOnScrollListener(object : EndlessRecyclerViewScrollListener(binding.rvMain.layoutManager!!) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                viewModel.loadMoreFriends()
            }
        })
    }

    private fun observeViewModel() {
        with(viewModel) {
            friendList.observe(this@FriendActivity, Observer { friends ->
                friendAdapter.submitList(friends)
            })

            navigateDetailEvent.observe(
                this@FriendActivity,
                EventObserver(this@FriendActivity::navigateFriendDetailActivity)
            )
        }
    }

    private fun navigateFriendDetailActivity(friend: Friend) {
        startActivity(FriendDetailActivity.createIntent(this, friend))
    }

    override fun onBackPressed() {
        val current = System.currentTimeMillis()
        val term = current - lastBackPressed
        if (term > TWO_SECONDS) {
            Toast.makeText(this, "뒤로가기를 한번 더 눌러주세요", Toast.LENGTH_SHORT).show()
            lastBackPressed = current
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        private const val TWO_SECONDS = 2000
    }
}