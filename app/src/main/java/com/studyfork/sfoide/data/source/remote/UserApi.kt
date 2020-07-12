package com.studyfork.sfoide.data.source.remote

import com.studyfork.sfoide.data.model.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

    @GET("api/")
    fun getRandomUser(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): Single<UserResponse>
}