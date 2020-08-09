package com.studyfork.sfoide.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment() {

    protected lateinit var binding: B
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()
    }

    private fun initBinding() {
        binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(requireContext()),
            layoutResId,
            binding.root.parent as ViewGroup,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }
}