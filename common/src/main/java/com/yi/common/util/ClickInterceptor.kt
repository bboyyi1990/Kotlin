package com.yi.common.util

import android.os.SystemClock
import android.view.View

object ClickInterceptor {
    private var time = 0L

    fun checkTime(): Boolean {
        val safe = SystemClock.elapsedRealtime() - time > 500
        if (safe) time = SystemClock.elapsedRealtime()
        return safe
    }
}

fun View.setOnClicker(clicker: View.OnClickListener) {
    setOnClickListener {
        if (ClickInterceptor.checkTime()) return@setOnClickListener
        clicker.onClick(it)
    }
}
