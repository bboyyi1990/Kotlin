package com.yi.common.http

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * create by Yi on 2021/4/4
 * 抽象请求动作
 * 上层模块使用需要实现此类方法
 */
open abstract class BaseAction<T : BaseData> {

    abstract fun getApi(): Any

    abstract fun buildHeaders(headers: MutableMap<String, Any> = mutableMapOf()): MutableMap<String, Any>

    abstract fun buildParams(headers: MutableMap<String, Any> = mutableMapOf()): MutableMap<String, Any>

    abstract fun getApiObservable(headers: MutableMap<String, Any>, params: MutableMap<String, Any>)
            : Observable<BaseResponse<T>>

    /**
     * final execute method
     * @param params
     * @param callback if don't care about result,can enter null
     */
    protected fun execute(
        callback: BaseCallback<T>?,
        params: MutableMap<String, Any> = mutableMapOf()
    ) {
        buildParams(params)
        val headers = buildHeaders()
        val api = getApiObservable(headers, params)
        var task = api.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
        if (null == callback) task.subscribe() else task.subscribe(callback)
    }
}