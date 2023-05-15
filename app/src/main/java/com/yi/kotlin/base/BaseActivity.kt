package com.yi.kotlin.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.OnKeyboardListener
import com.gyf.immersionbar.ktx.immersionBar
import com.yi.common.base.CommonBaseActivity

/**
 * @author Yi
 * @date 2020/4/2
 */
open abstract class BaseActivity : CommonBaseActivity() {
    companion object {
        val TAG = BaseActivity::class.java.simpleName;
    }

    /**
     * lazy view binding for convenient use
     * you must override the property
     */
    open val binding: ViewBinding by lazy { TODO("must implement view binding") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        initBar()
//        setContentView(getLayout())
        setContentView(binding.root)
        onCreate()
    }

    /**
     * 系统栏设置
     * fits - true 布局内容留出系统栏位置 / false 布局内容拉伸到系统栏底部，会被系统栏覆盖
     * listener = 软键盘操作回调，监听键盘状态以及高度
     */
    open fun initBar(fits: Boolean = true) = this.initBar(fits, null)
    protected fun initBar(fits: Boolean = true, keyboardListener: OnKeyboardListener? = null) =
        immersionBar {
            statusBarDarkFont(true, 0.2f)
            fitsSystemWindows(fits)
            keyboardEnable(null != keyboardListener)
            setOnKeyboardListener(keyboardListener)
        }

    abstract fun onCreate()
}
