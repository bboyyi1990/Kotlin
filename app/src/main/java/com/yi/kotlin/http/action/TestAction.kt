package com.yi.kotlin.http.action

import com.yi.kotlin.data.MainData
import com.yi.kotlin.http.base.ApiCallback
import com.yi.kotlin.http.base.BaseAction

/**
 * @author Yi
 * @date 2020/5/9
 */
open class TestAction : BaseAction() {
    open fun enqueue(callback: ApiCallback<MainData>) {
        var request = getApi().login("kotlin/login", "thisIsToken", getRequestMap())
        excuse(request, callback)
    }
}