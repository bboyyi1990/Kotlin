package com.yi.kotlin.base

import java.util.*

/**
 * @author Yi
 * @date 2020/4/8
 */
object ActivityManager {
    private val stacks: Stack<BaseActivity> by lazy { Stack<BaseActivity>() }

    val TAG = ActivityManager::class.java.simpleName

    fun addActivity(activity: BaseActivity) {
        stacks.add(activity)
    }

    fun removeActivity(activity: BaseActivity) {
        stacks.remove(activity)
    }

    fun clearAllActivity() {
        for (activity in stacks) {
            activity.finish()
        }
        stacks.clear()
        
    }

    fun getTopActivity(): BaseActivity? = stacks?.lastOrNull()
}