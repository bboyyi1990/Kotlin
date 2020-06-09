package com.yi.kotlin.http.action

import com.yi.kotlin.data.BannerData
import com.yi.kotlin.http.base.ApiCallback
import com.yi.kotlin.http.base.BaseAction

/**
 * @author Yi
 * @date 2020/5/12
 */
class BannerAction : BaseAction() {
    fun enqueue(callback: ApiCallback<BannerData>) {
//        var request = getApi().getTicket()
        var request = getApi().getDate()
        excuse(request, callback)
    }
}