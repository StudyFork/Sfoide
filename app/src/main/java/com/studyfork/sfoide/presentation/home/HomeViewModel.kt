package com.studyfork.sfoide.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.studyfork.sfoide.data.mapper.RandomUserMapper
import com.studyfork.sfoide.data.model.RandomUser
import com.studyfork.sfoide.data.remote.RandomUserApi
import com.studyfork.sfoide.presentation.base.BaseViewModel
import com.studyfork.sfoide.presentation.utils.Event
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : BaseViewModel() {
    private val api = RandomUserApi.create()

    private val _userList = MutableLiveData<List<RandomUser>>()
    val userList: LiveData<List<RandomUser>> = _userList

    private val _moveToDetailScreenEvent = MutableLiveData<Event<RandomUser>>()
    val moveToDetailScreenEvent: LiveData<Event<RandomUser>> = _moveToDetailScreenEvent

    private val _loadingEvent = MutableLiveData<Event<Unit>>()
    val loadingEvent: LiveData<Event<Unit>> = _loadingEvent

    private val _loadFinishEvent = MutableLiveData<Event<Unit>>()
    val loadFinishEvent: LiveData<Event<Unit>> = _loadFinishEvent

    private var page = INITIAL_PAGE
    var isLoadingMore = false

    private fun getRandomUsers(page: Int = 1): Single<List<RandomUser>> {
        return api.getRandomUsers(page = page)
            .map {
                it.results.map { result -> RandomUserMapper.fromResult(result) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _loadingEvent.value = Event(Unit) }
            .doOnSuccess { _loadFinishEvent.value = Event(Unit) }
    }

    fun initialLoad() {
        getRandomUsers()
            .subscribeBy(onSuccess = {
                _userList.value = it
                Log.d(TAG, "getRandomUsers success : $it")
            }, onError = {
                Log.e(TAG, "getRandomUsers failed", it)
            })
            .addTo(disposeBag)
    }

    fun onItemClick(randomUser: RandomUser) {
        _moveToDetailScreenEvent.value = Event(randomUser)
    }

    fun refresh() {
        getRandomUsers()
            .subscribeBy(onSuccess = {
                _userList.value = it
                page = INITIAL_PAGE
                Log.d(TAG, "refresh success : $it")
            }, onError = {
                Log.e(TAG, "refresh failed", it)
            })
            .addTo(disposeBag)
    }

    fun loadMore() {
        getRandomUsers(++page)
            .doOnSubscribe { isLoadingMore = true }
            .doOnSuccess { isLoadingMore = false }
            .subscribeBy(onSuccess = {
                _userList.value?.let { current ->
                    ArrayList(current).apply {
                        addAll(it)
                    }.also { new ->
                        _userList.value = new
                    }
                }
                Log.d(TAG, "loadMore success : ${_userList.value?.size} $it")
            }, onError = {
                Log.e(TAG, "loadMore failed", it)
            })
            .addTo(disposeBag)
    }

    companion object {
        val TAG: String = HomeViewModel::class.java.simpleName
        private const val INITIAL_PAGE = 1
        const val PAGING_OFFSET = 8
    }
}