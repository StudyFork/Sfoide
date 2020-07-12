package com.studyfork.sfoide.data.remote.api

import com.studyfork.sfoide.data.remote.response.FriendsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendApi {

    @GET("/")
    fun getFriends(
        @Query("page") page: Int,
        @Query("results") results: Int
    ): Call<FriendsResponse>
}