package com.studyfork.sfoide.di

import com.studyfork.sfoide.data.repository.UserRepository
import com.studyfork.sfoide.data.repository.UserRepositoryImpl
import com.studyfork.sfoide.data.source.remote.UserApi
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {

    // repository
    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    // remote
    single { get<Retrofit>().create(UserApi::class.java) }
}