package com.yi.kotlin.http.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Yi
 * @date 2020/5/9
 */
open abstract class BaseAction {

    protected fun getRequestMap(): Map<String, Any> {
        var params = hashMapOf<String, Any>("common" to 1)
        //TODO common params
        params["value"] = "1"
        return params
    }

    protected fun getApi() = RetrofitClient.api

    protected fun <T : BaseData> excuse(request: Observable<T>, callback: BaseCallback<T>) {
        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
    }
}