package com.yi.kotlin.base

import android.app.Activity
import android.content.Context
import android.net.Uri
import com.alibaba.android.arouter.launcher.ARouter
import com.yi.common.util.Logger

/**
 * @author Yi
 * @date 2020/4/9
 */
object Router {
    private val TAG = Router::class.java.simpleName
    private const val SCHEME = "yi://"
    private const val HOST = "kotlin"
    const val GROUP = "/activity/"
    //example -  yi://kotlin/activity/MainActivity

    fun route(clazz: Class<out BaseActivity>) = this.route(clazz, null)

    fun route(clazz: Class<out BaseActivity>, params: Map<String, Any>?) {
        var uri = buildUri(clazz, params)
        Logger.e(TAG, "route uri = $uri")
        ARouter.getInstance().build(uri).navigation()
    }

    fun routerForResult(context: Activity, requestCode: Int, params: Map<String, Any>?) {
        var uri = buildUri(context.javaClass, params)
        ARouter.getInstance().build(uri).navigation(context, requestCode)
    }

    private fun buildUri(clazz: Class<out Activity>, params: Map<String, Any>?): Uri {
        var buffer = StringBuffer()
            .append(SCHEME).append(HOST)
            .append(GROUP).append(clazz.simpleName)
        if (null != params) {
            buffer.append("?")
            for ((key, value) in params) {
                if (!buffer.endsWith("?")) buffer.append("&")
                buffer.append(key).append("=").append(value)
            }
        }
        return Uri.parse(buffer.toString())
    }
}