package com.yi.kotlin.http.base

import com.yi.common.http.BaseResponse
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * @author Yi
 * @date 2020/5/9
 */
open abstract class BaseCallback<BaseData> : Observer<BaseResponse<BaseData>> {
    override fun onComplete() {
        requestComplete()
    }

    override fun onSubscribe(d: Disposable) {
    }

    override fun onNext(t: BaseResponse<BaseData>) {
    }

    override fun onError(e: Throwable) {
        requestComplete()
    }

    open fun requestComplete() {

    }
}