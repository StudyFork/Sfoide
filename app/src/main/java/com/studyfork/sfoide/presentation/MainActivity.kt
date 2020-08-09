package com.studyfork.sfoide.presentation

import android.os.Bundle
import com.studyfork.sfoide.R
import com.studyfork.sfoide.presentation.base.BaseActivity
import com.studyfork.sfoide.presentation.extensions.replaceFragment
import com.studyfork.sfoide.presentation.home.HomeFragment

class MainActivity : BaseActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.replaceFragment<HomeFragment>(R.id.container)
    }
}