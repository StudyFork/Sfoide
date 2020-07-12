package com.studyfork.sfoide.di

import com.studyfork.sfoide.BuildConfig
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named("debug")) { BuildConfig.DEBUG }
}