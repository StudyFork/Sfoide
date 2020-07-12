package com.studyfork.sfoide.presentation.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.studyfork.sfoide.R
import com.studyfork.sfoide.base.BaseActivity
import com.studyfork.sfoide.databinding.ActivityMainBinding
import com.studyfork.sfoide.helper.BackPressCloseHelper
import com.studyfork.sfoide.presentation.detail.DetailActivity
import com.studyfork.sfoide.presentation.model.UserItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    SwipeRefreshLayout.OnRefreshListener {

    private val mainViewModel by viewModel<MainViewModel>()

    private val userAdapter by lazy {
        RandomUserAdapter().apply {
            onItemClickListener = object : RandomUserAdapter.OnItemClickListener {
                override fun onItemClick(data: UserItem) {
                    DetailActivity.startActivityWithUserItem(this@MainActivity, data)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.model = mainViewModel
        binding.rvRandomUser.adapter = userAdapter
        binding.srlMainActivity.setOnRefreshListener(this)

        mainViewModel.randomUsers.observe(this, Observer {
            userAdapter.replaceAll(it)
        })

        mainViewModel.loadRandomUsers()
    }

    override fun onRefresh() {
        mainViewModel.loadRandomUsers(true)
    }

    private val backPressCloseHelper by lazy { BackPressCloseHelper(this) }

    override fun onBackPressed() {
        backPressCloseHelper.onBackPressed()
    }
}