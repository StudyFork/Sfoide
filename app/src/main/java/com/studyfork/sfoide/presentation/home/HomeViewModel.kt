package com.studyfork.sfoide.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.data.mapper.RandomUserMapper
import com.studyfork.sfoide.data.model.RandomUser
import com.studyfork.sfoide.data.remote.RandomUserApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val disposeBag = CompositeDisposable()
    private val api = RandomUserApi.create()

    private val _userList = MutableLiveData<List<RandomUser>>()
    val userList: LiveData<List<RandomUser>> = _userList

    fun getRandomUsers() {
        api.getRandomUsers()
            .map {
                it.results.map { result -> RandomUserMapper.fromResult(result) }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                _userList.value = it
                Log.d(TAG, "getRandomUsers success : $it")
            }, onError = {
                Log.e(TAG, "getRandomUsers failed", it)
            })
            .addTo(disposeBag)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    companion object {
        val TAG: String = HomeViewModel::class.java.simpleName
    }
}