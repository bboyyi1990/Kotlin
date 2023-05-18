package com.yi.common.util

import android.util.Log
import com.yi.common.BuildConfig

/**
 * @author Yi
 * @date 2020/4/10
 */
object Logger {
    private fun canLog(): Boolean = BuildConfig.DEBUG

    fun e(TAG: String, message: String) {
        if (canLog()) {
            Log.e(TAG, message)
        }
    }

    fun d(TAG: String, message: String) {
        if (canLog()) {
            Log.d(TAG, message)
        }
    }

    fun String.loggerE(tag: String) {
        e(tag, this)
    }
}