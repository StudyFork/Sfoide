package com.studyfork.sfoide.di

import com.studyfork.sfoide.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel { MainViewModel(get()) }
}