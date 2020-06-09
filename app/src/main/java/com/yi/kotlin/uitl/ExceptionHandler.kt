package com.yi.kotlin.uitl

import android.os.Handler
import android.os.Looper

/**
 * @author Yi
 * @date 2020/5/19
 */
object ExceptionHandler {
    interface CustomExceptionHandler {
        fun handlerException(thread: Thread, throwable: Throwable)
    }

    open class QuiteException(message: String) : RuntimeException(message)


    var handler: CustomExceptionHandler? = null
    var installed = false
    fun install(handler: CustomExceptionHandler) {
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
                    if (throwable is QuiteException) {
                        return@post
                    }
                    this.handler?.handlerException(Looper.getMainLooper().thread, throwable)
                }
            }
        }
        //将原先默认的处理方式保存下来，以便后期如果有需要的话恢复原状态。mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(object : Thread.UncaughtExceptionHandler {
            override fun uncaughtException(t: Thread, e: Throwable) {
                ExceptionHandler.handler?.handlerException(t, e)
            }
        })
    }

    fun uninstall() {
        if (!installed) {
            return
        }
        installed = false
        this.handler = null
        Handler(Looper.getMainLooper()).post { throw QuiteException("Quit ExceptionHandler....") }
    }
}