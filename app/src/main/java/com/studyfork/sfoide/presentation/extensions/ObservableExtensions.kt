package com.studyfork.sfoide.presentation.extensions

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.throttleFirstWithHalfSecond(): Observable<T> {
    return throttleFirst(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
        .observeOn(AndroidSchedulers.mainThread())
}