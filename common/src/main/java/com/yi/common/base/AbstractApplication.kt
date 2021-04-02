package com.yi.common.base

import android.app.Application
import com.tencent.mmkv.MMKV
import com.yi.common.BuildConfig
import com.yi.common.util.CrashHandler
import com.yi.common.util.Logger

/**
 * create by Yi on 2021/4/3
 * 抽象application ，用于向底层提供context
 */
abstract class AbstractApplication : Application() {
    companion object {
        val TAG = AbstractApplication::class.java.simpleName

        private lateinit var application: AbstractApplication

        fun getInstance(): AbstractApplication {
            return application
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        MMKV.initialize(this)
        initCrashHandler()
    }

    private fun initCrashHandler() {
        if (BuildConfig.DEBUG) {
            return
        }
        //运行异常捕获的使用
        CrashHandler.install(object : CrashHandler.CrashCallback {
            override fun handlerException(thread: Thread, throwable: Throwable) {
                if (throwable != null) {
                    Logger.e(TAG, "Exception ===${throwable.message}")
                }
            }
        })
    }
}