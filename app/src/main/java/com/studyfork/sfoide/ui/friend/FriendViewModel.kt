package com.studyfork.sfoide.ui.friend

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.data.remote.datasource.RemoteFriendDataSource
import com.studyfork.sfoide.ui.mapper.toPresentation
import com.studyfork.sfoide.ui.model.Friend
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

    private var pageNumber: Int = 1

    init {
        fetchFriends(pageNumber)
    }

    private fun fetchFriends(pageNumber: Int, itemCount: Int = ITEM_COUNT) {
        remoteFriendDataSource.getFriends(
            pageNumber = pageNumber,
            itemCount = itemCount,
            onSuccess = { friends ->
                _loading.value = false
                _friendList.value =
                    (_friendList.value ?: emptyList()) + friends.map { it.toPresentation() }
            },
            onError = {
                _loading.value = false
            }
        )
    }

    fun loadMoreFriends() {
        fetchFriends(++pageNumber)
    }

    fun onRefresh() {
        _friendList.value = emptyList()
        _loading.value = true
        pageNumber = 1
        fetchFriends(pageNumber)
    }

    fun navigateFriendDetail(friend: Friend) {
        _navigateDetailEvent.value = Event(friend)
    }

    companion object {
        private const val ITEM_COUNT = 20
    }
}