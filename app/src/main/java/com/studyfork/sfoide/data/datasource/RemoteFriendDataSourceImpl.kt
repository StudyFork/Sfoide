package com.studyfork.sfoide.data.datasource

import com.studyfork.sfoide.data.mapper.toEntity
import com.studyfork.sfoide.data.model.Friend
import com.studyfork.sfoide.data.remote.api.FriendApi
import com.studyfork.sfoide.data.remote.datasource.RemoteFriendDataSource
import com.studyfork.sfoide.data.remote.response.FriendsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteFriendDataSourceImpl(
    private val friendApi: FriendApi
) : RemoteFriendDataSource {

    override fun getFriends(
        pageNumber: Int,
        itemCount: Int,
        onSuccess: (friends: List<Friend>) -> Unit,
        onError: (error: Throwable) -> Unit
    ) {
        friendApi.getFriends(
            page = pageNumber,
            results = itemCount
        )
            .enqueue(object : Callback<FriendsResponse?> {
                override fun onFailure(call: Call<FriendsResponse?>, t: Throwable) {
                    onError(t)
                }

                override fun onResponse(
                    call: Call<FriendsResponse?>,
                    response: Response<FriendsResponse?>
                ) {
                    if (response.isSuccessful) {
                        val friends =
                            response.body()?.results?.map { FriendResponse -> FriendResponse.toEntity() }
                                ?: emptyList()
                        onSuccess(friends)
                    } else {
                        onError(Throwable("network error"))
                    }
                }
            })
    }
}