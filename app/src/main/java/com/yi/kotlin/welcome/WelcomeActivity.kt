package com.yi.kotlin.welcome

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.yi.kotlin.R
import com.yi.kotlin.base.BaseActivity
import com.yi.kotlin.base.Router
import com.yi.kotlin.main.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_welcome.*
import java.util.concurrent.TimeUnit

/**
 * @author Yi
 * @date 2020/4/9
 */
@Route(path = "${Router.GROUP}WelcomeActivity")
class WelcomeActivity : BaseActivity() {

    companion object {
        val TAG = WelcomeActivity::class.java.simpleName
    }

    override fun getLayout(): Int = R.layout.activity_welcome

    override fun onCreate() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                var params = hashMapOf<String, Any>()
                params["value"] = "welcome to kotlin"
                params["digit"] = 1000
                Router.route(MainActivity::class.java, params)
                finish()
            }
    }
}