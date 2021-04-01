package com.yi.kotlin.http.action

import com.yi.kotlin.data.MainData
import com.yi.kotlin.http.base.ApiCallback
import com.yi.kotlin.http.base.BaseAction
import io.reactivex.Observable

/**
 * @author Yi
 * @date 2020/5/9
 */
open class TestAction : BaseAction<MainData>() {
    override fun getRequest(): Observable<MainData> {
        val headers = mutableMapOf<String, String>("New" to "1")
        var request = getApi().login("kotlin/login", "thisIsToken", headers, getRequestMap())
        return request
    }
}