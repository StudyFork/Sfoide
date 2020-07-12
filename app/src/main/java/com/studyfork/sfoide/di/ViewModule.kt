package com.studyfork.sfoide.di

import com.studyfork.sfoide.presentation.detail.DetailViewModel
import com.studyfork.sfoide.presentation.main.MainViewModel
import com.studyfork.sfoide.presentation.model.UserItem
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {

    viewModel { MainViewModel(get()) }

    viewModel { (userItem: UserItem) -> DetailViewModel(userItem) }
}