package com.studyfork.sfoide.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studyfork.sfoide.base.BaseViewModel
import com.studyfork.sfoide.presentation.model.UserItem

class DetailViewModel(
    private val userItem: UserItem
) : BaseViewModel() {

    private val _userItemData = MutableLiveData<UserItem>()
    val userItemData: LiveData<UserItem> get() = _userItemData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        _userItemData.postValue(userItem)
    }
}