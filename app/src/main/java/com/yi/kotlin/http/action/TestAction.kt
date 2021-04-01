package com.yi.kotlin.http.action

import com.yi.kotlin.data.MainData
import com.yi.kotlin.http.base.ApiCallback
import com.yi.kotlin.http.base.BaseAction
import io.reactivex.Observable

/**
 * @author Yi
 * @date 2020/5/9
 */
class TestAction : BaseAction<MainData>() {
    override fun getRequest(): Observable<MainData> {
        val headers = mutableMapOf<String, Any>("New" to "1", "TEST" to "2")
        var request = getApi().login("kotlin/login", "thisIsToken", headers, getRequestMap())
//        var request = getApi().addLikeRequest("1", "1", "1", "1", mutableMapOf())
        return request
    }
}