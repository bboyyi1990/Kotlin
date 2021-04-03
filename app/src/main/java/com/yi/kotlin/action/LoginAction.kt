package com.yi.kotlin.action

import com.yi.kotlin.action.api.ApiCallback
import com.yi.common.http.BaseResponse
import com.yi.kotlin.action.api.ApiAction
import com.yi.kotlin.data.LoginData
import io.reactivex.Observable

/**
 * create by Yi on 2021/4/2
 *
 */
class LoginAction() : ApiAction<LoginData>() {

    constructor(number: String, password: String, callback: ApiCallback<LoginData>?) : this() {
        val params = mutableMapOf<String, Any>()
        super.execute(callback, params)
    }

    fun enqueue(number: String, password: String, callback: ApiCallback<LoginData>?) {
        val body = mutableMapOf<String, Any>("number" to number, "password" to password)
        super.execute(callback, body)
    }

    override fun getApiObservable(
        headers: MutableMap<String, Any>,
        params: MutableMap<String, Any>
    ): Observable<BaseResponse<LoginData>> {
        return getApi().userPOST("",headers,params)
    }
}