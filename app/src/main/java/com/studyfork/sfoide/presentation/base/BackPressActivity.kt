package com.studyfork.sfoide.presentation.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.studyfork.sfoide.R
import com.studyfork.sfoide.presentation.home.HomeFragment

abstract class BackPressActivity() : AppCompatActivity() {

    companion object {
        const val FINISH_INTERVAL_TIME = 1500L
    }

    private var backPressedTime = 0L

    private fun finishOnDoubleBackPress() {
        val tempTime = System.currentTimeMillis()
        val intervalTime = tempTime - backPressedTime

        if (intervalTime in 0..FINISH_INTERVAL_TIME) {
            super.onBackPressed()
        } else {
            backPressedTime = tempTime
            Toast.makeText(this, R.string.back_press_guide_message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun getVisibleFragmentName(): String? {
        val fragmentManager = supportFragmentManager
        val fragments = fragmentManager.fragments
        for (fragment in fragments.reversed()) {
            if (fragment != null && fragment.isVisible) {
                return fragment::class.java.name
            }
        }
        return null
    }

    override fun onBackPressed() {
        val currentFragment = getVisibleFragmentName()

        if (currentFragment == HomeFragment::class.java.name) {
            finishOnDoubleBackPress()
        } else {
            super.onBackPressed()
        }
    }
}