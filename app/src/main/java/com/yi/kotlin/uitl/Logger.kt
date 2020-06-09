package com.yi.kotlin.uitl

import android.util.Log
import com.yi.kotlin.BuildConfig

/**
 * @author Yi
 * @date 2020/4/10
 */
object Logger {
    private fun canLog(): Boolean = BuildConfig.DEBUG

    fun e(TAG: String, message: String) = if (canLog()) {
        Log.e(TAG, message)
    } else {

    }

    fun d(TAG: String, message: String) {
        if (canLog()) {
            Log.d(TAG, message)
        }
    }
}