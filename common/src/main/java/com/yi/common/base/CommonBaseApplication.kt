package com.yi.common.base

import android.app.Application
import com.tencent.mmkv.MMKV

/**
 * create by Yi on 2021/4/3
 * 底层application ，用于向底层提供context
 * 上次需要继承此类
 */
abstract class CommonBaseApplication : Application() {
    companion object {
        val TAG = CommonBaseApplication::class.java.simpleName

        private lateinit var application: CommonBaseApplication

        fun getInstance(): CommonBaseApplication {
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
//        if (BuildConfig.DEBUG) {
//            return
//        }
        //运行异常捕获的使用
//        CrashHandler.install(object : CrashHandler.CrashCallback {
//            override fun handlerException(thread: Thread, throwable: Throwable) {
//                if (throwable != null) {
//                    Logger.e(TAG, "Exception ===${throwable.message}")
//                }
//            }
//        })
    }
}