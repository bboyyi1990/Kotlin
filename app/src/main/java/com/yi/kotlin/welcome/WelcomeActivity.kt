package com.yi.kotlin.welcome

import com.alibaba.android.arouter.facade.annotation.Route
import com.yi.common.util.setOnClicker
import com.yi.kotlin.base.BaseActivity
import com.yi.kotlin.base.Router
import com.yi.kotlin.databinding.ActivityWelcomeBinding
import com.yi.kotlin.main.MainActivity
import com.yi.kotlin.compose.MainComposeActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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

    override val binding by lazy { ActivityWelcomeBinding.inflate(layoutInflater) }

    override fun onCreate() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                var params = hashMapOf<String, Any>()
                params["value"] = "welcome to kotlin"
                params["digit"] = 1000
//                Router.route(MainActivity::class.java, params)
//                Router.route(MainComposeActivity::class.java, params)
//                finish()
            }
        binding.composeBtn.setOnClicker { Router.route(MainComposeActivity::class.java) }
        binding.mainBtn.setOnClicker { Router.route(MainActivity::class.java) }
    }
}