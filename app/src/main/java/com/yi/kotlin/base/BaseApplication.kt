package com.yi.kotlin.base

import android.graphics.Color
import com.alibaba.android.arouter.launcher.ARouter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.yi.common.BuildConfig
import com.yi.common.base.CommonBaseApplication
import com.yi.kotlin.R

/**
 * @author Yi
 * @date 2020/4/2
 */

class BaseApplication : CommonBaseApplication() {
    companion object {
        val TAG = BaseApplication::class.java.simpleName

        private lateinit var application: CommonBaseApplication

        fun getInstance(): CommonBaseApplication {
            return application
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
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
}