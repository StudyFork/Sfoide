package com.studyfork.sfoide.ui.friend.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.studyfork.sfoide.R
import com.studyfork.sfoide.data.model.Friend
import com.studyfork.sfoide.databinding.ActivityFriendDetailBinding

class FriendDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFriendDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_friend_detail)
        binding.lifecycleOwner = this

        val friend = intent.getParcelableExtra<Friend>(EXTRA_FRIEND)
        binding.friend = friend
    }

    companion object {
        const val EXTRA_FRIEND = "EXTRA_FRIEND"

        fun createIntent(context: Context, friend: Friend): Intent {
            return Intent(context, FriendDetailActivity::class.java).apply {
                putExtra(EXTRA_FRIEND, friend)
            }
        }
    }
}