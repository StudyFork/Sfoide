package com.studyfork.sfoide.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.studyfork.sfoide.R
import com.studyfork.sfoide.ui.model.Friend
import com.studyfork.sfoide.databinding.ViewFriendInfoBinding

class FriendInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private lateinit var binding: ViewFriendInfoBinding

    private val layoutId = R.layout.view_friend_info

    init {
        initBinding()
    }

    var friend: Friend? = null
        set(value) {
            field = value
            value?.let {
                fetchFriend(value)
            }
        }

    private fun fetchFriend(value: Friend) {
        binding.friend = value
    }

    private fun initBinding() {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            layoutId,
            this,
            false
        )
        addView(binding.root)
    }

    fun setOnPhoneClickListener(onPhoneClickListener: OnPhoneClickListener) {
        binding.tvFriendMobile.setOnClickListener {
            friend?.mobilePhone?.let {
                onPhoneClickListener.onPhoneClick(it)
            }
        }

        binding.tvFriendTelephone.setOnClickListener {
            friend?.telephone?.let {
                onPhoneClickListener.onPhoneClick(it)
            }
        }
    }

    fun setOnEmailClickListener(onEmailClickListener: OnEmailClickListener) {
        binding.tvFriendEmail.setOnClickListener {
            friend?.email?.let {
                onEmailClickListener.onEmailClick(it)
            }
        }
    }

    interface OnPhoneClickListener {
        fun onPhoneClick(phoneNumber: String)
    }

    interface OnEmailClickListener {
        fun onEmailClick(email: String)
    }
}