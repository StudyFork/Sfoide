package com.studyfork.sfoide.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.studyfork.sfoide.R
import com.studyfork.sfoide.data.model.RandomUser
import com.studyfork.sfoide.databinding.FragmentDetailBinding
import com.studyfork.sfoide.presentation.base.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {
    private val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewModel = viewModel

        arguments?.getParcelable<RandomUser>(ARG_USER)?.let { user ->
            viewModel.setUser(user)
        }
    }

    companion object {
        const val ARG_USER = "arg_user"
    }
}