package com.yi.kotlin.http.base

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * @author Yi
 * @date 2020/5/9
 */
open abstract class BaseCallback<T : BaseData> : Observer<T> {
    override fun onComplete() {
        requestComplete()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: T) {
//        TODO("deal common business")
        if (t.code == 200) {

        }
    }

    override fun onError(e: Throwable) {
        requestComplete()
    }

    open fun requestComplete() {

    }
}