package com.yi.kotlin.welcome

import androidx.viewbinding.ViewBinding
import com.alibaba.android.arouter.facade.annotation.Route
import com.yi.kotlin.base.BaseActivity
import com.yi.kotlin.base.Router
import com.yi.kotlin.databinding.ActivityWelcomeBinding
import com.yi.kotlin.main.MainComposeActivity
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

    override val binding: ViewBinding by lazy { ActivityWelcomeBinding.inflate(layoutInflater) }

    override fun onCreate() {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                var params = hashMapOf<String, Any>()
                params["value"] = "welcome to kotlin"
                params["digit"] = 1000
//                Router.route(MainActivity::class.java, params)
                Router.route(MainComposeActivity::class.java, params)
                finish()
            }
    }
}