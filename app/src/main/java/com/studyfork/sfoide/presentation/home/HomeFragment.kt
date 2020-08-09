package com.studyfork.sfoide.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.studyfork.sfoide.R
import com.studyfork.sfoide.presentation.base.BaseFragment

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getRandomUsers()
    }
}