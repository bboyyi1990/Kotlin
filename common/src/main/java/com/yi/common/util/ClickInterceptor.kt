package com.yi.common.util

import android.os.SystemClock
import android.view.View

/**
 * global scope fast click interceptor
 */
object ClickInterceptor {
    private var time = 0L

    fun needIntercept(): Boolean {
        val safe = SystemClock.elapsedRealtime() - time > 500
        Logger.e(this::class.java.simpleName, "is safe = $safe")
        if (safe) time = SystemClock.elapsedRealtime()
        return !safe
    }
}

/**
 * Ex method to intercept fast click
 */
fun View.setOnClicker(clicker: View.OnClickListener) {
    setOnClickListener {
        if (ClickInterceptor.needIntercept()) return@setOnClickListener
        Logger.e(ClickInterceptor::class.java.simpleName, "click accepted")
        clicker.onClick(it)
    }
}
