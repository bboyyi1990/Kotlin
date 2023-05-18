package com.yi.kotlin.action.user

import com.yi.common.http.BaseData
import com.yi.common.http.BaseResponse
import com.yi.kotlin.action.api.ApiCallback
import com.yi.kotlin.action.api.ApiAction
import io.reactivex.Observable

/**
 * create by Yi on 2021/4/3
 *
 */
class CheckRegisteredAction : ApiAction<BaseData>() {

    fun enqueue(phone: String, area: String, callback: ApiCallback<BaseData>) {
        val params = mutableMapOf<String, Any>("phone" to phone, "area" to area)
        execute(callback, params)
    }

    override fun getApiObservable(
        headers: MutableMap<String, Any>,
        params: MutableMap<String, Any>
    ): Observable<BaseResponse<BaseData>> {
        return getApi().userGet("www.baidu.com", headers, params)
    }
}