package com.yi.common.util

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import com.yi.common.base.CommonBaseApplication

/**
 * @author Yi
 * @date 2020/5/15
 */
object UIUtil {

    /**
     * dip 转 px
     *
     * @param dp
     * @return
     */
    fun dip2Pixel(dp: Int): Int {
        val res: Resources = CommonBaseApplication.getInstance().resources
        return dip2Pixel(dp, res)
    }

    /**
     * px转dip
     *
     * @param pixel
     * @return
     */
    fun pixel2Dip(pixel: Int): Int {
        val pre: Int = dip2Pixel(1)
        return pixel / pre
    }

    private fun dip2Pixel(dp: Int, res: Resources): Int {
        val scale = res.displayMetrics.density
        return (dp * scale + if (dp >= 0) 0.5f else -0.5f).toInt()
    }

    private var screenSize: Point? = null

    /**
     * 获取屏幕尺寸
     *
     * @return
     */
    fun getScreenWidth(): Int {
        initScreenSize()
        return screenSize?.x ?: 0
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    fun getScreenHeight(): Int {
        initScreenSize()
        return screenSize?.y ?: 0
    }

    private fun initScreenSize() {
        if (screenSize == null) {
            screenSize = Point()
            val wm = CommonBaseApplication.getInstance()
                .getSystemService(Application.WINDOW_SERVICE) as WindowManager
            wm.defaultDisplay.getSize(screenSize)
        }
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(): Int {
        val resources: Resources = CommonBaseApplication.getInstance().resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    /**
     * 潜航栏高度
     *
     * @param context
     * @return
     */
    fun getNavigationBarHeight(context: Context): Int {
        val resourceId =
            context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    /**
     * popupWindow 展示位置
     *
     * @param pw
     * @param anchor - 锚点View
     * @param xOff   - 锚点偏移量
     * @param yOff   - 锚点偏移量
     */
    fun showAsDropDown(
        pw: PopupWindow,
        anchor: View,
        xOff: Int,
        yOff: Int
    ) {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                val visibleFrame = Rect()
                anchor.getGlobalVisibleRect(visibleFrame)
                val height =
                    anchor.resources.displayMetrics.heightPixels - visibleFrame.bottom
                pw.height = height
                pw.showAsDropDown(anchor, xOff, yOff)
            } else {
                pw.showAsDropDown(anchor, xOff, yOff)
            }
        } catch (e: Exception) {
        }
    }
}