package com.yi.common.base

import android.app.Activity
import java.util.*

/**
 * @author Yi
 * @date 2020/4/8
 * activity栈管理
 */
object ActivityManager {
    private val stacks: Stack<Activity> by lazy { Stack<Activity>() }

    val TAG = ActivityManager::class.java.simpleName

    fun addActivity(activity: Activity) {
        stacks.add(activity)
    }

    fun removeActivity(activity: Activity) {
        stacks.remove(activity)
    }

    fun clearAllActivity() {
        stacks.forEach { it.finish() }
        stacks.clear()
    }

    fun getTopActivity(): Activity? = stacks?.lastOrNull()
}