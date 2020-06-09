package com.yi.kotlin.uitl

import android.widget.Toast
import com.yi.kotlin.base.BaseApplication

/**
 * @author Yi
 * @date 2020/5/15
 */
object ToastUtil {
    private val toast_default =
        Toast.makeText(BaseApplication.getInstance(), "", Toast.LENGTH_SHORT)

    fun showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        toast_default.setText(msg)
        toast_default.duration = duration
        toast_default.show()
    }
}