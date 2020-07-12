package com.studyfork.sfoide.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studyfork.sfoide.base.BaseViewModel
import com.studyfork.sfoide.data.repository.UserRepository
import com.studyfork.sfoide.presentation.model.UserItem
import com.studyfork.sfoide.util.Dlog

class MainViewModel(
    private val userRepository: UserRepository
) : BaseViewModel() {

    private val _randomUsers = MutableLiveData<List<UserItem>>()
    val randomUsers: LiveData<List<UserItem>> get() = _randomUsers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isRefresh = MutableLiveData<Boolean>()
    val isRefresh: LiveData<Boolean> get() = _isRefresh

    fun loadRandomUsers(isRefresh: Boolean = false) {
        compositeDisposable.add(
            userRepository.getRandomUsers(1)
                .doOnSubscribe {
                    if (!isRefresh) {
                        showLoading()
                    }
                }
                .doOnTerminate {
                    hideLoadingAndRefresh()
                }
                .subscribe({
                    page = 1
                    _randomUsers.postValue(it)
                }) {
                    Dlog.e(it.message)
                }
        )
    }

    private var page = 1

    private fun showLoading() {
        _isLoading.value = true
    }

    private fun hideLoadingAndRefresh() {
        _isLoading.value = false
        _isRefresh.value = false
    }

}