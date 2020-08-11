package com.studyfork.sfoide.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.data.model.RandomUser
import com.studyfork.sfoide.presentation.base.BaseViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

class DetailViewModel : BaseViewModel() {
    private val _user = MutableLiveData<RandomUser>()
    val user: LiveData<RandomUser> = _user

    fun setUser(user: RandomUser) {
        _user.value = user
    }

    companion object {
        val TAG: String = DetailViewModel::class.java.simpleName
    }
}