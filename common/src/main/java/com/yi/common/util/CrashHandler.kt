package com.yi.common.util

import android.os.Handler
import android.os.Looper

/**
 * create by Yi on 2021/3/18
 *
 * 防崩溃异常处理：捕获主线程中异常，抛出避免crash
 *
 */
object CrashHandler {

    /**
     * 避免重复注册标记
     */
    private var installed = false

    private var handler: CrashCallback? = null

    /**
     * 注册方法
     */
    fun install(handler: CrashCallback) {
        if (installed) {
            return
        }
        this.installed = true
        this.handler = handler
        Handler(Looper.getMainLooper()).post {
            while (true) {
                try {
                    Looper.loop()
                } catch (throwable: Throwable) {
                    this.handler?.handlerException(Looper.getMainLooper().thread, throwable)
                }
            }
        }
        //未捕获异常处理
        Thread.setDefaultUncaughtExceptionHandler { t, e -> handler?.handlerException(t, e) }
    }

    /**
     * 反注册
     */
    fun uninstall() {
        if (!installed) {
            return
        }
        installed = false
        this.handler = null
    }

    interface CrashCallback{
        fun handlerException(thread: Thread, throwable: Throwable)
    }
}