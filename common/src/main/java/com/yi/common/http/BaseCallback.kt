package com.yi.common.http

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * @author Yi
 * @date 2020/5/9
 * 抽象请求回调
 * 上层模块使用需要继承此类
 */
open abstract class BaseCallback<T : BaseData> : Observer<BaseResponse<T>> {
    override fun onComplete() {
        requestComplete()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: BaseResponse<T>) {
    }

    override fun onError(e: Throwable) {
        requestComplete()
    }

    open fun requestComplete() {

    }
}