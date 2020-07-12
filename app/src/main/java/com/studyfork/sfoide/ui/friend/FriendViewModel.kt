package com.studyfork.sfoide.ui.friend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.data.model.Friend
import com.studyfork.sfoide.data.remote.datasource.RemoteFriendDataSource
import com.studyfork.sfoide.ui.utils.Event

class FriendViewModel(
    private val remoteFriendDataSource: RemoteFriendDataSource
) : ViewModel() {

    private val _friendList = MutableLiveData<List<Friend>>()
    val friendList: LiveData<List<Friend>> = _friendList

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _navigateDetailEvent = MutableLiveData<Event<Friend>>()
    val navigateDetailEvent: LiveData<Event<Friend>> = _navigateDetailEvent

    init {
        fetchFriends()
    }

    private fun fetchFriends() {
        remoteFriendDataSource.getFriends(
            pageNumber = 1,
            itemCount = 20,
            onSuccess = { friends ->
                _friendList.value = friends
            },
            onError = {
                // todo
            }
        )
    }

    fun onRefresh() {
        // todo
    }

    fun navigateFriendDetail(friend: Friend) {
        _navigateDetailEvent.value = Event(friend)
    }
}