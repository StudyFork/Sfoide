package com.studyfork.sfoide.presentation.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        binding.srlMainActivity.setOnRefreshListener(this)

        initRecyclerView()

        mainViewModel.randomUsers.observe(this, Observer {
            userAdapter.replaceAll(it)
        })

        mainViewModel.addRandomUsers.observe(this, Observer {
            userAdapter.addItems(it)
        })

        mainViewModel.loadRandomUsers()
    }

    private fun initRecyclerView() {
        with(binding.rvRandomUser) {
            adapter = userAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (dy > 0) {
                        val lm = recyclerView.layoutManager
                        if (lm is LinearLayoutManager) {
                            if (lm.findLastCompletelyVisibleItemPosition() ==
                                recyclerView.adapter?.itemCount?.minus(1)
                            ) {
                                mainViewModel.addRandomUsers()
                            }
                        }
                    }

                }
            })
        }
    }

    override fun onRefresh() {
        mainViewModel.loadRandomUsers(true)
    }

    private val backPressCloseHelper by lazy { BackPressCloseHelper(this) }

    override fun onBackPressed() {
        backPressCloseHelper.onBackPressed()
    }
}