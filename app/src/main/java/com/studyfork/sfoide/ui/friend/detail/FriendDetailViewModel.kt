package com.studyfork.sfoide.ui.friend.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.studyfork.sfoide.ui.utils.Event
import timber.log.Timber

class FriendDetailViewModel : ViewModel() {

    private val _navigatePhoneAppEvent = MutableLiveData<Event<String>>()
    val navigatePhoneAppEvent: LiveData<Event<String>> = _navigatePhoneAppEvent

    private val _navigateEmailAppEvent = MutableLiveData<Event<String>>()
    val navigateEmailAppEvent: LiveData<Event<String>> = _navigateEmailAppEvent

    fun navigateEmail(email: String) {
        _navigateEmailAppEvent.value = Event(email)
    }

    fun navigatePhone(phoneNumber: String) {
        val formattedPhoneNumber = phoneNumber.filter { it in NUMBER_FILTER }
        Timber.e(formattedPhoneNumber)
        _navigatePhoneAppEvent.value = Event(formattedPhoneNumber)
    }

    companion object {
        private const val NUMBER_FILTER = "0123456789"
    }
}