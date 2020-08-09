package com.studyfork.sfoide.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.studyfork.sfoide.R
import com.studyfork.sfoide.databinding.FragmentHomeBinding
import com.studyfork.sfoide.presentation.base.BaseFragment
import com.studyfork.sfoide.presentation.detail.DetailFragment
import com.studyfork.sfoide.presentation.extensions.replaceFragment
import com.studyfork.sfoide.presentation.extensions.throttleFirstWithHalfSecond
import io.reactivex.rxjava3.kotlin.addTo

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initHomeViewModel()

        viewModel.getRandomUsers()

        binding.rvRandomUsers.adapter = RandomUsersAdapter().apply {
            itemClicks.throttleFirstWithHalfSecond()
                .subscribe { randomUser -> viewModel.onItemClick(randomUser) }
                .addTo(disposeBag)
        }
    }

    private fun initHomeViewModel() {
        binding.viewModel = viewModel

        with(viewModel) {
            moveToDetailScreenEvent.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let { user ->
                    parentFragmentManager.replaceFragment<DetailFragment>(
                        R.id.container,
                        "user" to user
                    )
                }
            }
        }
    }
}