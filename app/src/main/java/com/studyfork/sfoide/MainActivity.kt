package com.studyfork.sfoide

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.studyfork.sfoide.data.remote.RandomUserApi
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private val disposeBag = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = RandomUserApi.create()

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
}