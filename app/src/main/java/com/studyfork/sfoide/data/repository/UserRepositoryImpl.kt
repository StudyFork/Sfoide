package com.studyfork.sfoide.data.repository

import com.studyfork.sfoide.data.model.mapToPresentation
import com.studyfork.sfoide.data.source.remote.UserApi
import com.studyfork.sfoide.presentation.model.UserItem
import io.reactivex.Single

class UserRepositoryImpl(
    private val userApi: UserApi
) : UserRepository {

    override fun getRandomUsers(page: Int, results: Int): Single<List<UserItem>> {
        return userApi.getRandomUser(page, results)
            .map { it.results.mapToPresentation() }
    }
}