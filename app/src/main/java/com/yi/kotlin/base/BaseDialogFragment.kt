package com.yi.kotlin.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDialogFragment
import com.gyf.immersionbar.ktx.immersionBar


/**
 * create by yi on 7/13/21
 */
abstract class BaseDialogFragment : AppCompatDialogFragment() {

    override fun onStart() {
        super.onStart()
        initWindow()
    }

    /**
     * 设置xml 布局内容完全充满展示
     */
    private fun initWindow() {
        immersionBar {
//            statusBarDarkFont(true, 1f)
//            statusBarColor(R.color.colorAccent)
            fitsSystemWindows(false)
        }
        val window = dialog?.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.MATCH_PARENT;
        params?.height = WindowManager.LayoutParams.MATCH_PARENT;
        window?.attributes = params
    }
}