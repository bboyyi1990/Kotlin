package com.yi.kotlin.base

import android.app.Application
import android.graphics.Color
import com.alibaba.android.arouter.launcher.ARouter
import com.yi.kotlin.BuildConfig
import com.yi.kotlin.R
import com.yi.kotlin.uitl.ExceptionHandler
import com.yi.kotlin.uitl.Logger
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.mmkv.MMKV

/**
 * @author Yi
 * @date 2020/4/2
 */

class BaseApplication : Application() {
    companion object {
        val TAG = BaseApplication::class.java.simpleName

        private lateinit var application: BaseApplication

        fun getInstance(): BaseApplication {
            return application
        }
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->
            ClassicsHeader(context)
                .setAccentColorId(R.color.colorAccent)
                .setPrimaryColor(Color.WHITE)
                .setEnableLastTime(false)

        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
            ClassicsFooter(context)
                .setAccentColorId(R.color.colorAccent)
                .setPrimaryColor(Color.WHITE)
        }
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        ARouter.init(this);
        if(BuildConfig.DEBUG){
            ARouter.openLog()
            ARouter.openDebug()
        }
        MMKV.initialize(this)
        initCrashHandler()
    }

    private fun initCrashHandler() {
        if (!BuildConfig.DEBUG) {
            return
        }
        //运行异常捕获的使用
        ExceptionHandler.install(object : ExceptionHandler.CustomExceptionHandler {
            override fun handlerException(thread: Thread, throwable: Throwable) {
                if (throwable != null) {
                    Logger.e(TAG, "Exception ===${throwable.message}")
                }
            }
        })
    }
}