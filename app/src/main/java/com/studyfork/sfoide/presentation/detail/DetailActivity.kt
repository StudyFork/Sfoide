package com.studyfork.sfoide.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.studyfork.sfoide.R
import com.studyfork.sfoide.base.BaseActivity
import com.studyfork.sfoide.databinding.ActivityDetailBinding
import com.studyfork.sfoide.presentation.model.UserItem
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    private val detailViewModel by viewModel<DetailViewModel> {
        parametersOf(getUserItem())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.model = detailViewModel

    }

    private fun getUserItem(): UserItem {
        val userItem = intent?.getParcelableExtra<UserItem>(EXTRA_USER_ITEM)

        if (userItem == null) {
            throw NullPointerException("user item is null")
        } else {
            return userItem
        }
    }

    companion object {

        private const val EXTRA_USER_ITEM = "user_item"

        fun startActivityWithUserItem(context: Context, userItem: UserItem) {
            context.startActivity(
                Intent(context, DetailActivity::class.java).apply {
                    putExtra(EXTRA_USER_ITEM, userItem)
                }
            )
        }
    }
}