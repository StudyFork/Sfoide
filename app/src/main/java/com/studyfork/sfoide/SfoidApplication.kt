package com.studyfork.sfoide

import android.app.Application
import com.studyfork.sfoide.di.appModule
import com.studyfork.sfoide.di.networkModule
import com.studyfork.sfoide.di.repositoryModule
import com.studyfork.sfoide.di.viewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger

class SfoidApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            logger(
                if (BuildConfig.DEBUG) {
                    AndroidLogger()
                } else {
                    EmptyLogger()
                }
            )

            androidContext(this@SfoidApplication)

            modules(
                listOf(
                    appModule,
                    networkModule,
                    repositoryModule,
                    viewModel
                )
            )
        }
    }
}