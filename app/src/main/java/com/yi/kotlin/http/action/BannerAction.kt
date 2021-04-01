package com.yi.kotlin.http.action

import com.yi.kotlin.data.BannerData
import com.yi.kotlin.http.base.BaseAction
import io.reactivex.Observable

/**
 * @author Yi
 * @date 2020/5/12
 */
class BannerAction : BaseAction<BannerData>() {
    override fun getRequest(): Observable<BannerData> {
        return getApi().changeBaseUrl("2018135")
    }

//    override fun  getRequest(): Observable<BannerData> {
//        var request = getApi().changeBaseUrl("2018135")
//        return request
//    }
//    fun enqueue(callback: ApiCallback<BannerData>? = null) {
////        var request = getApi().getTicket()
////        var request = getApi().getDate()
////        var request = getApi().changeBaseUrl("lottery/ssq/aim_lottery?expect=2018135")
//        var request =
//            getApi().changeBaseUrl("2018135")
//        execute(request, callback)
//    }


}