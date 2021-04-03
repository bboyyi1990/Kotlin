package com.yi.kotlin.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yi.kotlin.base.BaseViewModel
import com.yi.kotlin.data.LoginData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @author Yi
 * @date 2020/4/7
 */
class MainViewModel : BaseViewModel() {
    private val count = 20
    var mainData = LoginData("0")
    fun getData(start: Int) {
        Observable.timer(1500, TimeUnit.MILLISECONDS)
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                var list = arrayListOf<LoginData>()
                for (i in start..start + count) {
                    list.add(LoginData("title is $i"))
                }
            }

//        Observable.interval(2, TimeUnit.SECONDS)
//            .subscribeOn(Schedulers.io())
//            .subscribe(Consumer {
//                Log.e("TAG", "interval $it")
//                mainData?.title = "title is $it"
//                mainData.notifyChange()
//            })
    }

    override fun onCleared() {
        super.onCleared()

    }
}

