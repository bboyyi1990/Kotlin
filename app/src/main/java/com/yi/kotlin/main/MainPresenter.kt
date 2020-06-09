package com.yi.kotlin.main

import com.yi.kotlin.data.MainData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author Yi
 * @date 2020/4/7
 */
class MainPresenter(private val callback: Callback) {
    private val count = 20
    fun getData(start: Int) {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                var list = arrayListOf<MainData>()
                for (i in start..start + count) {
                    list.add(MainData("title is $i"))
                }
                callback.onMainData(start == 0, list)
            }
    }

    interface Callback {
        fun onMainData(reset: Boolean, list: ArrayList<MainData>?)
    }
}

