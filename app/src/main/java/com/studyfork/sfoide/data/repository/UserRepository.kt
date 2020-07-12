package com.studyfork.sfoide.data.repository

import com.studyfork.sfoide.presentation.model.UserItem
import io.reactivex.Single

interface UserRepository {

    fun getRandomUsers(page: Int, results: Int = 10): Single<List<UserItem>>
}