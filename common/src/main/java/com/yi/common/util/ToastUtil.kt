package com.yi.common.util

import android.widget.Toast
import com.yi.common.base.AbstractApplication

/**
 * @author Yi
 * @date 2020/5/15
 */
object ToastUtil {
    private val toast_default by lazy {
        Toast.makeText(
            AbstractApplication.getInstance(),
            "",
            Toast.LENGTH_SHORT
        )
    }

    fun showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        toast_default.setText(msg)
        toast_default.duration = duration
        toast_default.show()
    }
}