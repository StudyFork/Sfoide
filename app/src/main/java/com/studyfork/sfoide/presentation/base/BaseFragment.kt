package com.studyfork.sfoide.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    Fragment() {

    protected lateinit var binding: B
        private set


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBinding()
        return binding.root
    }

    private fun initBinding() {
        binding = DataBindingUtil.inflate<B>(
            LayoutInflater.from(requireContext()),
            layoutResId,
            null,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
        }
    }
}