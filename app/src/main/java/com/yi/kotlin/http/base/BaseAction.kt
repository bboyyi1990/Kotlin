package com.yi.kotlin.http.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Yi
 * @date 2020/5/9
 */
open abstract class BaseAction<T : BaseData> {

    protected fun getRequestMap(): Map<String, Any> {
        var params = hashMapOf<String, Any>("common" to 1)
        //add common params
        params["value"] = "1"
        return params
    }

    protected fun getApi() = RetrofitClient.api

    fun enqueue(callback: ApiCallback<T>?) {
        execute(getRequest(), callback)
    }

    protected abstract fun getRequest(): Observable<T>

    /**
     * final execute method
     * @param request
     * @param callback if don't care about result,can enter null
     */
    private fun execute(request: Observable<T>, callback: BaseCallback<T>?) {
        var task = request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        if (null == callback) task.subscribe() else task.subscribe(callback)
    }
}