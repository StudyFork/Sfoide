package com.studyfork.sfoide.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.data.remote.RandomUserApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val disposeBag = CompositeDisposable()
    private val api = RandomUserApi.create()

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