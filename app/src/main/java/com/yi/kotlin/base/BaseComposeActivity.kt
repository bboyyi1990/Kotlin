package com.yi.kotlin.base

import android.os.Bundle
import com.gyf.immersionbar.OnKeyboardListener
import com.gyf.immersionbar.ktx.immersionBar
import com.yi.common.base.CommonBaseActivity

/**
 * create by yi on 2023/3/21
 */
abstract class BaseComposeActivity : CommonBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreate()
    }

    abstract fun onCreate()

    /**
     * 系统栏设置
     * fits - true 布局内容留出系统栏位置 / false 布局内容拉伸到系统栏底部，会被系统栏覆盖
     * listener = 软键盘操作回调，监听键盘状态以及高度
     */
    open fun dinitBar(fits: Boolean = true) = this.initBar(fits, null)
    protected fun initBar(fits: Boolean = true, keyboardListener: OnKeyboardListener? = null) =
        immersionBar {
            statusBarDarkFont(true, 0.2f)
            fitsSystemWindows(fits)
            keyboardEnable(null != keyboardListener)
            setOnKeyboardListener(keyboardListener)
        }
}