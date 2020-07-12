package com.studyfork.sfoide.data.remote.datasource

import com.studyfork.sfoide.data.model.FriendData

interface RemoteFriendDataSource {

    fun getFriends(
        pageNumber: Int,
        itemCount: Int,
        onSuccess: (friends: List<FriendData>) -> Unit,
        onError: (error: Throwable) -> Unit
    )
}