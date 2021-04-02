package com.yi.kotlin.action

import com.yi.kotlin.action.base.ApiCallback
import com.yi.common.http.BaseResponse
import com.yi.kotlin.action.base.BaseAction
import com.yi.kotlin.data.LoginData
import io.reactivex.Observable

/**
 * create by Yi on 2021/4/2
 *
 */
class LoginAction() : BaseAction<LoginData>() {

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
        body: MutableMap<String, Any>
    ): Observable<BaseResponse<LoginData>> {
        return getApi().login("", "111", headers, body)
    }
}