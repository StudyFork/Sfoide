package com.studyfork.sfoide.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.data.model.RandomUser
import io.reactivex.rxjava3.disposables.CompositeDisposable

class DetailViewModel : ViewModel() {
    private val disposeBag = CompositeDisposable()

    private val _user = MutableLiveData<RandomUser>()
    val user: LiveData<RandomUser> = _user

    fun setUser(user: RandomUser) {
        _user.value = user
    }

    override fun onCleared() {
        super.onCleared()
        disposeBag.dispose()
    }

    companion object {
        val TAG: String = DetailViewModel::class.java.simpleName
    }
}