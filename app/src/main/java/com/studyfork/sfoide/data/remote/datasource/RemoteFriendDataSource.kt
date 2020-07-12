package com.studyfork.sfoide.data.remote.datasource

import com.studyfork.sfoide.ui.model.Friend

interface RemoteFriendDataSource {

    fun getFriends(
        pageNumber: Int,
        itemCount: Int,
        onSuccess: (friends: List<Friend>) -> Unit,
        onError: (error: Throwable) -> Unit
    )
}