package com.yi.common.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yi.common.util.LanguageUtils
import com.yi.common.util.Logger


/**
 * create by Yi on 2021/4/4
 *
 */
open abstract class CommonBaseActivity : AppCompatActivity() {
    companion object {
        val TAG = CommonBaseActivity::class.java.simpleName;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.e(TAG, this::class.java.simpleName)
        ActivityManager.addActivity(this)
    }

    override fun attachBaseContext(newBase: Context) {
        //7.0以上多语言适配
        super.attachBaseContext(LanguageUtils.attachBaseContext(newBase))
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.removeActivity(this)
    }
}
