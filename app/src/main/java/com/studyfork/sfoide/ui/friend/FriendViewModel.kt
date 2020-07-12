package com.studyfork.sfoide.ui.friend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.data.model.Friend

class FriendViewModel : ViewModel() {

    private val _friendList = MutableLiveData<List<Friend>>()
    val friendList: LiveData<List<Friend>> = _friendList

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    init {
        fetchFriends()
    }

    private fun fetchFriends() {
        // todo
    }

    fun onRefresh() {
        // todo
    }

    fun navigateFriendDetail(friend: Friend) {
        // todo
    }
}