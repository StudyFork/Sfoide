package com.studyfork.sfoide.helper

import android.app.Activity
import android.widget.Toast
import com.studyfork.sfoide.R

class BackPressCloseHelper(private val activity: Activity) {

    private var backPressedTime = 0L
    private val timeRate = 2000
    private var toast: Toast? = null

    fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime > backPressedTime + timeRate) {
            backPressedTime = currentTime
            showToast()
            return
        }
        if (currentTime <= backPressedTime + timeRate) {
            activity.finish()
            toast?.cancel()
        }
    }

    private fun showToast() {
        toast = Toast.makeText(
            activity,
            activity.getString(R.string.back_pressed_alert),
            Toast.LENGTH_LONG
        )
        toast?.show()
    }
}