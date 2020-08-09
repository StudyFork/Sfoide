package com.studyfork.sfoide.presentation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.data.remote.RandomUserApi
import com.studyfork.sfoide.data.remote.response.RandomUserResponse
import com.studyfork.sfoide.presentation.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {
    private val disposeBag = CompositeDisposable()
    private val api = RandomUserApi.create()

    private val _userList = MutableLiveData<List<RandomUserResponse>>()
    val userList: LiveData<List<RandomUserResponse>> = _userList

    fun getRandomUsers() {
        api.getRandomUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onSuccess = {
                Log.d(MainActivity::class.java.simpleName, "getRandomUsers success : $it")
            }, onError = {
                Log.e(MainActivity::class.java.simpleName, "getRandomUsers failed", it)
            })
            .addTo(disposeBag)
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }
}