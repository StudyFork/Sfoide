package com.studyfork.sfoide.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studyfork.sfoide.R
import com.studyfork.sfoide.databinding.FragmentHomeBinding
import com.studyfork.sfoide.presentation.base.BaseFragment
import com.studyfork.sfoide.presentation.detail.DetailFragment
import com.studyfork.sfoide.presentation.extensions.addFragment
import com.studyfork.sfoide.presentation.extensions.throttleFirstWithHalfSecond
import io.reactivex.rxjava3.kotlin.addTo

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initHomeViewModel()
        viewModel.initialLoad()
        initRecyclerView()
        listenSwipeRefresh()
    }

    private fun initRecyclerView() {
        binding.rvRandomUsers.adapter = RandomUsersAdapter().apply {
            itemClicks.throttleFirstWithHalfSecond()
                .subscribe { randomUser -> viewModel.onItemClick(randomUser) }
                .addTo(disposeBag)
        }

        binding.rvRandomUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                val layoutManager = recyclerView.layoutManager as? LinearLayoutManager
                layoutManager ?: return

                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - HomeViewModel.PAGING_OFFSET &&
                    !viewModel.isLoadingMore
                ) {
                    viewModel.loadMore()
                }

                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun listenSwipeRefresh() {
        binding.srlUser.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initHomeViewModel() {
        binding.viewModel = viewModel

        with(viewModel) {
            moveToDetailScreenEvent.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let { user ->
                    parentFragmentManager.addFragment<DetailFragment>(
                        R.id.container,
                        true,
                        DetailFragment.ARG_USER to user
                    )
                }
            }

            loadingEvent.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let {
                    binding.srlUser.isRefreshing = true
                }
            }

            loadFinishEvent.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let {
                    binding.srlUser.isRefreshing = false
                }
            }
        }
    }
}