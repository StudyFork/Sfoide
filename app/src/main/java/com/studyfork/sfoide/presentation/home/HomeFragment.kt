package com.studyfork.sfoide.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.studyfork.sfoide.R
import com.studyfork.sfoide.databinding.FragmentHomeBinding
import com.studyfork.sfoide.presentation.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel
        viewModel.getRandomUsers()
        binding.rvRandomUsers.adapter = RandomUsersAdapter()
    }
}